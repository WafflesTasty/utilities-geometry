package waffles.utils.geom.spatial.owners.geom;

import waffles.utils.geom.shapes.Geometrical3D;
import waffles.utils.geom.shapes.convex.axial.AxialSet3D;
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
	public abstract AxialSet3D Shape();
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}