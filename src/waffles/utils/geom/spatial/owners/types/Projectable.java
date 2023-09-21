package waffles.utils.geom.spatial.owners.types;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Projectable} object can be projected into a vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface Projectable
{
	/**
	 * Returns the oculus of the {@code Projectable}.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Oculus();
	
	/**
	 * Projects the {@code Projectable} to a new oculus.
	 * 
	 * @param v  a new oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void projectTo(Vector v);
	
	/**
	 * Moves the oculus of the {@code Projectable} for a given distance.
	 * 
	 * @param v  a direction to move in
	 * @param d  a distance to move for
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectFor(Vector v, float d)
	{
		if(!Floats.isZero(d, 1))
		{
			projectFor(v.normalize().times(d));
		}
	}
	
	/**
	 * Moves the oculus of the {@code Projectable} for a given distance.
	 * 
	 * @param v  a direction to move in
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectFor(Vector v)
	{
		projectTo(Oculus().plus(v));
	}
}