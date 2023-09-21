package waffles.utils.geom.collidable.spaces;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collision.spaces.CLSHSpace;

/**
 * A {@code HSpace} defines a real-valued euclidian halfspace.
 * Halfspaces provide quick binary spatial tests.
 *
 * @author Waffles
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Collidable
 * @see Affine
 */
public class HSpace implements Affine, Collidable
{
	private Point origin;
	private Vector normal;
	
	/**
	 * Creates a new {@code HSpace}.
	 * 
	 * @param o  an origin point
	 * @param n  a normal vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public HSpace(Point o, Vector n)
	{
		origin = o;
		normal = n;
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
	 * @return  a normal vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Normal()
	{
		return normal;
	}
	

	@Override
	public Factory Factory()
	{
		return (m) ->
		{
			int dim = Dimension();
						
			Vector o = m.Column(0);
			Vector v = m.Column(1);
			
			o = Vectors.resize(o, dim);
			v = Vectors.resize(v, dim);
			
			
			float mass = m.get(dim, 0);
			Point p = new Point(o, mass);
			return new HSpace(p, v);
		};
	}
	
	@Override
	public Collision Collisions()
	{
		return new CLSHSpace(this);
	}

	@Override
	public int Dimension()
	{
		return normal.Size();
	}
	
	@Override
	public Matrix Span()
	{
		int dim = Dimension();
		
		Matrix span = Matrices.create(dim+1, 2);
		span.set(origin.Mass(), dim, 0);
		for(int r = 0; r < dim; r++)
		{
			span.set(origin.get(r), r, 0);
			span.set(normal.get(r), r, 1);
		}
		
		return span;
	}
}