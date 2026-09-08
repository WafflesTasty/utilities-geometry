package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.utilities.Transformable;

/**
 * An {@code Movable} object can be moved around an n-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 *
 *
 * @see Transformable
 * @see Positioned
 */
public interface Movable extends Positioned, Transformable
{
	/**
	 * Moves the {@code Movable} to a new origin.
	 *
	 * @param o  an origin point
	 *
	 *
	 * @see Point
	 */
	public default void moveTo(Point o)
	{
		Positioned.Mutable m = Transform().Mutator();
		if(m != null)
		{
			m.setOrigin(o);
		}
	}
	
	/**
	 * Moves the {@code Movable} to a new origin.
	 *
	 * @param o  an origin vector
	 *
	 *
	 * @see Vector
	 */
	public default void moveTo(Vector o)
	{
		moveTo(new Point(o, 1f));
	}
	
	/**
	 * Moves the {@code Movable} for a given distance.
	 *
	 * @param d  a distance vector
	 *
	 *
	 * @see Vector
	 */
	public default void moveFor(Vector d)
	{
		moveTo(Origin().plus(d));
	}
	
	/**
	 * Moves the {@code Movable} for a given distance.
	 *
	 * @param d  a distance vector
	 *
	 *
	 * @see Point
	 */
	public default void moveFor(Point d)
	{
		moveTo(Origin().plus(d));
	}


	@Override
	public abstract Positioned Transform();

	@Override
	public default Point Origin()
	{
		return Transform().Origin();
	}
}