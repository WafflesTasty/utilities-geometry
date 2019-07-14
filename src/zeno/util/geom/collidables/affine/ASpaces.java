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
import zeno.util.geom.collidables.affine.lines.LineND;
import zeno.util.geom.collidables.affine.spaces.FullASpace;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;

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
	 * @see ASpace
	 */
	public static ASpace full(int dim)
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
	 * @see ASpace
	 */
	public static ASpace trivial(int dim)
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
		Matrix mat = s.HMatrix();
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
		Matrix mat = s.HMatrix();
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
			
		return hset(m);
	}
	
	/**
	 * Defines an affine space that expands a new coördinate count.
	 * The amount of coördinates change and can increase dimension as well.
	 * 
	 * @param s  a space to expand
	 * @param coords  a coördinate count to use
	 * @return  a new expanded affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace expand(ASpace s, int coords)
	{
		return span(expand(s.Span(), coords));
	}
	
	/**
	 * Defines an affine space that occupies a new coördinate count.
	 * The amount of coördinates change but this does not increase dimension.
	 * 
	 * @param s  a space to occupy
	 * @param coords  a coördinate count to use
	 * @return  a new occupying affine space
	 * 
	 * 
	 * @see ASpace
	 */
	public static ASpace occupy(ASpace s, int coords)
	{
		return span(occupy(s.Span(), coords));
	}
	
	
	/**
	 * Creates a new affine space from static access.
	 * 
	 * @param set  an affine set to span
	 * @return  an affine space
	 * 
	 * 
	 * @see ASpace
	 * @see Affine
	 */
	public static ASpace span(Affine set)
	{
		int size = set.Size() - 1;
		APoint[] pts = set.Points();
		
		APoint o = pts[size];
		Vector[] vecs = new Vector[size];
		for(int i = 0; i < size; i++)
		{
			vecs[i] = pts[i].minus(o);
		}
		
		int dim = o.VMatrix().Size();
		if(size == 0)
		{
			return span(o, VSpaces.trivial(dim));
		}
		
		return span(o, VSpaces.create(vecs));
	}
	
	/**
	 * Creates a new affine space from static access.
	 * 
	 * @param mats  a set of points
	 * @return  an affine space
	 * 
	 * 
	 * @see Matrix
	 * @see ASpace
	 */
	public static ASpace span(Matrix... mats)
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
	 * @see APoint
	 * @see ASpace
	 * @see VSpace
	 */
	public static ASpace span(APoint o, VSpace v)
	{
		if(v.Dimension() == 0)
			return o;
		if(v.Dimension() == 1)
			return span(o, (Vector) v.Span());
		
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
	 * @see APoint
	 * @see ASpace
	 * @see Vector
	 */
	public static ASpace span(APoint o, Vector v)
	{
		if(v.Size() == 2)
			return new Line2D(o, (Vector2) v);
		if(v.Size() == 3)
			return new Line3D(o, (Vector3) v);
		return new LineND(o, v);
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
	public static Affine hset(Matrix... mats)
	{
		Matrix m = Matrices.concat(mats);
		if(m instanceof Vector)
		{
			return vset(vectorize(m));
		}

		return new Affine.HSet(m);
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
	public static Affine vset(Matrix... mats)
	{
		Matrix m = Matrices.concat(mats);
		if(m instanceof Vector)
		{
			return new APoint((Vector) m);
		}

		return new Affine.VSet(m);
	}
	
			
	private ASpaces()
	{
		// NOT APPLICABLE
	}
}