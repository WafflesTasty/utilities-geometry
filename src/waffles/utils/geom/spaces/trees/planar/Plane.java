package waffles.utils.geom.spaces.trees.planar;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Plane} separates n-dimensional space across a translated axis.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see Collidable
 * @see Positioned
 */
public class Plane implements Positioned, Collidable
{
	private int axis;
	private HSpace s;

	/**
	 * Creates a new {@code Plane}.
	 * 
	 * @param k  a plane axis
	 * @param d  a plane dimension
	 * @param v  a plane value
	 */
	public Plane(int k, int d, float v)
	{
		Vector x = Vectors.create(d);
		Vector y = Vectors.create(d);
		float w = Floats.abs(v);
		
		x.set(v + w, d);
		y.set(v, k);
		
		
		Point p = new Point(x, 1f);
		Point q = new Point(y, 1f);
		
		s = new HSpace(p, q);
		axis = k;
	}

	
	/**
	 * Returns the value of the {@code Plane}.
	 * 
	 * @return  a plane value
	 */
	public float Value()
	{
		return Origin().aff(axis);
	}
	
	/**
	 * Returns the axis of the {@code Plane}.
	 * 
	 * @return  a plane axis
	 */
	public int Axis()
	{
		return axis;
	}
	
	
	@Override
	public Collision Collision()
	{
		return s.Collision();
	}

	@Override
	public Point Origin()
	{
		return s.Origin();
	}


}