package waffles.utils.geom.spatial.owners.types;

import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Vantage} object can be moved and rotated in a vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Rotatable
 * @see Movable
 */
public interface Vantage extends Movable, Rotatable
{
	/**
	 * Moves the {@code Vantage} for a given distance.
	 * 
	 * @param i  a basis vector index
	 * @param d  a distance to move
	 */
	public default void moveFor(int i, float d)
	{
		if(!Floats.isZero(d, 1))
		{
			moveFor(Spin().Basis(i), d);
		}
	}
}