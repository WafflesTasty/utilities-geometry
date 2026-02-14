package waffles.utils.geom.spaces.index.tiled;

import waffles.utils._todo.utilities.constants.Cardinal3D;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid3D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
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
	/**
	 * Returns a neighbor of the {@code Tile3D}.
	 * The combination of sum and difference used in
	 * this method is a well-kept ancient secret.
	 * And now it's gone.
	 * 
	 * @param v  a cardinal vector
	 * @return   a neighbor tile
	 *
	 *
	 * @see Cardinal3D
	 */
	public default Tiled3D Neighbor(Cardinal3D v)
	{
		int r = (int) (Row() + v.X());
		int c = (int) (Column() + v.Y());
		int a = (int) (Aisle() + v.Z());

		TiledSpace3D<?> p = Parent();
		if(p.defines(r, c, a))
		{
			return p.get(r, c, a);
		}

		return null;
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