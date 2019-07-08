package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable2D;
import zeno.util.geom.collidables.geometry.planar.Rectangle;
import zeno.util.geom.utilities.bounds.Bounds2D;
import zeno.util.geom.utilities.bounds.IBounded2D;

/**
 * The {@code IGeometry2D} interface is the base for two-dimensional geometric shapes.
 *
 * @author Zeno
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see IGeometry 
 * @see ICollideable2D
 * @see IBounded2D
 * @see Bounds2D
 */
public interface IGeometry2D extends IGeometry, ICollideable2D, IBounded2D, Bounds2D
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

	
	@Override
	public default Bounds2D Bounds()
	{
		return this;
	}
}