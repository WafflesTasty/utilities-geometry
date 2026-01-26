package waffles.utils.geom.shapes;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds3D;
import waffles.utils.geom.spatial.bounds.owners.Boundable3D;
import waffles.utils.geom.spatial.maps.data.unary.Positioned3D;

/**
 * A {@code Geometry2D} defines a three-dimensional {@code Geometry}.
 *
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 *
 *
 * @see Collideable3D
 * @see Positioned3D
 * @see Boundable3D
 * @see Geometry
 */
public interface Geometry3D extends Geometry, Collideable3D, Boundable3D, Positioned3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
	
	@Override
	public abstract Bounds3D Bounds(LinearMap m);
	
	@Override
	public default Point Origin()
	{
		return Positioned3D.super.Origin();
	}
}