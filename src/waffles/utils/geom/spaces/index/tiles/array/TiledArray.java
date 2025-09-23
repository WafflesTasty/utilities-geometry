package waffles.utils.geom.spaces.index.tiles.array;

import waffles.utils.geom.spaces.index.TiledSpace;
import waffles.utils.geom.spaces.index.tiles.Tiled;
import waffles.utils.sets.indexed.array.index.ObjectIndex;

/**
 * A {@code TiledArray} provides a basic implementation of a {@code TiledSpace}
 * by storing its tiles in an {@code ObjectIndex}.
 * 
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.0
 *
 * 
 * @param <T>  a tile type
 * @see ObjectIndex
 * @see TiledSpace
 * @see Tiled
 */
public class TiledArray<T extends Tiled> extends ObjectIndex<T> implements TiledSpace<T>
{		
	private float tSize;
	
	/**
	 * Creates a new {@code TiledArray}.
	 * 
	 * @param dim  an index dimension
	 */
	public TiledArray(int... dim)
	{
		super(dim);
		tSize = 2f;
	}
	
	/**
	 * Changes the tile size of the {@code TiledArray}.
	 * 
	 * @param s  a tile size
	 */
	public void setTileSize(float s)
	{
		tSize = s;
	}
	

	@Override
	public float TileSize()
	{
		return tSize;
	}
}