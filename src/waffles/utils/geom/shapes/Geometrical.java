package waffles.utils.geom.shapes;

import waffles.utils._todo.geometric.CLSGeometrical;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;

/**
 * A {@code Geometrical} object defines a {@code Collidable} with a transformation and a shape.
 * Its shape is an instance of {@code Geometry} and is assumed to be in unit space coordinates,
 * while its transform is an instance of {@code LinearMap} and is assumed to be in world space
 * coordinates. Finally, its {@code Bounds} are also assumed to be in world space, and are
 * computed as a transform of the bounds of the object's shape.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.2
 *
 *
 * @see Collidable
 * @see Bounded
 */
public interface Geometrical extends Bounded, Collidable
{
	/**
	 * Returns the shape of the {@code Geometrical}.
	 *
	 * @return  a geometric shape
	 *
	 *
	 * @see Geometry
	 */
	public abstract Geometry Shape();

	/**
	 * Returns the transform of the {@code Geometrical}.
	 *
	 * @return  a linear transform
	 *
	 *
	 * @see LinearMap
	 */
	public abstract LinearMap Transform();


	@Override
	public default Bounds Bounds()
	{
		return Shape().Bounds(Transform());
	}

	@Override
	public default Collision Collision()
	{
		return new CLSGeometrical(this);
	}

	@Override
	public default int Dimension()
	{
		return Shape().Dimension();
	}
}