package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.tools.patterns.properties.Inaccurate;

/**
 * The {@code ASpace} class defines a standard euclidian affine space.
 * Affine spaces are immutable objects designed to produce various bases,
 * and allow for intersection and containment checks.
 *
 * @author Zeno
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see ICollidable
 * @see Inaccurate
 */
public class ASpace implements Inaccurate<ASpace>, ICollidable
{
	private Matrix hmat;
	private Vector origin;
	private VSpace space;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param s  a vector direction
	 * 
	 * 
	 * @see Vector
	 * @see VSpace
	 */
	public ASpace(Vector o, VSpace s)
	{
		origin = o;
		space = s;
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param pts  a set of affine points
	 * 
	 * 
	 * @see Vector
	 */
	public ASpace(Vector... pts)
	{
		int cols = pts.length;
		int rows = pts[0].Size();
		
		hmat = Matrices.create(rows + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			hmat.set(1f, rows, c);
			for(int r = 0; r < rows; r++)
			{
				hmat.set(pts[c].get(r), r, c);
			}
		}
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param m  a homogenous matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public ASpace(Matrix m)
	{
		hmat = m;
	}

	/**
	 * Creates a new {@code ASpace}.
	 */
	public ASpace()
	{
		// NOT APPLICABLE
	}
	
	
	/**
	 * Returns the origin of the {@code ASpace}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Origin()
	{
		if(origin == null)
		{
			int rows = hmat.Rows();
			int cols = hmat.Columns();
	
			
			origin = hmat.Column(cols-1);
			origin = Vectors.resize(origin, rows-1);
			float scale = hmat.get(rows-1, cols-1);
			origin = origin.times(1f / scale);
		}
		
		return origin;
	}
	
	/**
	 * Returns the direction of the {@code ASpace}.
	 * 
	 * @return  a direction space
	 * 
	 * 
	 * @see VSpace
	 */
	public VSpace Direction()
	{
		if(space == null)
		{
			int rows = hmat.Rows();
			int cols = hmat.Columns();
						
			Vector s = hmat.Row(rows - 1);
			Matrix d = Matrices.diagonal(s);
			d.setOperator(Diagonal.Type());
			
			Matrix m = hmat.times(d.pseudoinverse());
			m = Matrices.resize(m, rows - 1, cols - 1);
			for(int r = 0; r < rows - 1; r++)
			{
				float val = m.get(r, cols - 1);
				for(int c = 0; c < cols - 1; c++)
				{
					m.set(m.get(r, c) - val, r, c);
				}
			}
			
			space = new VSpace(m);
		}
		
		return space;
	}
	
	/**
	 * Returns the complement of the {@code ASpace}.
	 * 
	 * @return  an affine complement matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Complement()
	{
		Matrix comp = Direction().Complement();
		
		int cols = comp.Columns();
		int rows = comp.Rows();
		
		comp = Matrices.resize(comp, rows, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				comp.set(comp.get(r, c) + origin.get(r), r, c);
			}
		}
		
		return comp;
	}
	
	/**
	 * Returns the affine span of the {@code ASpace}.
	 * 
	 * @return  an affine span matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix Span()
	{
		Matrix span = Direction().Span();
		
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				span.set(span.get(r, c) + origin.get(r), r, c);
			}
		}
		
		return span;
	}
	
	
	/**
	 * Returns the affine matrix of the {@code ASpace}.
	 * 
	 * @return  a homogenous matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix AMatrix()
	{
		if(hmat == null)
		{
			int rows = origin.Size() + 1;
			int cols = space.Dimension() + 1;
			
			hmat = Matrices.resize(space.Span(), rows, cols);
			for(int c = 0; c < cols; c++)
			{
				hmat.set(1f, rows - 1, c);
				for(int r = 0; r < rows - 1; r++)
				{
					hmat.set(hmat.get(r, c) + origin.get(r), r, c);
				}
			}
		}
		
		return hmat;
	}
	
	/**
	 * Returns the vector matrix of the {@code ASpace}.
	 * 
	 * @return  a non-homogenous matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix VMatrix()
	{
		Matrix mat = AMatrix();
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		Matrix m = Matrices.resize(mat, rows - 1, cols);
		for(int c = 0; c < cols; c++)
		{
			float val = AMatrix().get(rows - 1, c);
			for(int r = 0; r < rows - 1; r++)
			{
				m.set(m.get(r, c) / val, r, c);
			}
		}
		
		return m;
	}
	
	/**
	 * Returns the dimension of the {@code ASpace}.
	 * 
	 * @return  a space dimension
	 */
	public int Dimension()
	{
		return Direction().Dimension();
	}

	
	/**
	 * Checks if the {@code ASpace} contains a space.
	 * 
	 * @param s  a space to check
	 * @return  {@code true} if the space is contained
	 */
	public boolean contains(ASpace s)
	{
		return Direction().contains(s.Direction())
			&& intersects(s);
	}
	
	/**
	 * Returns an intersection with the {@code ASpace}.
	 * 
	 * @param s  a space to intersect
	 * @return  an intersected space
	 */
	public ASpace intersect(ASpace s)
	{
		int size = Origin().Size();
		Vector v = Origin().minus(s.Origin());
		VSpace sum = Direction().add(s.Direction());
		Vector x = sum.coordinates(v);
		if(x == null)
		{
			return ASpaces.trivial(size);
		}
		
		x = Vectors.resize(x, size);
		x = Origin().plus(Direction().Span().times(x));
		
		Matrix m = sum.RowComplement();
		m = Matrices.resize(m, Dimension(), m.Columns());
		VSpace dir = new VSpace(sum.Span().times(m));
		ASpace space = new ASpace(x, dir);
		m = space.AMatrix();
		
		return ASpaces.create(m);
	}
	
	/**
	 * Returns a direct sum with the {@code ASpace}.
	 * 
	 * @param s  a space to add
	 * @return  an added space
	 */
	public ASpace add(ASpace s)
	{
		return ASpaces.create(Matrices.concat(AMatrix(), s.AMatrix()));
	}

	
	@Override
	public boolean equals(ASpace s, int ulps)
	{
		return Direction().equals(s.Direction(), ulps)
			&& intersects(s);
	}

	@Override
	public boolean intersects(ASpace s)
	{
		return intersect(s).Dimension() >= 0; 
	}

	@Override
	public boolean contains(Vector v)
	{
		return Direction().contains(v.minus(Origin()));
	}
}