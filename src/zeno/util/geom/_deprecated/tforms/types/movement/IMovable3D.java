package zeno.util.geom.tforms.types.movement;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._deprecated.ITransformable;
import zeno.util.geom.tforms.types.ITransformation3D;

/**
 * The {@code IMovable3D} interface defines an object
 * capable of moving in 3D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
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
		moveTo(x + getX(), y + getY(), z + getZ());
	}
	
	/**
	 * Moves the {@code IMovable3D} for a specified distance.
	 * 
	 * @param v  a vector direction to move
	 * @param dist  a distance to move
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
	 * @see Vector3
	 */
	public default void moveFor(Vector3 vec)
	{
		moveTo(vec.X() + getX(), vec.Y() + getY(), vec.Z() + getZ());
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
	 * @see Vector3
	 */
	public default void moveTo(Vector3 vec)
	{
		Transform().moveTo(vec);
	}

	
	/**
	 * Returns the x-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  the movable's x-coördinate
	 */
	public default float getX()
	{
		return Transform().getX();
	}
	
	/**
	 * Returns the y-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  the movable's y-coördinate
	 */
	public default float getY()
	{
		return Transform().getY();
	}
	
	/**
	 * Returns the z-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  the movable's z-coördinate
	 */
	public default float getZ()
	{
		return Transform().getZ();
	}
}