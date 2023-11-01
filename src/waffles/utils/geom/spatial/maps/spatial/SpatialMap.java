package waffles.utils.geom.spatial.maps.spatial;

import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.data.unary.Rotated;
import waffles.utils.geom.spatial.maps.axial.AxialMap;

/**
 * A {@code SpatialMap} defines a global map with spatial data.
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
public interface SpatialMap extends AxialMap, Spatial.Mutable
{
	/**
	 * Returns the source of the {@code AffineMap}.
	 * 
	 * @return  a spatial data source
	 * 
	 * 
	 * @see Spatial
	 */
	@Override
	public abstract Spatial Source();

		
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
	
	@Override
	public default Spin Spin()
	{
		return Source().Spin();
	}
}