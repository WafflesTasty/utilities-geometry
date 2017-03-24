package zeno.util.geom._deprecated.geometry.types;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._deprecated.geometry.IGeometry;
import zeno.util.geom._deprecated.geometry.shapes.surfaces.Rectangle;
import zeno.util.geom._deprecated.tools.IBounds2D;

/**
 * The {@code IGeometry2D} interface is the base for two-dimensional convex geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Geometry
 * @see IBounds2D
 */
public interface IGeometry2D extends IGeometry, IBounds2D
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
		return contains(new Rectangle(x, y, w, h));
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
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return {@code true} if the point is contained
	 */
	public default boolean contains(float x, float y)
	{
		return contains(new Vector2(x, y));
	}
}