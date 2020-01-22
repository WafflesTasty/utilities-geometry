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
	 * Moves the {@code IVantage} for a specified distance.
	 * 
	 * @param i  a basis vector index
	 * @param d  a distance to move
	 */
	public default void moveFor(int i, float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Spin().vector(i), d);
		}
	}
}