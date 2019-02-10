package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.tools.Floats;

/**
 * The {@code IMovable3D} interface defines an object
 * capable of being moved through a 3D affine space.
 *
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see IMovable
 */
public interface IMovable3D extends IMovable
{	
	/**
	 * Moves the {@code IMovable3D} for a specified distance.
	 * 
	 * @param x  a distance x-coördinate
	 * @param y  a distance y-coördinate
	 * @param z  a distance z-coördinate
	 */
	public default void moveFor(float x, float y, float z)
	{
		if(!Floats.isZero(x, 1)
		&& !Floats.isZero(y, 1)
		&& !Floats.isZero(z, 1))
		{
			moveFor(new Vector3(x, y, z));
		}
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
	
	
	@Override
	public abstract Vector3 Origin();

	/**
	 * Returns the x-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  an x-coördinate
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns the y-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  an y-coördinate
	 */
	public default float Y()
	{
		return Origin().Y();
	}
	
	/**
	 * Returns the z-coördinate of the {@code IMovable3D}.
	 * 
	 * @return  an z-coördinate
	 */
	public default float Z()
	{
		return Origin().Z();
	}
}