package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.tools.Floats;

/**
 * The {@code IMovable2D} interface defines an object
 * capable of being moved through a 2D affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IMovable
 */
public interface IMovable2D extends IMovable
{		
	/**
	 * Moves the {@code IMovable2D} for a specified distance.
	 * 
	 * @param x  a distance x-coördinate
	 * @param y  a distance y-coördinate
	 */
	public default void moveFor(float x, float y)
	{
		if(!Floats.isZero(x, 1)
		&& !Floats.isZero(y, 1))
		{
			moveFor(new Vector2(x, y));
		}
	}

	/**
	 * Moves the {@code IMovable2D} to a new origin vector.
	 * 
	 * @param x  a new x-coördinate
	 * @param y  a new y-coördinate
	 */
	public default void moveTo(float x, float y)
	{
		moveTo(new Vector2(x, y));
	}

	
	@Override
	public abstract Vector2 Origin();

	/**
	 * Returns the x-coördinate of the {@code IMovable2D}.
	 * 
	 * @return  an x-coördinate
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns the y-coördinate of the {@code IMovable2D}.
	 * 
	 * @return  an y-coördinate
	 */
	public default float Y()
	{
		return Origin().Y();
	}
}