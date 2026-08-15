package waffles.utils.geom.spaces.index.tiled;

import java.util.Iterator;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.index.IndexSpace;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.maps.data.Axial;
import waffles.utils.sets.utilities.indexed.iterators.IndexValues;
import waffles.utils.tools.collections.iterators.EmptyIterator;
import waffles.utils.tools.collections.iterators.SingleIterator;

/**
 * A {@code TiledSpace} defines an {@code IndexSpace} with individual {@code Tiled} objects.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 *
 * @param <T>  a  tile type
 * @see IndexSpace
 * @see Tiled
 * @see Axial
 */
public interface TiledSpace<T extends Tiled> extends Axial, IndexSpace.Mutable<T, T>
{
	/**
	 * A {@code TiledSpace.Query} defines queries for a {@code TiledSpace}.
	 *
	 * @author Waffles
	 * @since 17 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <T>  a tile type
	 * @see Space
	 * @see Tiled
	 */
	@FunctionalInterface
	public static interface Query<T extends Tiled> extends Space.Query<T>
	{
		/**
		 * Returns the space of the {@code Query}.
		 * 
		 * @return  a tiled space
		 * 
		 * 
		 * @see TiledSpace
		 */
		public abstract TiledSpace<T> Space();
		
		/**
		 * Iterates over a subset of a {@code TiledSpace}.
		 *
		 * @param min  a minimum index
		 * @param max  a maximum index
		 * @return  a tile iterator
		 *
		 *
		 * @see Iterator
		 */
		public default Iterator<T> between(int[] min, int[] max)
		{
			return new IndexValues<>(Space(), min, max);
		}
		
		
		@Override
		public default Iterator<T> in(HyperCuboid c)
		{
			Bounds bnd = c.Bounds();
			Point x = bnd.Minimum();
			Point y = bnd.Maximum();

			int[] min = Space().indexOf(x);
			int[] max = Space().indexOf(y);
			
			return between(min, max);
		}
		
		@Override
		public default Iterator<T> at(Point p)
		{
			TiledSpace<T> s = Space();
			int[] crds = s.indexOf(p);
			if(crds != null)
			{
				if(s.defines(crds))
				{
					T tile = s.get(crds);
					if(tile != null)
					{
						return new SingleIterator<>(tile);
					}
				}
			}

			return new EmptyIterator<>();
		}
		
		@Override
		public default Iterator<T> All()
		{
			int[] min = Space().Minimum();
			int[] max = Space().Maximum();
			
			return between(min, max);
		}
	}
	
	
	/**
	 * Iterates all tiles in the {@code TiledSpace}.
	 *
	 * @return  a tile iterable
	 *
	 *
	 * @see Iterable
	 */
	public default Iterable<T> Tiles()
	{
		return () -> Query().All();
	}

	
	@Override
	public default Tiled Tile(int... crd)
	{
		return get(crd);
	}
	
	@Override
	public default Query<T> Query()
	{
		return () -> this;
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