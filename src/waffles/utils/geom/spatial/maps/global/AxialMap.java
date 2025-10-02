package waffles.utils.geom.spatial.maps.global;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.spatial.data.unary.Scaled;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.matrix.LazyIdentity;

/**
 * An {@code AxialMap} defines a global map with axis-aligned spatial data.
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
@FunctionalInterface
public interface AxialMap extends GlobalMap, Axial
{
	/**
	 * A {@code Mutable AxialMap} can manipulate its own state.
	 *
	 * @author Waffles
	 * @since 29 Sep 2025
	 * @version 1.1
	 *
	 * 
	 * @see AxialMap
	 * @see Axial
	 */
	public static interface Mutable extends AxialMap, Axial.Mutable
	{
		@Override
		public default void setOrigin(Vector o)
		{
			Positioned.Mutable src = Source().Mutator();
			if(src != null)
			{
				src.setOrigin(o);
				setChanged();
			}
		}
		
		@Override
		public default void setScale(Vector s)
		{
			Scaled.Mutable src = Source().Mutator();
			if(src != null)
			{
				src.setScale(s);
				setChanged();
			}
		}
	}
	
	
	/**
	 * Returns the source of the {@code AxialMap}.
	 * 
	 * @return  a data source
	 * 
	 * 
	 * @see Axial
	 */
	public abstract Axial Source();

	
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
	public default Vector Origin()
	{
		return Source().Origin();
	}
	
	@Override
	public default Vector Scale()
	{
		return Source().Scale();
	}
}