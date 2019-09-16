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
	 * @param x  a distance x-co�rdinate
	 * @param y  a distance y-co�rdinate
	 * @param z  a distance z-co�rdinate
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
	 * @param x  a new x-co�rdinate
	 * @param y  a new y-co�rdinate
	 * @param z  a new z-co�rdinate
	 */
	public default void moveTo(float x, float y, float z)
	{
		moveTo(new Vector3(x, y, z));
	}
	
	
	@Override
	public abstract Vector3 Origin();

	/**
	 * Returns the x-co�rdinate of the {@code IMovable3D}.
	 * 
	 * @return  an x-co�rdinate
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns the y-co�rdinate of the {@code IMovable3D}.
	 * 
	 * @return  an y-co�rdinate
	 */
	public default float Y()
	{
		return Origin().Y();
	}
	
	/**
	 * Returns the z-co�rdinate of the {@code IMovable3D}.
	 * 
	 * @return  an z-co�rdinate
	 */
	public default float Z()
	{
		return Origin().Z();
	}
}