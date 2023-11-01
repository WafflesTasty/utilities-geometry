package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.types.Projectable;

/**
 * A {@code Projector} object observes a projection of an n-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable
 * @see Adjustable
 */
public interface Projector extends Adjustable, Projectable
{
	@Override
	public abstract Watcher.Mutable Transform();
}