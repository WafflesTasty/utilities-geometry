package waffles.utils.geom.spaces.index;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.index.tiled.Tiled;
import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.sets.indexed.MutableIndex;

/**
 * An {@code IndexSpace} defines an {@code IndexedSet} as a {@code Space}.
 * Each tile in the index
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <T>  a tile type
 * @see IndexedSet
 * @see Space
 */
public interface IndexSpace<O, T> extends IndexedSet<T>, Space<O>
{
	/**
	 * An {@code IndexSpace.Mutable} defines a {@code Mutable IndexSpace}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <T>  a tile type
	 * @see MutableIndex
	 * @see IndexSpace
	 */
	public static interface Mutable<O, T> extends IndexSpace<O, T>, MutableIndex<T>
	{
		// NOT APPLICABLE
	}

		
	/**
	 * Defines tile size in the {@code IndexSpace}.
	 *
	 * @return  a tile size
	 */
	public abstract Arrow TileSize();
	
	/**
	 * Returns a {@code Tiled} in the {@code IndexSpace}.
	 * 
	 * @param crd  a tile coordinate
	 * @return  a tiled
	 */
	public abstract Tiled Tile(int... crd);
	/**
	 * Returns a coordinate in the {@code IndexSpace}.
	 *
	 * @param v  a point vector
	 * @return   a space index
	 *
	 *
	 * @see Vector
	 */
	public default int[] indexOf(Vector v)
	{
		return indexOf(new Point(v, 1f));
	}

	/**
	 * Returns a coordinate in the {@code IndexSpace}.
	 *
	 * @param p  an affine point
	 * @return   a space index
	 *
	 *
	 * @see Point
	 */
	public default int[] indexOf(Point p)
	{
		Arrow s = TileSize();
		
		int[] min = Minimum();
		int[] max = Maximum();

		int[] crds = new int[Order()];
		for(int k = 0; k < Order(); k++)
		{
			crds[k] = (int) (p.aff(k) / s.aff(k));
			if(crds[k] < min[k] || max[k] < crds[k])
			{
				return null;
			}
		}

		return crds;
	}

		
	@Override
	public default int Dimension()
	{
		return Order();
	}
}