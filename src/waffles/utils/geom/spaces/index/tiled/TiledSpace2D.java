package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space2D;
import waffles.utils.geom.spatial.maps.data.Axial2D;
import waffles.utils.sets.utilities.indexed.coords.Coordination2D;

/**
 * A {@code TiledSpace2D} defines a two-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see Coordination2D
 * @see TiledSpace
 * @see Space2D
 * @see Tiled2D
 * @see Axial2D
 */
public interface TiledSpace2D<T extends Tiled2D> extends TiledSpace<T>, Space2D<T>, Coordination2D, Axial2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}

	@Override
	public default Point Origin()
	{
		return TiledSpace.super.Origin();
	}
	
	@Override
	public default Arrow Scale()
	{
		return TiledSpace.super.Scale();
	}
}