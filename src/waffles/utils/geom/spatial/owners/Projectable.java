package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Projected;
import waffles.utils.geom.utilities.Transformable;

/**
 * A {@code Projectable} object can be projected into an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Transformable
 * @see Projected
 */
public interface Projectable extends Projected, Transformable
{		
	/**
	 * Projects the {@code Projectable} to a new oculus.
	 * 
	 * @param o  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectTo(Vector o)
	{
		Transform().setOculus(o);
	}
	
	/**
	 * Moves the {@code Projectable} for a given distance.
	 * 
	 * @param v  a direction vector
	 * @param d  a distance value
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectFor(Vector v, float d)
	{
		if(ERROR < d)
		{
			projectFor(v.normalize().times(d));
		}
	}
	
	/**
	 * Moves the {@code Projectable} for a given distance.
	 * 
	 * @param v  a distance vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void projectFor(Vector v)
	{
		projectTo(Oculus().plus(v));
	}
	
	
	@Override
	public abstract Projected.Mutable Transform();
	
	@Override
	public default Vector Oculus()
	{
		return Transform().Oculus();
	}
}