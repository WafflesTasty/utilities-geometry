package waffles.utils.geom.collidable;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collision.CLSGeometrical;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Shapeable;

/**
 * A {@code Geometrical} object defines a collidable with an arbitrary n-dimensional geometric shape.
 * It defines its own global transformation, and admits rudimentary collision detection.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.2
 * 
 * 
 * @see Collidable
 * @see Shapeable
 */
public interface Geometrical extends Collidable, Shapeable
{
	/**
	 * Returns the transform of the {@code Geometrical}.
	 * 
	 * @return  a global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public abstract GlobalMap Transform();

	
	@Override
	public default Bounds Bounds()
	{
		return Shape().Bounds(Transform());
	}

	@Override
	public default Collision Collisions()
	{
		return new CLSGeometrical(this);
	}
			
	@Override
	public default int Dimension()
	{
		return Shape().Dimension();
	}
}