package waffles.utils.geom.spaces.index.tiles.array;

import waffles.utils.geom.spaces.index.TiledSpace3D;
import waffles.utils.geom.spaces.index.tiles.Tiled3D;

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
	 * @param rows  a row count
	 * @param cols  a column count
	 * @param aisl  an aisle count
	 */
	public TiledArray3D(int rows, int cols, int aisl)
	{
		super(rows, cols, aisl);
	}
}