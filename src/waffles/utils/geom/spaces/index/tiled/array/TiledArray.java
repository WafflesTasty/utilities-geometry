package waffles.utils.geom.spaces.index.tiled.array;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.spaces.index.tiled.Tiled;
import waffles.utils.geom.spaces.index.tiled.TiledSpace;
import waffles.utils.sets.indexed.array.index.ObjectIndex;
import waffles.utils.sets.utilities.indexed.coords.Coordinator;

/**
 * A {@code TiledArray} implements an {@code ObjectIndex} as a {@code TiledSpace}.
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
	/**
	 * The {@code Hints} interface defines settings for a {@code TiledArray}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see Coordinator
	 */
	@FunctionalInterface
	public static interface Hints extends Coordinator
	{
		/**
		 * Returns the {@code Hints} tile size.
		 * 
		 * @return  a tile size
		 * 
		 * 
		 * @see Arrow
		 */
		public default Arrow TileSize()
		{
			return Arrow.create(2f, Order());
		}
	}
	
	
	private Hints hints;

	/**
	 * Creates a new {@code TiledArray}.
	 *
	 * @param h  array hints
	 * 
	 * 
	 * @see Hints
	 */
	public TiledArray(Hints h)
	{
		super(h.Dimensions());
		hints = h;
	}
	
	/**
	 * Creates a new {@code TiledArray}.
	 *
	 * @param dim  an index dimension
	 */
	public TiledArray(int... dim)
	{
		this(() -> dim);
	}
	
	/**
	 * Returns {@code TiledArray} hints.
	 * 
	 * @return  array hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}


	@Override
	public Arrow TileSize()
	{
		return Hints().TileSize();
	}
}