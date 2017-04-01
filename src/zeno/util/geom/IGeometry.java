package zeno.util.geom;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.ICube;
import zeno.util.geom.shapes.ICuboid;
import zeno.util.geom.shapes.IEllipsoid;
import zeno.util.geom.shapes.ISphere;
import zeno.util.geom.shapes.lines.Line;
import zeno.util.geom.tools.bounds.Bounded;

/**
 * The {@code IGeometry} interface is the base for geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see Bounded
 */
public interface IGeometry extends Bounded
{
	/**
	 * Indicates if the {@code IGeometry} contains a point.
	 * 
	 * @param v  a point to check
	 * @return {@code true} if the point is contained
	 * @see Vector
	 */
	public abstract boolean contains(Vector v);
	
	/**
	 * Indicates if the {@code IGeometry} contains an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * @see IEllipsoid
	 */
	public abstract boolean contains(IEllipsoid e);
	
	/**
	 * Indicates if the {@code IGeometry} contains a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see ICuboid
	 */
	public abstract boolean contains(ICuboid c);
	
	/**
	 * Indicates if the {@code IGeometry} contains a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is contained
	 * @see Line
	 */
	public abstract boolean contains(Line l);
	
	
	/**
	 * Indicates if the {@code IGeometry} intersects an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * @see IEllipsoid
	 */
	public abstract boolean intersects(IEllipsoid e);
	
	/**
	 * Indicates if the {@code IGeometry} intersects a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * @see ICuboid
	 */
	public abstract boolean intersects(ICuboid c);

	/**
	 * Indicates if the {@code IGeometry} intersects a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is contained
	 * @see Line
	 */
	public abstract boolean intersects(Line l);

	
	/**
	 * Indicates if the {@code IGeometry} contains a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * @see ICube
	 */
	public default boolean contains(ICube c)
	{
		return contains((ICuboid) c);
	}
	
	/**
	 * Indicates if the {@code IGeometry} contains a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see ISphere
	 */
	public default boolean contains(ISphere s)
	{
		return contains((IEllipsoid) s);
	}
		
	/**
	 * Indicates if the {@code IGeometry} intersects a sphere.
	 * 
	 * @param s  a sphere to check
	 * @return {@code true} if the sphere is contained
	 * @see ISphere
	 */
	public default boolean intersects(ISphere s)
	{
		return contains((IEllipsoid) s);
	}
	
	/**
	 * Indicates if the {@code IGeometry} intersects a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * @see ICube
	 */
	public default boolean intersects(ICube c)
	{
		return contains((ICuboid) c);
	}
}