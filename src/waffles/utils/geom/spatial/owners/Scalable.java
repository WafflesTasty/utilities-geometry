package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Scaled;
import waffles.utils.geom.utilities.Transformable;

/**
 * A {@code Scalable} object can be scaled in an n-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 *
 *
 * @see Transformable
 * @see Scaled
 */
public interface Scalable extends Scaled, Transformable
{
	/**
	 * Scales the {@code Scaled} to a new size.
	 *
	 * @param s  a scale point
	 *
	 *
	 * @see Point
	 */
	public default void scaleTo(Point s)
	{
		Transform().setScale(s);
	}

	/**
	 * Scales the {@code Scaled} to a new size.
	 *
	 * @param s  a scale vector
	 *
	 *
	 * @see Vector
	 */
	public default void scaleTo(Vector s)
	{
		scaleTo(new Arrow(s));
	}
	
	/**
	 * Scales the {@code Scaled} for a given factor.
	 *
	 * @param s  a scale factor
	 *
	 *
	 * @see Vector
	 */
	public default void scaleFor(Vector s)
	{
		scaleFor(new Arrow(s));
	}
	
	/**
	 * Scales the {@code Scaled} for a given factor.
	 *
	 * @param s  a scale point
	 *
	 *
	 * @see Point
	 */
	public default void scaleFor(Point s)
	{
		scaleTo(Scale().hadamard(s));
	}
	

	@Override
	public abstract Scaled.Mutable Transform();

	@Override
	public default Arrow Scale()
	{
		return Transform().Scale();
	}
}