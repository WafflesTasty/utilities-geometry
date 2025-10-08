package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Scaled;
import waffles.utils.geom.utilities.Transformable;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Scalable} object can be scaled in an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Transformable
 * @see Scaled
 */
public interface Scalable extends Scaled, Transformable
{		
	/**
	 * Scales the {@code Scaled} to a new size.
	 * 
	 * @param v  a scale vector
	 * 
	 * 
	 * @see Vector
	 */
	public default void scaleTo(Vector v)
	{
		Transform().setScale(v);
	}
	
	/**
	 * Scales the {@code Scaled} for a given factor.
	 * 
	 * @param v  a scale direction
	 * @param d  a scale distance
	 * 
	 * 
	 * @see Vector
	 */
	public default void scaleFor(Vector v, float d)
	{
		if(ERROR < Floats.abs(d - 1f))
		{
			scaleFor(v.normalize().times(d));
		}
	}
	
	/**
	 * Scales the {@code Scaled} for a given factor.
	 * 
	 * @param v  a scale factor
	 * 
	 * 
	 * @see Vector
	 */
	public default void scaleFor(Vector v)
	{		
		scaleTo(Scale().hadamard(v));
	}
	
	
	@Override
	public abstract Scaled.Mutable Transform();
	
	@Override
	public default Vector Scale()
	{
		return Transform().Scale();
	}
}