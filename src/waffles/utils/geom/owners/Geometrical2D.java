package waffles.utils.geom.owners;

import waffles.utils.geom.shapes.Geometry2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code Geometrical2D} object defines a two-dimensional {@code Geometrical}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 *
 *
 * @see Geometrical
 * @see Geometry2D
 */
public interface Geometrical2D extends Geometrical, Geometry2D
{
	@Override
	public default Bounds2D Bounds()
	{
		return (Bounds2D) Geometrical.super.Bounds();
	}

	@Override
	public abstract Geometry2D Shape();

	@Override
	public default int Dimension()
	{
		return 2;
	}
}