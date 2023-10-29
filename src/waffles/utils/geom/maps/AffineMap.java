package waffles.utils.geom.maps;

import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.data.unary.Rotated;

/**
 * An {@code AffineMap} interface defines a global map with spatial data.
 * It delegates its data access to a {@code Spatial} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see AlignedMap
 * @see Spatial
 */
public interface AffineMap extends AlignedMap, Spatial.Mutable
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