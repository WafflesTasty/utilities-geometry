package zeno.util.geom._deprecated.geometry;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom._deprecated.IBounds;
import zeno.util.geom._deprecated.geometry.shapes.NCube;
import zeno.util.geom._deprecated.geometry.shapes.NCuboid;
import zeno.util.geom._deprecated.geometry.shapes.NEllipsoid;
import zeno.util.geom._deprecated.geometry.shapes.NSphere;
import zeno.util.geom._deprecated.geometry.shapes.lines.Line;

/**
 * The {@code Geometry} interface is the base for convex geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 */
public interface IGeometry extends IBounds
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
	 * @see NEllipsoid
	 */
	public abstract boolean contains(NEllipsoid e);
	
	/**
	 * Indicates if the {@code Geometry} contains a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see NCuboid
	 */
	public abstract boolean contains(NCuboid c);
	
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
	 * @see NEllipsoid
	 */
	public abstract boolean intersects(NEllipsoid e);
	
	/**
	 * Indicates if the {@code Geometry} intersects a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see NCuboid
	 */
	public abstract boolean intersects(NCuboid c);

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
	 * @see NCube
	 */
	public default boolean contains(NCube c)
	{
		return contains((NCuboid) c);
	}
	
	/**
	 * Indicates if the {@code Geometry} contains a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see NSphere
	 */
	public default boolean contains(NSphere s)
	{
		return contains((NEllipsoid) s);
	}
		
	/**
	 * Indicates if the {@code Geometry} intersects a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see NSphere
	 */
	public default boolean intersects(NSphere s)
	{
		return contains((NEllipsoid) s);
	}
	
	/**
	 * Indicates if the {@code Geometry} intersects a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * @see NCube
	 */
	public default boolean intersects(NCube c)
	{
		return contains((NCuboid) c);
	}
}