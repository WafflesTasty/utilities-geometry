package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.maps.data.Watcher;
import waffles.utils.geom.spatial.owners.Projectable;

/**
 * A {@code Viewpoint} object defines a transformable viewpoint in an n-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.1
 *
 *
 * @see Projectable
 * @see Adjusted
 * @see Watcher
 */
public interface Viewpoint extends Adjusted, Projectable, Watcher
{
	@Override
	public abstract Watcher Transform();
}