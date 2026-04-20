package waffles.utils.geom.spatial.bounds;

import waffles.utils.geom.shapes.convex.axial.cube.base.Rectangle;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Circle;

/**
 * The {@code Bounds} interface defines {@code Bounds} in two-dimensional space.
 *
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 *
 *
 * @see Bounds
 */
@FunctionalInterface
public interface Bounds2D extends Bounds
{
	/**
	 * Returns the origin x of the {@code Bounds2D}.
	 *
	 * @return  an origin x
	 */
	public default float X()
	{
		return Origin().aff(0);
	}

	/**
	 * Returns the origin y of the {@code Bounds2D}.
	 *
	 * @return  an origin y
	 */
	public default float Y()
	{
		return Origin().aff(1);
	}

	/**
	 * Returns the width of the {@code Bounds2D}.
	 *
	 * @return  a width scale
	 */
	public default float Width()
	{
		return Scale().aff(0);
	}

	/**
	 * Returns the height of the {@code Bounds2D}.
	 *
	 * @return  a height scale
	 */
	public default float Height()
	{
		return Scale().aff(1);
	}


	/**
	 * Returns the minimum x of the {@code Bounds2D}.
	 *
	 * @return  a minimum x
	 */
	public default float XMin()
	{
		return Minimum().aff(0);
	}

	/**
	 * Returns the maximum x of the {@code Bounds2D}.
	 *
	 * @return  a maximum x
	 */
	public default float XMax()
	{
		return Maximum().aff(0);
	}

	/**
	 * Returns the minimum y of the {@code Bounds2D}.
	 *
	 * @return  a minimum y
	 */
	public default float YMin()
	{
		return Minimum().aff(1);
	}

	/**
	 * Returns the maximum y of the {@code Bounds2D}.
	 *
	 * @return  a maximum y
	 */
	public default float YMax()
	{
		return Maximum().aff(1);
	}


	@Override
	public default Rectangle Box()
	{
		return (Rectangle) Bounds.super.Box();
	}

	@Override
	public default Circle Orb()
	{
		return (Circle) Bounds.super.Orb();
	}
}