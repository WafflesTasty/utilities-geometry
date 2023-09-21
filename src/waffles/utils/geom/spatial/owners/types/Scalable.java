package waffles.utils.geom.spatial.owners.types;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Scalable} object can be scaled in a vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface Scalable
{
	/**
	 * Returns the size of the {@code Scalable}.
	 * 
	 * @return  a scale vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Size();
	
	/**
	 * Scales the {@code Scalable} to a new size.
	 * 
	 * @param v  a scale vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void scaleTo(Vector v);
	
	/**
	 * Scales the {@code Scalable} for a given factor.
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
	 * Scales the {@code Scalable} for a given factor.
	 * 
	 * @param v  a scale factor
	 * 
	 * 
	 * @see Vector
	 */
	public default void scaleFor(Vector v)
	{		
		scaleTo(Size().eltimes(v));
	}
}