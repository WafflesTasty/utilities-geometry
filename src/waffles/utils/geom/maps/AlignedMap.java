package waffles.utils.geom.maps;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.Axial;

/**
 * An {@code AlignedMap} interface defines a global map with axis-aligned spatial data.
 * It delegates its data access to an {@code Axial} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 * 
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 * @see Axial
 */
public interface AlignedMap extends GlobalMap, Axial
{
	/**
	 * Returns the source of the {@code AlignedMap}.
	 * 
	 * @return  a spatial data source
	 * 
	 * 
	 * @see Axial
	 */
	public abstract Axial Source();

	
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
	public default Vector Origin()
	{
		return Source().Origin();
	}
	
	@Override
	public default Vector Size()
	{
		return Source().Size();
	}
}