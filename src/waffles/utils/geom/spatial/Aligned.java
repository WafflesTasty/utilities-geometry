package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.owners.Movable;
import waffles.utils.geom.spatial.owners.Scalable;

/**
 * An {@code Aligned} object can be axis-aligned in an n-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.1
 * 
 * 
 * @see Scalable
 * @see Movable
 * @see Axial
 */
public interface Aligned extends Movable, Scalable, Axial
{
	@Override
	public abstract Axial.Mutable Transform();
}