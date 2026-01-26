package waffles.utils.geom.shapes.convex.axial;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.Geometry3D;
import waffles.utils.geom.shapes.bounds.convex.axial.BNDAxial3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.spatial.bounds.Bounds3D;
import waffles.utils.geom.spatial.maps.data.Axial3D;

/**
 * An {@code AxialSet3D} defines a three-dimensional {@code AxialSet}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Geometry3D
 * @see AxialSet
 * @see Axial3D
 */
public interface AxialSet3D extends AxialSet, Axial3D, Geometry3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
	
	@Override
	public default Bounds3D Bounds()
	{
		return new BNDAxial3D(this);
	}
	
	@Override
	public default Bounds3D Bounds(LinearMap m)
	{
		return (Bounds3D) AxialSet.super.Bounds(m);
	}
		
	@Override
	public default Arrow Scale()
	{
		return AxialSet.super.Scale();
	}
}