package waffles.utils.geom.spatial.maps.global;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.data.unary.Projected;
import waffles.utils.geom.utilities.matrix.LazyIdentity;

/**
 * A {@code WatcherMap} defines a global map with projective spatial data.
 * It delegates its data access to a {@code Watcher} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see SpatialMap
 * @see Watcher
 */
@FunctionalInterface
public interface WatcherMap extends SpatialMap, Watcher
{
	/**
	 * A {@code Mutable WatcherMap} can manipulate its own state.
	 *
	 * @author Waffles
	 * @since 29 Sep 2025
	 * @version 1.1
	 *
	 * 
	 * @see SpatialMap
	 * @see WatcherMap
	 * @see Watcher
	 */
	public static interface Mutable extends WatcherMap, Watcher.Mutable, SpatialMap.Mutable
	{		
		@Override
		public default void setOculus(Vector o)
		{
			Projected.Mutable src = Source().Mutator();
			if(src != null)
			{
				src.setOculus(o);
				setChanged();
			}
		}
	}
	
	
	/**
	 * Returns the source of the {@code WatcherMap}.
	 * 
	 * @return  a data source
	 * 
	 * 
	 * @see Watcher
	 */
	@Override
	public abstract Watcher Source();

	
	@Override
	public default LazyMatrix UTW()
	{
		return new LazyIdentity();
	}
	
	@Override
	public default LazyMatrix WTU()
	{
		return new LazyIdentity();
	}
	
	@Override
	public default Vector Oculus()
	{
		return Source().Oculus();
	}
}