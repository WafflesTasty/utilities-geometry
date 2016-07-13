package zeno.util.geom.shapes;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.shapes.lines.Line3D;
import zeno.util.geom.tools.bounds.IBound3D;

/**
 * The {@code Geometry3D} class is the base for any convex three-dimensional geometric shape.
 *
 * @author Zeno
 * @since Aug 22, 2015
 * @see IGeometry
 * @see IBound3D
 */
public interface IGeometry3D extends IGeometry, IBound3D
{
	/**
	 * Indicates whether the {@code IGeometry3D} crosses a line.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 * @return  {@code true} if the lines intersect
	 */
	public abstract boolean crosses(float x1, float y1, float z1, float x2, float y2, float z2);
	
	/**
	 * Indicates if the {@code Geometry3D} intersects a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid intersects
	 */
	public abstract boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2);
	
	/**
	 * Indicates if the {@code Geometry3D} contains a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid contains
	 */
	public abstract boolean contains(float x1, float y1, float z1, float x2, float y2, float z2);
	
	/**
	 * Indicates if the {@code Geometry3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return  {@code true} if the point is contained
	 */
	public abstract boolean contains(float x, float y, float z);
		
	/**
	 * Returns the vertices of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's vertices
	 * @see Vector3
	 */
	@Override
	public abstract Vector3[] Vertices();

	
	/**
	 * Indicates if the {@code IGeometry3D} crosses a line.
	 * 
	 * @param line  a line to check
	 * @return  {@code true} if the lines intersect
	 * @see Line3D
	 */
	public default boolean crosses(Line3D line)
	{
		return crosses(line.X1(), line.Y1(), line.Z1(), line.X2(), line.Y2(), line.Z2());
	}
		
	/**
	 * Indicates if the {@code Geometry3D} intersects a cuboid.
	 * 
	 * @param cube  a cuboid to check
	 * @return  {@code true} if the cuboid is intersected
	 * @see IBound3D
	 */
	public default boolean intersects(IBound3D cube)
	{
		return intersects(cube.XMin(), cube.YMin(), cube.ZMin(), cube.XMax(), cube.YMax(), cube.ZMax());
	}
		
	/**
	 * Indicates if the {@code Geometry3D} contains a cuboid.
	 * 
	 * @param cube  a cuboid to check
	 * @return  {@code true} if the cuboid is contained
	 * @see IBound3D
	 */
	public default boolean contains(IBound3D cube)
	{
		return contains(cube.XMin(), cube.YMin(), cube.ZMin(), cube.XMax(), cube.YMax(), cube.ZMax());
	}
		
	/**
	 * Indicates if the {@code Geometry3D} contains a point.
	 * 
	 * @param v  a point to check
	 * @return  {@code true} if the point is contained
	 * @see Vector3
	 */
	public default boolean contains(Vector3 v)
	{
		return contains(v.X(), v.Y(), v.Z());
	}
}