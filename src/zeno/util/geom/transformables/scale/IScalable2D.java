package zeno.util.geom.transformables.scale;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation2D;

/**
 * The {@code IScalable2D} interface defines an object
 * capable of being scaled in 2D space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformable
 */
public interface IScalable2D extends ITransformable
{	
	@Override
	public abstract ITransformation2D Transform();

	
	/**
	 * Scales the {@code IScalable2D} with a specified factor.
	 * 
	 * @param w  a width factor
	 * @param h  a height factor
	 */
	public default void scaleFor(float w, float h)
	{
		if(w != 1f || h != 1f)
		{
			Vector2 s = Transform().Scale();
			scaleTo(w * s.X(), h * s.Y());
		}
	}
			
	/**
	 * Scales the {@code IScalable2D} with a set factor.
	 * 
	 * @param vec  a factor to scale with
	 * 
	 * 
	 * @see Vector2
	 */
	public default void scaleFor(Vector2 vec)
	{
		scaleFor(vec.X(), vec.Y());
	}

	
	/**
	 * Scales the {@code IScalable2D} to a new scale vector.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 */
	public default void scaleTo(float w, float h)
	{
		scaleTo(new Vector2(w, h));
	}
	
	/**
	 * Scales the {@code IScalable2D} to a new scale vector.
	 * 
	 * @param vec  a new scale vector
	 * 
	 * 
	 * @see Vector2
	 */
	public default void scaleTo(Vector2 vec)
	{
		Transform().setScale(vec);
	}
}