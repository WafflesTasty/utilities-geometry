package waffles.utils.geom.spaces.index.tiled;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.index.IndexSpace;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.utilities.indexed.iterators.IndexValues;
import waffles.utils.tools.collections.iterators.EmptyIterator;
import waffles.utils.tools.collections.iterators.SingleIterator;

/**
 * A {@code TiledSpace} defines a spatial index with individual mutable tiles.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 *
 * @param <T>  a  tile type
 * @see HyperCuboid
 * @see IndexSpace
 * @see Tiled
 */
public interface TiledSpace<T extends Tiled> extends IndexSpace.Mutable<T, T>, HyperCuboid
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
	 * Iterates over a section of the {@code TiledSpace}.
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
		return () -> new IndexValues<>(this, min, max);
	}

	
	@Override
	public default Iterable<T> query(HyperCuboid c)
	{
		Bounds bnd = c.Bounds();
		
		int[] min = indexOf(bnd.Minimum());
		int[] max = indexOf(bnd.Maximum());
		
		return get(min, max);
	}
	
	@Override
	public default Iterable<T> query(Point p)
	{
		int[] crds = indexOf(p);
		if(defines(crds))
		{
			T t = get(crds);
			if(t != null)
			{
				return () -> new SingleIterator<>(t);
			}
		}

		return () -> new EmptyIterator<>();
	}

	
	@Override
	public default int Dimension()
	{
		return Order();
	}
	
	@Override
	public default Point Origin()
	{
		Arrow a = TileSize();
		int[] min = Minimum();
		int[] max = Maximum();
		int ord = Order();
		
		
		Vector o = Vectors.create(ord);
		for(int k = 0; k < ord; k++)
		{
			float v = min[k] + max[k] + 1;
			o.set(a.aff(k) * v / 2, k);
		}
		
		return new Point(o, 1f);
	}
	
	@Override
	public default Arrow Scale()
	{
		int[] dim = Dimensions();
		Arrow a = TileSize();
		int ord = Order();
		

		Vector s = Vectors.create(ord);
		for(int k = 0; k < ord; k++)
		{
			s.set(dim[k] * a.aff(k), k);
		}
		
		return new Arrow(s);
	}
}