package waffles.utils.geom.spaces.index.tiled.array;

import waffles.utils.geom.spaces.index.tiled.Tiled2D;
import waffles.utils.geom.spaces.index.tiled.TiledSpace2D;

/**
 * A {@code TiledArray2D} defines a two-dimensional {@code TiledArray}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TiledSpace2D
 * @see TiledArray
 * @see Tiled2D
 */
public class TiledArray2D<T extends Tiled2D> extends TiledArray<T> implements TiledSpace2D<T>
{
	/**
	 * Creates a new {@code TiledArray2D}.
	 *
	 * @param r  a row count
	 * @param c  a column count
	 */
	public TiledArray2D(int r, int c)
	{
		super(r, c);
	}
}