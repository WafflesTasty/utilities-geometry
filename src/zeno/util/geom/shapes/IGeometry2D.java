package zeno.util.geom.shapes;

import zeno.util.algebra.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry;
import zeno.util.geom.shapes.lines.Line2D;
import zeno.util.geom.tools.bounds.IBound2D;

/**
 * The {@code Geometry2D} class is the base for any convex two-dimensional geometric shape.
 * 
 * @since Jul 6, 2016
 * @author Zeno
 * 
 * @see IGeometry
 * @see IBound2D
 */
public interface IGeometry2D extends IGeometry, IBound2D
{
	/**
	 * Indicates if the {@code IGeometry2D} crosses a line.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle intersects
	 */
	public abstract boolean crosses(float x1, float y1, float x2, float y2);
	
	/**
	 * Indicates if the {@code IGeometry2D} intersects a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle intersects
	 */
	public abstract boolean intersects(float x1, float y1, float x2, float y2);
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a rectangle.
	 * 
	 * @param x1  the rectangle's first x-coördinate
	 * @param y1  the rectangle's first y-coördinate
	 * @param x2  the rectangle's second x-coördinate
	 * @param y2  the rectangle's second y-coördinate
	 * @return  {@code true} if the rectangle contains
	 */
	public abstract boolean contains(float x1, float y1, float x2, float y2);
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @return  {@code true} if the point is contained
	 */
	public abstract boolean contains(float x, float y);
	
	/**
	 * Returns the vertices of the {@code IGeometry2D}.
	 * 
	 * @return  the geometry's vertices
	 * @see Vector2
	 */
	@Override
	public abstract Vector2[] Vertices();

	
	/**
	 * Indicates if the {@code IGeometry2D} crosses a line.
	 * 
	 * @param line  a line to check
	 * @return  {@code true} if the lines intersect
	 * @see Line2D
	 */
	public default boolean crosses(Line2D line)
	{
		return crosses(line.X1(), line.Y1(), line.X2(), line.Y2());
	}
	
	/**
	 * Indicates if the {@code IGeometry2D} intersects a rectangle.
	 * 
	 * @param rect  a rectangle to check
	 * @return  {@code true} if the rectangle intersects
	 * @see IBound2D
	 */
	public default boolean intersects(IBound2D rect)
	{
		return intersects(rect.XMin(), rect.YMin(), rect.XMax(), rect.YMax());
	}
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a rectangle.
	 * 
	 * @param rect  a rectangle to check
	 * @return  {@code true} if the rectangle contains
	 * @see IBound2D
	 */
	public default boolean contains(IBound2D rect)
	{
		return contains(rect.XMin(), rect.YMin(), rect.XMax(), rect.YMax());
	}
	
	/**
	 * Indicates if the {@code IGeometry2D} contains a point.
	 * 
	 * @param v  a point to check
	 * @return  {@code true} if the point is contained
	 * @see Vector2
	 */
	public default boolean contains(Vector2 v)
	{
		return contains(v.X(), v.Y());
	}
}