package zeno.util.geom;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.Cube;
import zeno.util.geom.shapes.Cuboid;
import zeno.util.geom.shapes.Ellipsoid;
import zeno.util.geom.shapes.Sphere;
import zeno.util.geom.shapes.lines.Line;

/**
 * The {@code Geometry} interface is the base for geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Bounded
 */
public interface Geometry extends Bounded
{
	/**
	 * Indicates if the {@code Geometry} contains a point.
	 * 
	 * @param v  a point to check
	 * @return {@code true} if the point is contained
	 * @see Vector
	 */
	public abstract boolean contains(Vector v);
	
	/**
	 * Indicates if the {@code Geometry} contains an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * @see Ellipsoid
	 */
	public abstract boolean contains(Ellipsoid e);
	
	/**
	 * Indicates if the {@code Geometry} contains a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see Cuboid
	 */
	public abstract boolean contains(Cuboid c);
	
	/**
	 * Indicates if the {@code Geometry} contains a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is contained
	 * @see Line
	 */
	public abstract boolean contains(Line l);
	
	
	/**
	 * Indicates if the {@code Geometry} intersects an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * @see Ellipsoid
	 */
	public abstract boolean intersects(Ellipsoid e);
	
	/**
	 * Indicates if the {@code Geometry} intersects a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see Cuboid
	 */
	public abstract boolean intersects(Cuboid c);

	/**
	 * Indicates if the {@code Geometry} intersects a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is contained
	 * @see Line
	 */
	public abstract boolean intersects(Line l);

	
	/**
	 * Indicates if the {@code Geometry} contains a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * @see Cube
	 */
	public default boolean contains(Cube c)
	{
		return contains((Cuboid) c);
	}
	
	/**
	 * Indicates if the {@code Geometry} contains a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see Sphere
	 */
	public default boolean contains(Sphere s)
	{
		return contains((Ellipsoid) s);
	}
		
	/**
	 * Indicates if the {@code Geometry} intersects a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see Sphere
	 */
	public default boolean intersects(Sphere s)
	{
		return contains((Ellipsoid) s);
	}
	
	/**
	 * Indicates if the {@code Geometry} intersects a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * @see Cube
	 */
	public default boolean intersects(Cube c)
	{
		return contains((Cuboid) c);
	}
}