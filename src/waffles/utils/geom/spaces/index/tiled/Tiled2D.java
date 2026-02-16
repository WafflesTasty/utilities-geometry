package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.chiral.arrows.Cardinal;
import waffles.utils.sets.utilities.indexed.coords.Coordinated2D;

/**
 * A {@code Tiled2D} object can be contained in a {@code TiledSpace2D}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 *
 *
 * @see Coordinated2D
 * @see HyperCuboid2D
 * @see Tiled
 */
public interface Tiled2D extends Tiled, Coordinated2D, HyperCuboid2D
{
	@Override
	public default Tiled2D Neighbor(Cardinal c)
	{
		return (Tiled2D) Tiled.super.Neighbor(c);
	}

	@Override
	public abstract TiledSpace2D<?> Parent();

	
	@Override
	public default int Dimension()
	{
		return 2;
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
		return 2;
	}
}