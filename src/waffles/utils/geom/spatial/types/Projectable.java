package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Projected;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Projectable} object can be projected into an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Projected
 */
public interface Projectable extends Projected
{
	/**
	 * Returns the transform of the {@code Projectable}.
	 * 
	 * @return  a projection mutable
	 * 
	 * 
	 * @see Projected
	 */
	public abstract Projected.Mutable Transform();
	
		
	/**
	 * Projects the {@code Projectable} to a new oculus.
	 * 
	 * @param v  a new oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectTo(Vector v)
	{
		Transform().setOculus(v);
	}
	
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
	
	
	@Override
	public default Vector Oculus()
	{
		return Transform().Oculus();
	}
}