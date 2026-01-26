package waffles.utils.geom.spatial.bounds;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid3D;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Sphere;

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
		return Origin().aff(0);
	}

	/**
	 * Returns an origin y of the {@code Bounds3D}.
	 *
	 * @return  an origin y
	 */
	public default float Y()
	{
		return Origin().aff(1);
	}

	/**
	 * Returns an origin z of the {@code Bounds3D}.
	 *
	 * @return  an origin z
	 */
	public default float Z()
	{
		return Origin().aff(2);
	}


	/**
	 * Returns the width of the {@code Bounds3D}.
	 *
	 * @return  a width scale
	 */
	public default float Width()
	{
		return Scale().aff(0);
	}

	/**
	 * Returns the height of the {@code Bounds3D}.
	 *
	 * @return  a height scale
	 */
	public default float Height()
	{
		return Scale().aff(1);
	}

	/**
	 * Returns the depth of the {@code Bounds3D}.
	 *
	 * @return  a depth scale
	 */
	public default float Depth()
	{
		return Scale().aff(2);
	}


	/**
	 * Returns the minimum x of the {@code Bounds3D}.
	 *
	 * @return  a minimum x
	 */
	public default float XMin()
	{
		return Minimum().aff(0);
	}

	/**
	 * Returns the maximum x of the {@code Bounds3D}.
	 *
	 * @return  a maximum x
	 */
	public default float XMax()
	{
		return Maximum().aff(0);
	}

	/**
	 * Returns the minimum y of the {@code Bounds3D}.
	 *
	 * @return  a minimum y
	 */
	public default float YMin()
	{
		return Minimum().aff(1);
	}

	/**
	 * Returns the maximum y of the {@code Bounds3D}.
	 *
	 * @return  a maximum y
	 */
	public default float YMax()
	{
		return Maximum().aff(1);
	}

	/**
	 * Returns the minimum z of the {@code Bounds3D}.
	 *
	 * @return  a minimum z
	 */
	public default float ZMin()
	{
		return Minimum().aff(2);
	}

	/**
	 * Returns the maximum z of the {@code Bounds3D}.
	 *
	 * @return  a maximum z
	 */
	public default float ZMax()
	{
		return Maximum().aff(2);
	}


	@Override
	public default HyperCuboid3D Box()
	{
		return (HyperCuboid3D) Bounds.super.Box();
	}

	@Override
	public default Sphere Orb()
	{
		return (Sphere) Bounds.super.Orb();
	}
}