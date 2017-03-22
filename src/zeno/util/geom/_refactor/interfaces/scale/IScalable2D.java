package zeno.util.geom._refactor.interfaces.scale;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.ITransformable;
import zeno.util.geom._refactor.interfaces.ITransformation2D;

/**
 * The {@code IScalable2D} interface defines an object
 * capable of being scaled in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
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
		if(w != 1 || h != 1)
		{
			scaleTo(w * getWidth(), h * getHeight());
		}
	}
			
	/**
	 * Scales the {@code IScalable2D} with a set factor.
	 * 
	 * @param vec  a factor to scale with
	 * @see Vector2
	 */
	public default void scaleFor(Vector2 vec)
	{
		if(vec.X() != 1 || vec.Y() != 1)
		{
			scaleFor(vec.X(), vec.Y());
		}
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
	 * @see Vector2
	 */
	public default void scaleTo(Vector2 vec)
	{
		Transform().scaleTo(vec);
	}
	
	
	/**
	 * Returns the height of the {@code IScalable2D}.
	 * 
	 * @return  the scalable's height
	 */
	public default float getHeight()
	{
		return Transform().getHeight();
	}
	
	/**
	 * Returns the width of the {@code IScalable2D}.
	 * 
	 * @return  the scalable's width
	 */
	public default float getWidth()
	{
		return Transform().getWidth();
	}
}