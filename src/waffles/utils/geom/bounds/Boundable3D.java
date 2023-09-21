package waffles.utils.geom.bounds;

import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Boundable} object defines a three-dimensional boundary after applying a {@code GlobalMap}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 */
@FunctionalInterface
public interface Boundable3D extends Boundable, Bounded3D
{	
	@Override
	public abstract Bounds3D Bounds(GlobalMap map);
	
	@Override
	public default Bounds3D Bounds()
	{
		return (Bounds3D) Boundable.super.Bounds();
	}
}