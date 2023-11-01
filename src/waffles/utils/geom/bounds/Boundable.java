package waffles.utils.geom.bounds;

import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Transforms;

/**
 * A {@code Boundable} object defines an n-dimensional boundary after applying a {@code GlobalMap}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see Bounded
 */
@FunctionalInterface
public interface Boundable extends Bounded
{	
	/**
	 * Returns the bounds of the {@code Bounded} object.
	 * 
	 * @param map  a transformation map
	 * @return  an object boundary
	 * 
	 * 
	 * @see GlobalMap
	 * @see Bounds
	 */
	public abstract Bounds Bounds(GlobalMap map);
	
	@Override
	public default Bounds Bounds()
	{
		return Bounds(Transforms.identity());
	}
}