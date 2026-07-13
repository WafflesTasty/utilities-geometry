package waffles.utils.geom.spatial.maps.global;

import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.spatial.maps.data.Radial;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.data.unary.Rotated;
import waffles.utils.geom.utilities.tform.lazy.LazyIdentity;
import waffles.utils.geom.spatial.maps.data.spin.Spin;

/**
 * A {@code RadialMap} defines a global map with affine-oriented spatial data.
 * It delegates its data access to a {@code Radial} object and notifies
 * the underlying {@code LazyMatrix} objects of any changes.
 *
 * @author Waffles
 * @since Feb 03, 2020
 * @version 1.0
 *
 *
 * @see GlobalMap
 * @see Radial
 */
@FunctionalInterface
public interface RadialMap extends GlobalMap, Radial
{
	/**
	 * A {@code Mutable RadialMap} can manipulate its own state.
	 *
	 * @author Waffles
	 * @since 29 Sep 2025
	 * @version 1.1
	 *
	 *
	 * @see RadialMap
	 * @see Radial
	 */
	public static interface Mutable extends RadialMap, Radial.Mutable
	{
		@Override
		public default void setOrigin(Point o)
		{
			Positioned.Mutable src = Source().Mutator();
			if(src != null)
			{
				src.setOrigin(o);
				setChanged();
			}
		}

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
	 * Returns the source of the {@code RadialMap}.
	 *
	 * @return  a data source
	 *
	 *
	 * @see Radial
	 */
	public abstract Radial Source();


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
	public default Point Origin()
	{
		return Source().Origin();
	}

	@Override
	public default Spin Spin()
	{
		return Source().Spin();
	}
}