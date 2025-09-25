package waffles.utils.geom.collidable;

import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.bounds.Boundable3D;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * The {@code Geometry3D} interface is the base for bounded three-dimensional shapes.
 * 
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see Collideable3D
 * @see Boundable3D
 * @see Geometry
 */
public interface Geometry3D extends Geometry, Collideable3D, Boundable3D
{			
	@Override
	public abstract Bounds3D Bounds(GlobalMap map);
}