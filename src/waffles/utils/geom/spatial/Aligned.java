package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.types.Movable;
import waffles.utils.geom.spatial.types.Scalable;

/**
 * An {@code Aligned} object can be axis-aligned in an n-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Movable
 * @see Scalable
 */
public interface Aligned extends Movable, Scalable
{
	@Override
	public abstract Axial.Mutable Transform();
}