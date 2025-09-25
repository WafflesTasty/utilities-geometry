package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.types.Rotatable;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Adjustable} object can be fully transformed in an n-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.1
 * 
 * 
 * @see Rotatable
 * @see Aligned
 * @see Spatial
 */
public interface Adjustable extends Aligned, Rotatable, Spatial
{
	@Override
	public abstract Spatial.Mutable Transform();
	
	/**
	 * Moves the {@code Adjustable} for a given distance.
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