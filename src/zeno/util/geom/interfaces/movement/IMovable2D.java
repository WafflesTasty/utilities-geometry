package zeno.util.geom.interfaces.movement;

import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.ITransformable;
import zeno.util.geom.interfaces.ITransformation2D;

/**
 * The {@code IMovable2D} interface defines an object
 * capable of being moved in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see ITransformable
 */
public interface IMovable2D extends ITransformable
{
	@Override
	public abstract ITransformation2D Transform();
	
	
	/**
	 * Moves the {@code IMovable2D} for a specified distance.
	 * 
	 * @param v  a vector direction to move
	 * @param dist  a distance to move
	 * @see Vector2
	 */
	public default void moveFor(Vector2 v, float dist)
	{
		if(dist != 0)
		{
			moveFor(v.normalize().times(dist));
		}
	}
		
	/**
	 * Moves the {@code IMovable2D} for a specified distance.
	 * 
	 * @param x  a distance x-coördinate
	 * @param y  a distance y-coördinate
	 */
	public default void moveFor(float x, float y)
	{
		moveTo(x + getX(), y + getY());
	}
	
	/**
	 * Moves the {@code IMovable2D} for a specified distance.
	 * 
	 * @param vec  a distance to move for
	 * @see Vector2
	 */
	public default void moveFor(Vector2 vec)
	{
		if(vec.X() != 0 || vec.Y() != 0)
		{
			moveTo(vec.X() + getX(), vec.Y() + getY());
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
	
	/**
	 * Moves the {@code IMovable2D} to a new origin vector.
	 * 
	 * @param vec  a new origin vector
	 * @see Vector2
	 */
	public default void moveTo(Vector2 vec)
	{
		Transform().moveTo(vec);
	}
	
	
	/**
	 * Returns the x-coördinate of the {@code IMovable2D}.
	 * 
	 * @return  the movable's x-coördinate
	 */
	public default float getX()
	{
		return Transform().getX();
	}
	
	/**
	 * Returns the y-coördinate of the {@code IMovable2D}.
	 * 
	 * @return  the movable's y-coördinate
	 */
	public default float getY()
	{
		return Transform().getY();
	}
}