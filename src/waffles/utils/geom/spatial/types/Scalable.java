package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.unary.Scaled;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Scalable} object can be scaled in an n-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Scaled
 */
public interface Scalable extends Scaled
{
	/**
	 * Returns the transform of the {@code Scalable}.
	 * 
	 * @return  a scaling mutable
	 * 
	 * 
	 * @see Scaled
	 */
	public abstract Scaled.Mutable Transform();
	
	
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
		if(!Floats.isEqual(d, 1f, 1))
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
		scaleTo(Scale().ltimes(v));
	}
	
	
	@Override
	public default Vector Scale()
	{
		return Transform().Scale();
	}
}