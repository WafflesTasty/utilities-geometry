package waffles.utils.geom.collidable;

import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.bounds.Boundable2D;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * The {@code Geometry2D} interface is the base for bounded two-dimensional shapes.
 * 
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see Collideable2D
 * @see Boundable2D
 * @see Geometry
 */
public interface Geometry2D extends Geometry, Collideable2D, Boundable2D
{			
	@Override
	public abstract Bounds2D Bounds(GlobalMap map);
}