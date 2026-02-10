package waffles.utils.geom.shapes.convex.axial;

import waffles.utils.geom.shapes.Geometry2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.spatial.bounds.Bounds2D;
import waffles.utils.geom.spatial.maps.data.Axial2D;

/**
 * An {@code AxialSet2D} defines a two-dimensional {@code AxialSet}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Geometry2D
 * @see AxialSet
 * @see Axial2D
 */
public interface AxialSet2D extends AxialSet, Axial2D, Geometry2D
{		
	@Override
	public abstract Bounds2D Bounds();
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
	
	@Override
	public default Arrow Scale()
	{
		return AxialSet.super.Scale();
	}
}