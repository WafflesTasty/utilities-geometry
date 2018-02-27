package zeno.util.geom.dimension.two;

import zeno.util.geom.IGeometry;
import zeno.util.geom.dimension.two.shapes.Rectangle;
import zeno.util.geom.utilities.bounds.Bounded2D;

/**
 * The {@code IGeometry2D} interface is the base for two-dimensional geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry
 * @see ICollideable2D
 * @see Bounded2D
 */
public interface IGeometry2D extends IGeometry, ICollideable2D, Bounded2D
{
	/**
	 * Indicates if the {@code IGeometry2D} intersects a rectangle.
	 * 
	 * @param x  the rectangle's center x-coördinate
	 * @param y  the rectangle's center y-coördinate
	 * @param w  the rectangle's width
	 * @param h  the rectangle's height
	 * @return {@code true} if the rectangle is contained
	 */
	public default boolean intersects(float x, float y, float w, float h)
	{
		return intersects(new Rectangle(x, y, w, h));
	}
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a rectangle.
	 * 
	 * @param x  the rectangle's center x-coördinate
	 * @param y  the rectangle's center y-coördinate
	 * @param w  the rectangle's width
	 * @param h  the rectangle's height
	 * @return {@code true} if the rectangle is contained
	 */
	public default boolean contains(float x, float y, float w, float h)
	{
		return contains(new Rectangle(x, y, w, h));
	}
}