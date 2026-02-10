package waffles.utils.geom.owners;

import waffles.utils.geom.shapes.Geometry3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code Geometrical3D} object defines a three-dimensional {@code Geometrical}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Geometry3D
 */
public interface Geometrical3D extends Geometrical, Geometry3D
{
	@Override
	public default Bounds3D Bounds()
	{
		return (Bounds3D) Geometrical.super.Bounds();
	}
	
	@Override
	public abstract Geometry3D Shape();
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}