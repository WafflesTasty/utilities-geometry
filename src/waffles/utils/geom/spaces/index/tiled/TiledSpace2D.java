package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.index.IndexSpace2D;
import waffles.utils.geom.spatial.maps.data.Axial2D;

/**
 * A {@code TiledSpace2D} defines a two-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see IndexSpace2D
 * @see TiledSpace
 * @see Tiled2D
 * @see Axial2D
 */
public interface TiledSpace2D<T extends Tiled2D> extends TiledSpace<T>, IndexSpace2D.Mutable<T, T>, Axial2D
{
	@Override
	public default Tiled2D Tile(int... crd)
	{
		return get(crd);
	}
	
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