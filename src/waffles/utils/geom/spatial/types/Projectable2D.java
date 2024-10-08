package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.data.unary.Projected2D;

/**
 * A {@code Projectable2D} object can be projected into a two-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 * 
 * 
 * @see Projectable
 * @see Projected2D
 */
public interface Projectable2D extends Projectable, Projected2D
{		
	/**
	 * Moves the oculus for a given distance.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 */
	public default void projectFor(float x, float y)
	{
		projectFor(new Vector2(x, y));
	}
	
	/**
	 * Projects the {@code Projectable2D} to a new oculus.
	 * 
	 * @param x  an x-coordinate 
	 * @param y  an y-coordinate
	 */
	public default void projectTo(float x, float y)
	{
		projectTo(new Vector2(x, y));
	}
	
	
	@Override
	public default Vector2 Oculus()
	{
		return (Vector2) Projectable.super.Oculus();
	}
}