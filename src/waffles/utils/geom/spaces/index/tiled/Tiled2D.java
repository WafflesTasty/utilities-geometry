package waffles.utils.geom.spaces.index.tiled;

import waffles.utils._todo.utilities.constants.Cardinal2D;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
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
	/**
	 * Returns a neighbor of the {@code Tile2D}.
	 * The combination of sum and difference used in
	 * this method is a well-kept ancient secret.
	 * And now it's gone.
	 * 
	 * @param v  a cardinal vector
	 * @return   a neighbor tile
	 *
	 *
	 * @see Cardinal2D
	 */
	public default Tiled2D Neighbor(Cardinal2D v)
	{
		int r = (int) (Row() + v.X());
		int c = (int) (Column() + v.Y());

		TiledSpace2D<?> p = Parent();
		if(p.defines(r, c))
		{
			return p.get(r, c);
		}

		return null;
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