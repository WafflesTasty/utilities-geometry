package zeno.util.geom.transformables.projective;

import zeno.util.algebra.linear.vector.fixed.Vector2;

/**
 * The {@code IProjectable2D} interface defines an object
 * capable of being projected through a 2D vector space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IProjectable
 */
public interface IProjectable2D extends IProjectable
{	
	/**
	 * Moves the oculus of the {@code IProjectable2D} for a specified distance.
	 * 
	 * @param x  an x-direction to move in 
	 * @param y  an y-direction to move in
	 */
	public default void projectFor(float x, float y)
	{
		projectFor(new Vector2(x, y));
	}
	
	/**
	 * Projects the {@code IProjectable2D} to a new oculus.
	 * 
	 * @param x  an oculus x-coördinate 
	 * @param y  an oculus y-coördinate
	 */
	public default void projectTo(float x, float y)
	{
		projectTo(new Vector2(x, y));
	}
	
	
	@Override
	public abstract Vector2 Oculus();
}