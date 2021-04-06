package zeno.util.geom.collidables.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.Affine;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.collisions.affine.CLSHSpace;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code HSpace} class defines a euclidian affine halfspace.
 * Affine spaces are immutable objects designed to produce various bases,
 * and allow for intersection and containment checks.
 *
 * @author Zeno
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Affine
 */
public class HSpace implements Affine
{
	private Point origin;
	private VSpace normal;
	
	/**
	 * Creates a new {@code HSpace}.
	 * 
	 * @param o  an origin point
	 * @param n  a normal vector
	 * 
	 * 
	 * @see Point
	 * @see Vector
	 */
	public HSpace(Point o, Vector n)
	{
		origin = o;
		normal = new VSpace(n);
	}
	
	
	/**
	 * Returns the origin of the {@code HSpace}.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Point Origin()
	{
		return origin;
	}
	
	/**
	 * Returns the normal of the {@code HSpace}.
	 * 
	 * @return  a normal space direction
	 * 
	 * 
	 * @see VSpace
	 */
	public VSpace Normal()
	{
		return normal;
	}
	
	/**
	 * Returns the dimension of the {@code Affine Space}.
	 * 
	 * @return  a space dimension
	 */
	public int Dimension()
	{
		return origin.Size() - 2;
	}

	
	@Override
	public Factory Factory()
	{
		return (m) ->
		{
			Point p = new Point(m.Column(0));
			Point q = new Point(m.Column(1));
			
			Vector n = Geometries.subtract(p, q);
			return new HSpace(p, n);
		};
	}
	
	@Override
	public ICollision Collisions()
	{
		return new CLSHSpace(this);
	}
	
	@Override
	public Matrix Span()
	{
		Matrix span = Normal().Span();
		float m = Origin().Mass();
		Point o = Origin();
		
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows + 1, cols + 1);
		for(int c = 0; c <= cols; c++)
		{
			span.set(m, rows, c);
			for(int r = 0; r < rows; r++)
			{
				float val = o.get(r) + m * span.get(r, c);
				span.set(val, r, c);
			}
		}
		
		return span;
	}
}