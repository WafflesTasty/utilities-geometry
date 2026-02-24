package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical3D;
import waffles.utils.geom.shapes.Geometry3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Aligned3D;

/**
 * An {@code AxisAligned3D} defines a three-dimensional {@code Aligned Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical3D
 * @see AxisAligned
 * @see Aligned3D
 */
public interface AxisAligned3D extends AxisAligned, Aligned3D, Geometrical3D
{
	@Override
	public default Point Origin()
	{
		return AxisAligned.super.Origin();
	}
	
	@Override
	public abstract Geometry3D Shape();
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}