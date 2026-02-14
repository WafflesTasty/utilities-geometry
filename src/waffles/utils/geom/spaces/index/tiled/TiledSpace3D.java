package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space3D;
import waffles.utils.sets.utilities.indexed.coords.Coordinated3D;
import waffles.utils.sets.utilities.indexed.coords.Coordination3D;

/**
 * A {@code TiledSpace3D} defines a three-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see Coordinated3D
 * @see HyperCuboid3D
 * @see TiledSpace
 * @see Space3D
 * @see Tiled3D
 */
public interface TiledSpace3D<T extends Tiled3D> extends TiledSpace<T>, Space3D<T>, Coordination3D, HyperCuboid3D
{
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