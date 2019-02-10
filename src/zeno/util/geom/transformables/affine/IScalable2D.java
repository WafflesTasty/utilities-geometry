package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.tools.Floats;

/**
 * The {@code IScalable2D} interface defines an object
 * capable of being scaled in a 2D affine space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IScalable
 */
public interface IScalable2D extends IScalable
{	
	/**
	 * Scales the {@code IScalable2D} with a specified factor.
	 * 
	 * @param w  a  width factor
	 * @param h  a height factor
	 */
	public default void scaleFor(float w, float h)
	{
		if(!Floats.isEqual(w, 1f, 1) && !Floats.isEqual(h, 1f, 1))
		{
			scaleFor(new Vector2(w, h));
		}
	}

	/**
	 * Scales the {@code IScalable2D} to a new scale vector.
	 * 
	 * @param w  a new  width
	 * @param h  a new height
	 */
	public default void scaleTo(float w, float h)
	{
		scaleTo(new Vector2(w, h));
	}

	
	@Override
	public abstract Vector2 Size();
	
	/**
	 * Returns the height of the {@code IScalable2D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the width of the {@code IScalable2D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Size().X();
	}
}