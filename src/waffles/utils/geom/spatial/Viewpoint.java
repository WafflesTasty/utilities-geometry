package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.data.Watcher;
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
 * @see Adjustable
 * @see Watcher
 */
public interface Viewpoint extends Adjustable, Projectable, Watcher
{
	@Override
	public abstract Watcher.Mutable Transform();
}