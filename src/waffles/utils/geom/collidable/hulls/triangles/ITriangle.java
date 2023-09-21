package waffles.utils.geom.collidable.hulls.triangles;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.hulls.IHull;
import waffles.utils.geom.collision.hulls.CLSTriangle;

/**
 * The {@code ITriangle} interface is the base for convex hulls of three point sets.
 * 
 * @author Waffles
 * @since 13 Jan 2021
 * @version 1.0 
 * 
 * 
 * @see IHull
 */
public interface ITriangle extends IHull
{
	/**
	 * Returns the first point of the {@code ITriangle}.
	 * 
	 * @return  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public default Point P1()
	{
		Vector v = Generator().Column(0);
		return new Point(v, 1f);
	}
	
	/**
	 * Returns the second point of the {@code ITriangle}.
	 * 
	 * @return  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public default Point P2()
	{
		Vector v = Generator().Column(1);
		return new Point(v, 1f);
	}
	
	/**
	 * Returns the third point of the {@code ITriangle}.
	 * 
	 * @return  a triangle point
	 * 
	 * 
	 * @see Point
	 */
	public default Point P3()
	{
		Vector v = Generator().Column(2);
		return new Point(v, 1f);
	}
	
	
	@Override
	public default CLSTriangle Collisions()
	{
		return new CLSTriangle(this);
	}
}