package waffles.utils.geom.spaces.index;

import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.spaces.index.tiles.Tiled;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.utilities.Iterables;

/**
 * A {@code TiledSpace} defines a spatial index with individual mutable tiles.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 * 
 * @param <T>  a  tile type
 * @see IndexSpace
 * @see MutableIndex
 * @see Tiled
 */
public interface TiledSpace<T extends Tiled> extends IndexSpace<T>, MutableIndex<T>
{	
	/**
	 * Iterates over all tiles in the {@code TiledSpace}.
	 * 
	 * @return  a tile iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<T> Tiles()
	{
		return get(Minimum(), Maximum());
	}
	
	/**
	 * Iterates over a section of tiles from the {@code TiledSpace}.
	 * 
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * @return  a tile iterable
	 * 
	 * 
	 * @see Iterable
	 */
	public default Iterable<T> get(int[] min, int[] max)
	{
		return Iterables.index(this, min, max);
	}
	
	@Override
	public default Iterable<T> query(ICuboid c)
	{
		int[] min = indexOf(c.Bounds().Minimum());
		int[] max = indexOf(c.Bounds().Maximum());
		return get(min, max);
	}
}