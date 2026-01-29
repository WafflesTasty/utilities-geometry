package waffles.utils.geom.spatial.owners.geom;

import waffles.utils.geom.shapes.Geometrical2D;
import waffles.utils.geom.shapes.convex.axial.AxialSet2D;
import waffles.utils.geom.spatial.Aligned2D;

/**
 * An {@code AxisAligned2D} defines a two-dimensional {@code Aligned Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical2D
 * @see AxisAligned
 * @see Aligned2D
 */
public interface AxisAligned2D extends AxisAligned, Aligned2D, Geometrical2D
{
	@Override
	public abstract AxialSet2D Shape();
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}