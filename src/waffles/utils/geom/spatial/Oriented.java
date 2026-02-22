package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Radial;
import waffles.utils.geom.spatial.owners.Movable;
import waffles.utils.geom.spatial.owners.Rotatable;

/**
 * An {@code Oriented} object can be affine-oriented in an n-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.1
 *
 *
 * @see Rotatable
 * @see Movable
 * @see Radial
 */
public interface Oriented extends Movable, Rotatable, Radial
{
	@Override
	public abstract Radial.Mutable Transform();
}