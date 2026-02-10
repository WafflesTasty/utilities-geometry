package waffles.utils.geom.owners;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.owners.collision.CLSGeometrical;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.Bounds.Factory;

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
 * @see Geometry
 */
public interface Geometrical extends Geometry
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
		LinearMap map = Transform();
		Bounds bnd = Shape().Bounds();
		Factory fct = bnd.Factory();
		return fct.create(map);
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