package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.tools.Floats;

/**
 * The {@code IScalable} interface defines an object
 * capable of being scaled in a affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 */
public interface IScalable
{
	/**
	 * Returns the size of the {@code IScalable}.
	 * 
	 * @return  a size vector
	 * @see Vector
	 */
	public abstract Vector Size();
	
	/**
	 * Scales the {@code IScalable} to a new size vector.
	 * 
	 * @param v  a new scale vector
	 * @see Vector
	 */
	public abstract void scaleTo(Vector v);
	
	/**
	 * Scales the {@code IScalable} for a specified factor.
	 * 
	 * @param v  a direction to move in
	 * @param d  a distance to move for
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
	 * Scales the {@code IScalable} for a specified factor.
	 * 
	 * @param v  a scale factor to use
	 * @see Vector
	 */
	public default void scaleFor(Vector v)
	{		
		scaleTo(Vectors.eMult(Size(), v));
	}
}