package waffles.utils.geom.spatial.bounds;

import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geomold.collidable.axial.cuboid.Cuboid;
import waffles.utils.geomold.collidable.axial.spheroid.Sphere;

/**
 * The {@code Bounds} interface defines {@code Bounds} in three-dimensional space.
 *
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 *
 *
 * @see Bounds
 */
public interface Bounds3D extends Bounds
{
	/**
	 * Returns an origin x of the {@code Bounds3D}.
	 *
	 * @return  an origin x
	 */
	public default float X()
	{
		return Origin().X();
	}

	/**
	 * Returns an origin y of the {@code Bounds3D}.
	 *
	 * @return  an origin y
	 */
	public default float Y()
	{
		return Origin().Y();
	}

	/**
	 * Returns an origin z of the {@code Bounds3D}.
	 *
	 * @return  an origin z
	 */
	public default float Z()
	{
		return Origin().Z();
	}


	/**
	 * Returns the width of the {@code Bounds3D}.
	 *
	 * @return  a width scale
	 */
	public default float Width()
	{
		return Scale().X();
	}

	/**
	 * Returns the height of the {@code Bounds3D}.
	 *
	 * @return  a height scale
	 */
	public default float Height()
	{
		return Scale().Y();
	}

	/**
	 * Returns the depth of the {@code Bounds3D}.
	 *
	 * @return  a depth scale
	 */
	public default float Depth()
	{
		return Scale().Z();
	}


	/**
	 * Returns the minimum x of the {@code Bounds3D}.
	 *
	 * @return  a minimum x
	 */
	public default float XMin()
	{
		return Minimum().X();
	}

	/**
	 * Returns the maximum x of the {@code Bounds3D}.
	 *
	 * @return  a maximum x
	 */
	public default float XMax()
	{
		return Maximum().X();
	}

	/**
	 * Returns the minimum y of the {@code Bounds3D}.
	 *
	 * @return  a minimum y
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}

	/**
	 * Returns the maximum y of the {@code Bounds3D}.
	 *
	 * @return  a maximum y
	 */
	public default float YMax()
	{
		return Maximum().Y();
	}

	/**
	 * Returns the minimum z of the {@code Bounds3D}.
	 *
	 * @return  a minimum z
	 */
	public default float ZMin()
	{
		return Minimum().Z();
	}

	/**
	 * Returns the maximum z of the {@code Bounds3D}.
	 *
	 * @return  a maximum z
	 */
	public default float ZMax()
	{
		return Maximum().Z();
	}


	@Override
	public default Vector3 Minimum()
	{
		return (Vector3) Bounds.super.Minimum();
	}

	@Override
	public default Vector3 Maximum()
	{
		return (Vector3) Bounds.super.Maximum();
	}

	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Bounds.super.Origin();
	}

	@Override
	public default Vector3 Scale()
	{
		return (Vector3) Bounds.super.Scale();
	}

	@Override
	public default Cuboid Box()
	{
		return (Cuboid) Bounds.super.Box();
	}

	@Override
	public default Sphere Orb()
	{
		return (Sphere) Bounds.super.Orb();
	}
}