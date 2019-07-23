package zeno.util.geom;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.patterns.properties.Inaccurate;

/**
 * The {@code ICollidable} interface defines an object that can be collided with.
 * It allows spatial queries to be performed through affine spaces.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface ICollidable extends Inaccurate<ICollidable>
{	
	@Override
	public default boolean equals(ICollidable c, int ulps)
	{
		Boolean bln = Collisions().equals(c, ulps);
		if(bln == null)
		{
			bln = c.Collisions().equals(this, ulps);
			if(bln == null)
			{
				throw new Geometries.EqualityError(this, c);
			}
		}
		
		return bln;
	}

	/**
	 * Intersects the {@code ICollidable} with another object.
	 * 
	 * @param c  an object to check
	 * @return  the object intersection
	 */
	public default ICollidable intersect(ICollidable c)
	{
		ICollidable isc = Collisions().intersect(c);
		if(isc == null)
		{
			isc = c.Collisions().intersect(this);
			if(isc == null)
			{
				throw new Geometries.IntersectionError(this, c);
			}
		}
		
		return isc;
	}
	
	/**
	 * Checks if the {@code ICollidable} intersects another object.
	 * 
	 * @param c  an object to check
	 * @return  {@code true} if the object is intersected
	 */
	public default boolean intersects(ICollidable c)
	{
		Boolean bln = Collisions().intersects(c);
		if(bln == null)
		{
			bln = c.Collisions().intersects(this);
			if(bln == null)
			{
				throw new Geometries.IntersectingError(this, c);
			}
		}
		
		return bln;
	}
	
	/**
	 * Checks if the {@code ICollidable} contains another object.
	 * 
	 * @param c  an object to check
	 * @return  {@code true} if the object is contained
	 */
	public default boolean contains(ICollidable c)
	{
		Boolean bln = Collisions().contains(c);
		if(bln == null)
		{
			bln = c.Collisions().inhabits(this);
			if(bln == null)
			{
				throw new Geometries.ContainError(this, c);
			}
		}
		
		return bln;
	}
	
	/**
	 * Indicates if the {@code ICollidable} contains a point.
	 * 
	 * @param v  a vector point to check
	 * @return  {@code true} if the point is contained
	 * 
	 * 
	 * @see Vector
	 */
	public default boolean contains(Vector v)
	{
		return contains(new Point(v));
	}

	/**
	 * Returns the collisions of the {@code ICollidable}.
	 * 
	 * @return  a collision object
	 * 
	 * 
	 * @see ICollision
	 */
	public abstract ICollision Collisions();

	/**
	 * Checks if the {@code ICollidable} is a point.
	 * 
	 * @return  {@code true} if the shape is a point
	 */
	public default boolean isPoint()
	{
		return false;
	}
	
	/**
	 * Checks if the {@code ICollidable} is empty.
	 * 
	 * @return  {@code true} if the shape is empty
	 */
	public default boolean isEmpty()
	{
		return false;
	}
}