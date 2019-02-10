package zeno.util.geom.transformables.projective;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.tools.Floats;

/**
 * The {@code IProjectable} interface defines an object
 * capable of being projected through a vector space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface IProjectable
{
	/**
	 * Returns the oculus of the {@code IProjectable}.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Oculus();
	
	/**
	 * Projects the {@code IProjectable} to a new oculus vector.
	 * 
	 * @param v  a new oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void projectTo(Vector v);
	
	/**
	 * Moves the oculus of the {@code IProjectable} for a specified distance.
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
	 * Moves the oculus of the {@code IProjectable} for a specified distance.
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