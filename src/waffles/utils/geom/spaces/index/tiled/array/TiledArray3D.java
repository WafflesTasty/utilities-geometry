package waffles.utils.geom.spaces.index.tiled.array;

import waffles.utils.geom.spaces.index.tiled.Tiled3D;
import waffles.utils.geom.spaces.index.tiled.TiledSpace3D;

/**
 * A {@code TiledArray3D} defines a three-dimensional {@code TiledArray}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TiledSpace3D
 * @see TiledArray
 * @see Tiled3D
 */
public class TiledArray3D<T extends Tiled3D> extends TiledArray<T> implements TiledSpace3D<T>
{
	/**
	 * Creates a new {@code TiledArray3D}.
	 *
	 * @param r  a row count
	 * @param c  a column count
	 * @param a  an aisle count
	 */
	public TiledArray3D(int r, int c, int a)
	{
		super(r, c, a);
	}
}