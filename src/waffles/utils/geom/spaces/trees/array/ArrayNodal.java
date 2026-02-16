package waffles.utils.geom.spaces.trees.array;

import java.util.Iterator;

import waffles.utils.geom.spaces.trees.SpatialNodal;
import waffles.utils.sets.IterableSet;
import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * An {@code ArrayNodal} defines a {@code SpatialNodal} with a data array.
 *
 * @author Waffles
 * @since 16 Feb 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @see SpatialNodal
 * @see IterableSet
 */
public interface ArrayNodal<O> extends SpatialNodal, IterableSet<O>
{
	/**
	 * Checks for data in the {@code SpatialNodal}.
	 * 
	 * @return  {@code true} if data is present
	 */
	public default boolean hasData()
	{
		for(Nodal c : Arch().Children())
		{
			ArrayNodal<O> n = (ArrayNodal<O>) c;
			if(n.hasData())
			{
				return true;
			}
		}
		
		return !isEmpty();
	}
	
	/**
	 * Returns the data of the {@code SpatialNodal}.
	 * 
	 * @return  a data set
	 * 
	 * 
	 * @see ArraySet
	 */
	public abstract ArraySet<?, O> Data();
	

	@Override
	public default Iterator<O> iterator()
	{
		return Data().iterator();
	}
	
	@Override
	public default boolean contains(O o)
	{
		return Data().contains(o);
	}
	
	@Override
	public default boolean isEmpty()
	{
		return Data().isEmpty();
	}
	
	@Override
	public default int Count()
	{
		return Data().Count();
	}
}