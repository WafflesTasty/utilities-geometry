package waffles.utils.geom.shapes;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;
import waffles.utils.geom.spatial.bounds.owners.Boundable3D;

/**
 * A {@code Geometry2D} defines a three-dimensional {@code Geometry}.
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
	public abstract Bounds3D Bounds(LinearMap map);
}