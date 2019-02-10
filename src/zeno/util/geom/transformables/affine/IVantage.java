package zeno.util.geom.transformables.affine;

import zeno.util.tools.Floats;

/**
 * The {@code IVantage} interface defines an object
 * capable of moving and rotating in an affine space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IRotatable
 * @see IMovable
 */
public interface IVantage extends IMovable, IRotatable
{
	/**
	 * Strafes the {@code IVantage2D} for a specified distance.
	 * 
	 * @param i  a basis vector index
	 * @param d  a distance to strafe
	 */
	public default void moveFor(int i, float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Basis().Column(i), d);
		}
	}
}