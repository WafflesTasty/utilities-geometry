package waffles.utils.geom.spaces.index;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.geom.utilities.Shapeable;
import waffles.utils.sets.indexed.IndexedSet;
import waffles.utils.tools.collections.Iterables;

/**
 * An {@code IndexSpace} partitions n-dimensional space through an {@code IndexedSet}.
 *
 * @author Waffles
 * @since 28 Feb 2020
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see IndexedSet
 * @see Shapeable
 * @see Space
 */
public interface IndexSpace<O> extends IndexedSet<O>, Space<O>, Shapeable
{		
	/**
	 * Returns a coordinate in the {@code IndexSpace}.
	 * 
	 * @param v  a space vector
	 * @return  a tile coordinate
	 * 
	 * 
	 * @see Vector
	 */
	public default int[] indexOf(Vector v)
	{
		int[] dims = Dimensions();
		int[] coords = new int[Order()];
		for(int i = 0; i < Order(); i++)
		{
			coords[i] = (int) (v.get(i) / TileSize());
			if(coords[i] < 0 || dims[i] <= coords[i])
			{
				return null;
			}
		}

		return coords;
	}
	
	/**
	 * Returns the tile size of the {@code IndexSpace}.
	 * 
	 * @return  a tile size
	 */
	public abstract float TileSize();
	
	
	@Override
	public default Iterable<O> query(Point p)
	{
		O obj = get(indexOf(p.Generator()));
		if(obj != null)
		{
			return Iterables.singleton(obj);
		}
		
		return Iterables.empty();
	}
		
	@Override
	public default HyperCuboid Shape()
	{
		int[] dims = Dimensions();

		Vector s = Vectors.create(Order());
		for(int i = 0; i < Order(); i++)
		{
			s.set(dims[i] * TileSize(), i);
		}
		
		Vector c = s.times(0.5f);
		return Geometries.Cuboid(c, s);
	}
}