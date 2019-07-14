package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
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
	private VSpace space;
	private APoint origin;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param s  a direction space
	 * 
	 * 
	 * @see APoint
	 * @see VSpace
	 */
	public ASpace(APoint o, VSpace s)
	{
		origin = o;
		space = s;
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param o  an origin point
	 * @param v  a direction vector
	 * 
	 * 
	 * @see APoint
	 * @see Vector
	 */
	public ASpace(APoint o, Vector v)
	{
		space = new VSpace(v);
		origin = o;
	}
	
	
	/**
	 * Returns the origin of the {@code ASpace}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see APoint
	 */
	public APoint Origin()
	{
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
		return space;
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
	 * Returns the span of the {@code ASpace}.
	 * This generates a minimal affine set that spans the space.
	 * 
	 * @return  an affine set
	 * 
	 * 
	 * @see Affine
	 */
	public Affine Span()
	{
		Matrix span = Direction().Span();
		Matrix o = Origin().VMatrix();
		
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			for(int r = 0; r < rows; r++)
			{
				float val = span.get(r, c) + o.get(r);
				span.set(val, r, c);
			}
		}
		
		return ASpaces.vset(span);
	}

	
	/**
	 * Checks if the space contains an {@code ASpace}.
	 * 
	 * @param s  a space to check
	 * @return  {@code true} if the space is contained
	 */
	public boolean contains(ASpace s)
	{
		Vector pq = Origin().minus(s.Origin());
		VSpace dir = Direction().add(s.Direction());		
		return dir.Dimension() == s.Dimension()
			&& dir.contains(pq);
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
		Vector pq = Origin().minus(s.Origin());
		VSpace dir = Direction().add(s.Direction());
		
		// If p-q not in V+W...
		Vector x = dir.coordinates(pq);
		if(x == null)
		{
			// The intersection is empty.
			return ASpaces.trivial(size);
		}
		
		// Otherwise, a common point is found.
		x = Vectors.resize(x, size);
		x = Direction().Span().times(x);
		x = Origin().VMatrix().plus(x);
		
		// Calculate the direction intersection.
		Matrix m = dir.RowComplement();
		m = Matrices.resize(m, Dimension(), m.Columns());
		m = dir.Span().times(m);
		
		// Create the new affine subspace.
		APoint o = new APoint(x);
		VSpace v = VSpaces.create(m);
		return ASpaces.span(o, v);
	}

	
	@Override
	public boolean intersects(ASpace s)
	{
		VSpace dir = Direction().add(s.Direction());		
		Vector pq = Origin().minus(s.Origin());
		return dir.contains(pq); 
	}
	
	@Override
	public boolean equals(ASpace s, int ulps)
	{
		VSpace dir1 = Direction();
		VSpace dir2 = s.Direction();
		return dir1.equals(dir2, ulps)
			&& intersects(s);
	}

	@Override
	public boolean contains(Vector v)
	{
		return contains(new APoint(v));
	}
	
	@Override
	public boolean contains(APoint p)
	{
		VSpace dir = Direction();
		Vector ov = p.minus(Origin());
		return dir.contains(ov);
	}
}