package waffles.utils.geom.bounds;

import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code Boundable} object defines a two-dimensional boundary after applying a {@code GlobalMap}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 */
@FunctionalInterface
public interface Boundable2D extends Boundable, Bounded2D
{	
	@Override
	public abstract Bounds2D Bounds(GlobalMap map);
	
	@Override
	public default Bounds2D Bounds()
	{
		return (Bounds2D) Boundable.super.Bounds();
	}
}