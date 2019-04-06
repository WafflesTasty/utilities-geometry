package zeno.util.geom.collidables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom._deprecated.collideables.affine.ASpace;
import zeno.util.geom._deprecated.collideables.geometry.higher.lines.NLine;
import zeno.util.geom._deprecated.collideables.lines.ILine;
import zeno.util.geom.collidables.geometry.ICube;
import zeno.util.geom.collidables.geometry.ICuboid;
import zeno.util.geom.collidables.geometry.IEllipsoid;
import zeno.util.geom.collidables.geometry.ISphere;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code IGeometry} interface is the base for geometric shapes.
 * 
 * @author Zeno
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see ICollidable
 * @see IBounded
 * @see Bounds
 */
public interface IGeometry extends IBounded, ICollidable, Bounds
{		
	public abstract boolean intersects(ILine l);
	
	public default boolean intersects(ASpace a)
	{
		int dim = Dimension();
		Matrix m = a.vectorize(dim);
		if(m.Columns() == 1)
		{
			return contains((Vector) m);
		}
		
		Vector p1 = m.Column(0);
		Vector p2 = m.Column(1);
		return intersects(new NLine(p1, p2));
	}
	
	
	/**
	 * Indicates if the {@code IGeometry} contains a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * 
	 * 
	 * @see ICuboid
	 */
	public abstract boolean contains(ICuboid c);
	
	/**
	 * Indicates if the {@code IGeometry} contains an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * 
	 * 
	 * @see IEllipsoid
	 */
	public abstract boolean contains(IEllipsoid e);

	/**
	 * Indicates if the {@code IGeometry} intersects an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @return {@code true} if the ellipsoid is contained
	 * 
	 * 
	 * @see IEllipsoid
	 */
	public abstract boolean intersects(IEllipsoid e);
	
	/**
	 * Indicates if the {@code IGeometry} intersects a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @return {@code true} if the cuboid is contained
	 * 
	 * 
	 * @see ICuboid
	 */
	public abstract boolean intersects(ICuboid c);

	
	/**
	 * Indicates if the {@code IGeometry} contains a cube.
	 * 
	 * @param c  a cube to check
	 * @return {@code true} if the cube is contained
	 * 
	 * 
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
	 * 
	 * 
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
	 * 
	 * 
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
	 * 
	 * 
	 * @see ICube
	 */
	public default boolean intersects(ICube c)
	{
		return contains((ICuboid) c);
	}
}