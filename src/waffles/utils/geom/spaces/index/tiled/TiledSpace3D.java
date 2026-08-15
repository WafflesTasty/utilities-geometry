package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.index.IndexSpace3D;
import waffles.utils.geom.spatial.maps.data.Axial3D;

/**
 * A {@code TiledSpace3D} defines a three-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see IndexSpace3D
 * @see TiledSpace
 * @see Tiled3D
 * @see Axial3D
 */
public interface TiledSpace3D<T extends Tiled3D> extends TiledSpace<T>, IndexSpace3D.Mutable<T, T>, Axial3D
{
	@Override
	public default Tiled3D Tile(int... crd)
	{
		return get(crd);
	}
	
	@Override
	public default int Dimension()
	{
		return 3;
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