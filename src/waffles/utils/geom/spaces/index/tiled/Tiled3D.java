package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.chiral.arrows.Cardinal;
import waffles.utils.sets.utilities.indexed.coords.Coordinated3D;

/**
 * A {@code Tiled3D} object can be contained in a {@code TiledSpace3D}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 *
 *
 * @see Coordinated3D
 * @see HyperCuboid3D
 * @see Tiled
 */
public interface Tiled3D extends Tiled, Coordinated3D, HyperCuboid3D
{
	@Override
	public default Tiled3D Neighbor(Cardinal c)
	{
		return (Tiled3D) Tiled.super.Neighbor(c);
	}

	@Override
	public abstract TiledSpace3D<?> Parent();

	
	@Override
	public default int Dimension()
	{
		return 3;
	}
	
	@Override
	public default Point Origin()
	{
		return Tiled.super.Origin();
	}
	
	@Override
	public default Arrow Scale()
	{
		return Tiled.super.Scale();
	}
	
	@Override
	public default int Order()
	{
		return 3;
	}
}