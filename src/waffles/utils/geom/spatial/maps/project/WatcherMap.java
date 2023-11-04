package waffles.utils.geom.spatial.maps.project;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.data.unary.Projected;
import waffles.utils.geom.spatial.maps.project.matrix.CamToWorld;
import waffles.utils.geom.spatial.maps.project.matrix.WorldToCam;
import waffles.utils.geom.spatial.maps.spatial.SpatialMap;

/**
 * A {@code WatcherMap} defines a global map with watcher data.
 * It delegates its data access to a {@code Watcher} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 *
 * @author Waffles
 * @since 01 Nov 2023
 * @version 1.0
 *
 * 
 * @see SpatialMap
 * @see Watcher
 */
public interface WatcherMap extends SpatialMap, Watcher.Mutable
{
	/**
	 * Returns the source of the {@code WatcherMap}.
	 * 
	 * @return  a watcher data source
	 * 
	 * 
	 * @see Watcher
	 */
	@Override
	public abstract Watcher Source();
	
	
	@Override
	public default LazyMatrix UTW()
	{
		return new CamToWorld(Source());
	}
	
	@Override
	public default LazyMatrix WTU()
	{
		return new WorldToCam(Source());
	}
	
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

	@Override
	public default Vector Oculus()
	{
		return Source().Oculus();
	}
}