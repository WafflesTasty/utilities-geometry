package zeno.util.geom.interfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.geom.tools.IBounds2D;

/**
 * The {@code IGeometry2D} interface is the base for two-dimensional convex geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry
 * @see IBounds2D
 */
public interface IGeometry2D extends IGeometry, IBounds2D
{
	/**
	 * Indicates if the {@code IGeometry2D} intersects a rectangle.
	 * 
	 * @param x  the rectangle's center x-co�rdinate
	 * @param y  the rectangle's center y-co�rdinate
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
	 * @param x  the rectangle's center x-co�rdinate
	 * @param y  the rectangle's center y-co�rdinate
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
	 * @param x  the point's x-co�rdinate
	 * @param y  the point's y-co�rdinate
	 * @return {@code true} if the point is contained
	 */
	public default boolean contains(float x, float y)
	{
		return contains(new Vector2(x, y));
	}
}