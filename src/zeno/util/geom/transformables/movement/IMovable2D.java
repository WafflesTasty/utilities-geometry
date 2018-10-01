package zeno.util.geom.transformables.movement;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation2D;

/**
 * The {@code IMovable2D} interface defines an object
 * capable of being moved in 2D space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
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
	 * 
	 * 
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
		if(x != 0f && y != 0f)
		{
			Vector2 o = Transform().Origin();
			moveTo(x + o.X(), y + o.Y());
		}
	}
	
	/**
	 * Moves the {@code IMovable2D} for a specified distance.
	 * 
	 * @param vec  a distance to move for
	 * 
	 * 
	 * @see Vector2
	 */
	public default void moveFor(Vector2 vec)
	{
		moveFor(vec.X(), vec.Y());
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
	 * 
	 * 
	 * @see Vector2
	 */
	public default void moveTo(Vector2 vec)
	{
		Transform().setOrigin(vec);
	}
}