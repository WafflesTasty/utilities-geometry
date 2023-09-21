package waffles.utils.geom.maps;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.spin.Spin;

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
 * @see GlobalMap
 * @see Spatial
 */
public interface AffineMap extends GlobalMap, Spatial
{
	/**
	 * Returns the source of the {@code AffineMap}.
	 * 
	 * @return  a spatial data source
	 * 
	 * 
	 * @see Spatial
	 */
	public abstract Spatial Source();

	
	@Override
	public default void setOrigin(Vector o)
	{
		Source().setOrigin(o);
		setChanged();
	}
	
	@Override
	public default void setSize(Vector s)
	{
		Source().setSize(s);
		setChanged();
	}
	
	@Override
	public default void setSpin(Spin s)
	{
		Source().setSpin(s);
		setChanged();
	}
	
	
	@Override
	public default Vector Origin()
	{
		return Source().Origin();
	}
	
	@Override
	public default Vector Size()
	{
		return Source().Size();
	}
	
	@Override
	public default Spin Spin()
	{
		return Source().Spin();
	}
}