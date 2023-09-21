package waffles.utils.geom.collidable;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collision.CLSGeometrical;
import waffles.utils.geom.maps.Transformable;

/**
 * A {@code Geometrical} object defines an object with an n-dimensional geometric shape.
 * It defines its own global transformation, and admits collision detection.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Transformable
 * @see Collidable
 * @see Bounded
 */
public interface Geometrical extends Bounded, Collidable, Transformable
{
	/**
	 * Returns the shape of the {@code Geometrical}.
	 * 
	 * @return  an object shape
	 * 
	 * 
	 * @see Geometry
	 */
	public abstract Geometry Shape();

	
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