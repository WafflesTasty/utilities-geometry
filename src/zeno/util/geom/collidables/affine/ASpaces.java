package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.lines.Line2D;
import zeno.util.geom.collidables.affine.lines.Line3D;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.affine.points.HPoints;
import zeno.util.geom.collidables.affine.points.VPoints;
import zeno.util.geom.collidables.affine.spaces.FullASpace;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;
import zeno.util.tools.helper.Array;

/**
 * The {@code ASpaces} class defines basic {@code ASpace} operations.
 *
 * @author Zeno
 * @since Jul 15, 2018
 * @version 1.0
 * 
 * 
 * @see ASpace
 */
public final class ASpaces
{
	/**
	 * Defines a full affine space for static access.
	 * 
	 * @param dim  an affine coördinate dimension
	 * @return  a full affine space
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine.Space full(int dim)
	{
		return new FullASpace(dim);
	}
	
	/**
	 * Defines a trivial affine space for static access.
	 * 
	 * @param dim  an affine coördinate dimension 
	 * @return  a trivial affine space
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine.Space trivial(int dim)
	{
		return new TrivialASpace(dim);
	}

	
	/**
	 * Defines an affine set that expands a new coördinate count.
	 * The amount of coördinates change and can increase dimension as well.
	 * 
	 * @param s  a set to expand
	 * @param coords  a coördinate count to use
	 * @return  a new expanded affine set
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine expand(Affine s, int coords)
	{
		Matrix mat = s.Span().HMatrix();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = mat;
		if(rows > coords)
		{
			m = Matrices.resize(m, coords + 1, cols);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous coördinates.
				m.set(mat.get(rows - 1, c), coords, c);
			}
		}
		else
		{
			m = Matrices.resize(m, coords + 1, coords + cols - rows + 1);
			for(int c = 0; c < cols; c++)
			{
				// Move the original homogenous coördinates.
				m.set(mat.get(rows - 1, c), coords, c);
				m.set(0f, rows - 1, c);
			}
			
			float val = mat.get(rows - 1, cols - 1);
			for(int c = cols; c <= coords + cols - rows; c++)
			{
				m.set(val, coords, c);
				m.set(val, rows + c - cols - 1, c);
				for(int r = 0; r < rows - 1; r++)
				{
					// Copy the final affine point.
					m.set(mat.get(r, cols - 1), r, c);
				}
			}
		}
		
		if(!s.isFinite())
			return span(hset(m));
		return hset(m);
	}
	
	/**
	 * Defines an affine set that occupies a new coördinate count.
	 * The amount of coördinates change but this does not increase dimension.
	 * 
	 * @param s  a set to occupy
	 * @param coords  a coördinate count to use
	 * @return  a new occupying affine set
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine occupy(Affine s, int coords)
	{
		Matrix mat = s.Span().HMatrix();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = Matrices.resize(mat, coords + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			// Move the original homogenous coördinates.
			m.set(mat.get(rows - 1, c), coords, c);
			if(rows <= coords)
			{
				m.set(0f, rows - 1, c);
			}
		}
		
		if(!s.isFinite())
			return span(hset(m));
		return hset(m);
	}
	
	
	/**
	 * Creates a new affine space from static access.
	 * 
	 * @param set  an affine set to span
	 * @return  an affine space
	 * 
	 * 
	 * @see Affine
	 */
	public static Affine.Space span(Affine.Set set)
	{
		if(set.isEmpty())
		{
			return (Affine.Space) set;
		}
		
		Point o = null;
		Vector[] vecs = new Vector[0];
		for(Point p : set)
		{
			if(o != null)
			{
				vecs = Array.add.to(vecs, p.minus(o));
				continue;
			}
			
			o = p;
		}

		if(vecs.length > 0)
		{
			return span(o, VSpaces.create(vecs));
		}
		
		return o;
	}
	
	/**
	 * Creates a new affine space from static access.
	 * 
	 * @param mats  a set of points
	 * @return  an affine space
	 * 
	 * 
	 * @see Affine
	 * @see Matrix
	 */
	public static Affine.Space span(Matrix... mats)
	{
		return span(vset(mats));
	}
	
	/**
	 * Creates a new affine space from static access.
	 * 
	 * @param o  an affine origin
	 * @param v  a vector space direction
	 * @return  a new affine space
	 * 
	 * 
	 * @see Point
	 * @see Affine
	 * @see VSpace
	 */
	public static Affine.Space span(Point o, VSpace v)
	{
		int size = v.Span().Rows();

		if(o == null)
			return trivial(size);
		if(v.Dimension() == size)
			return full(size);
		if(v.Dimension() == 1)
			return span(o, (Vector) v.Span());
		if(v.Dimension() == 0)
			return o;

		return new ASpace(o, v);
	}
	
	/**
	 * Creates a new affine line from static access.
	 * 
	 * @param o  an affine origin
	 * @param v  a vector direction
	 * @return  a new affine line
	 * 
	 * 
	 * @see Point
	 * @see ASpace
	 * @see Vector
	 */
	public static ASpace span(Point o, Vector v)
	{
		if(v.Size() == 2)
			return new Line2D(o, (Vector2) v);
		if(v.Size() == 3)
			return new Line3D(o, (Vector3) v);
		return new Line(o, v);
	}

	
	/**
	 * Adds a homogeneous coördinate row to a matrix.
	 * 
	 * @param mat  a matrix to homogenize
	 * @return  a homogenized matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix homogenize(Matrix mat)
	{
		int rows = mat.Rows();
		int cols = mat.Columns();
		
		Matrix hmat = Matrices.create(rows + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			hmat.set(1f, rows, c);
			for(int r = 0; r < rows; r++)
			{
				hmat.set(mat.get(r, c), r, c);
			}
		}
		
		return hmat;
	}
	
	/**
	 * Removes a homogeneous coördinate from a matrix.
	 * 
	 * @param mat  a matrix to vectorize
	 * @return  a vectorized matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix vectorize(Matrix mat)
	{
		int rows = mat.Rows() - 1;
		int cols = mat.Columns();
		
		Matrix vmat = Matrices.create(rows, cols);
		for(int c = 0; c < cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				float val = mat.get(r, c) / mat.get(rows, c);
				vmat.set(val, r, c);
			}
		}
		
		return vmat;
	}
	
	
	/**
	 * Creates a new affine set from static access.
	 * This method requires homogeneous matrices.
	 * 
	 * @param mats  a set of points
	 * @return  an affine set
	 * 
	 * 
	 * @see Affine
	 * @see Matrix
	 */
	public static Affine.Set hset(Matrix... mats)
	{
		Matrix m = Matrices.concat(mats);
		if(m instanceof Vector)
		{
			return vset(vectorize(m));
		}

		return new HPoints(m);
	}
	
	/**
	 * Creates a new affine set from static access.
	 * This method requires non-homogeneous matrices.
	 * 
	 * @param mats  a set of points
	 * @return  an affine set
	 * 
	 * 
	 * @see Affine
	 * @see Matrix
	 */
	public static Affine.Set vset(Matrix... mats)
	{
		Matrix m = Matrices.concat(mats);
		
		if(m.Columns() == 0)
		{
			return (Affine.Set) trivial(m.Rows());
		}
		
		if(m instanceof Vector)
		{
			return new Point((Vector) m);
		}

		return new VPoints(m);
	}
	
	/**
	 * Creates a new affine set from static access.
	 * 
	 * @param pts  a set of points
	 * @return  an affine set
	 * 
	 * 
	 * @see Affine
	 * @see Point
	 */
	public static Affine.Set set(Point... pts)
	{
		Vector[] set = new Vector[pts.length];
		for(int i = 0; i < pts.length; i++)
		{
			set[i] = pts[i].VMatrix();
		}

		return vset(set);
	}
	
			
	private ASpaces()
	{
		// NOT APPLICABLE
	}
}