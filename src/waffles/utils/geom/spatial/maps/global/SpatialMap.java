package waffles.utils.geom.spatial.maps.global;

import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.data.Spatial;
import waffles.utils.geom.spatial.maps.data.spin.Spin;
import waffles.utils.geom.spatial.maps.data.unary.Rotated;
import waffles.utils.geom.utilities.tform.lazy.LazyIdentity;

/**
 * A {@code SpatialMap} defines a global map with affine-oriented spatial data.
 * It delegates its data access to a {@code Spatial} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AxialMap
 * @see Spatial
 */
@FunctionalInterface
public interface SpatialMap extends AxialMap, Spatial
{
	/**
	 * A {@code Mutable SpatialMap} can manipulate its own state.
	 *
	 * @author Waffles
	 * @since 29 Sep 2025
	 * @version 1.1
	 *
	 * 
	 * @see AxialMap
	 * @see SpatialMap
	 * @see Spatial
	 */
	public static interface Mutable extends SpatialMap, Spatial.Mutable, AxialMap.Mutable
	{
		@Override
		public default void setSpin(Spin s)
		{
			Rotated.Mutable src = Source().Mutator();
			if(src != null)
			{
				src.setSpin(s);
				setChanged();
			}
		}
	}
	
	
	/**
	 * Returns the source of the {@code SpatialMap}.
	 * 
	 * @return  a data source
	 * 
	 * 
	 * @see Spatial
	 */
	@Override
	public abstract Spatial Source();

	
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
	public default Spin Spin()
	{
		return Source().Spin();
	}
}