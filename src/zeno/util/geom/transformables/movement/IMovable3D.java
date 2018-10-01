package zeno.util.geom.transformables.movement;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation3D;

/**
 * The {@code IMovable3D} interface defines an object
 * capable of moving in 3D space.
 *
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformable
 */
public interface IMovable3D extends ITransformable
{
	@Override
	public abstract ITransformation3D Transform();

	
	/**
	 * Moves the {@code IMovable3D} for a specified distance.
	 * 
	 * @param x  a distance x-coördinate
	 * @param y  a distance y-coördinate
	 * @param z  a distance z-coördinate
	 */
	public default void moveFor(float x, float y, float z)
	{
		if(x != 0f && y != 0f && z != 0f)
		{
			Vector3 o = Transform().Origin();
			moveTo(x + o.X(), y + o.Y(), z + o.Z());
		}
	}
	
	/**
	 * Moves the {@code IMovable3D} for a specified distance.
	 * 
	 * @param v  a vector direction to move
	 * @param dist  a distance to move
	 * 
	 * 
	 * @see Vector3
	 */
	public default void moveFor(Vector3 v, float dist)
	{
		moveFor(v.normalize().times(dist));
	}
	
	/**
	 * Moves the {@code IMovable3D} for a specified distance.
	 * 
	 * @param vec  a distance to move for
	 * 
	 * 
	 * @see Vector3
	 */
	public default void moveFor(Vector3 vec)
	{
		moveFor(vec.X(), vec.Y(), vec.Z());
	}
	
	
	/**
	 * Moves the {@code IMovable3D} to a new origin vector.
	 * 
	 * @param x  a new x-coördinate
	 * @param y  a new y-coördinate
	 * @param z  a new z-coördinate
	 */
	public default void moveTo(float x, float y, float z)
	{
		moveTo(new Vector3(x, y, z));
	}
	
	/**
	 * Moves the {@code IMovable3D} to a new origin vector.
	 * 
	 * @param vec  a new origin vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default void moveTo(Vector3 vec)
	{
		Transform().setOrigin(vec);
	}
}