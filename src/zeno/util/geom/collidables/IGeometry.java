package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine.Space;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.geometry.generic.ICube;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code IGeometry} interface is the base for bounded n-dimensional shapes.
 * <br> It allows for basic collision detection and bounding volumes.
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
public interface IGeometry extends ICollidable, IBounded, Bounds
{			
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
	 * Indicates if the {@code IGeometry} intersects a segment.
	 * 
	 * @param l  a segment to check
	 * @return {@code true} if the segment is intersected
	 * 
	 * 
	 * @see ISegment
	 */
	public abstract boolean intersects(ISegment l);
	
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
		return intersects((IEllipsoid) s);
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
		return intersects((ICuboid) c);
	}

	
	@Override
	public default boolean contains(ICollidable c)
	{
		if(c instanceof Point)
		{
			return contains(((Point) c).VMatrix());
		}
		
		throw new RuntimeException();
	}
	
	@Override
	public default boolean intersects(ICollidable c)
	{
		if(c instanceof Affine)
		{
			Affine a = (Affine) c;
			if(a instanceof Affine.Set)
			{
				for(Point p : a.Span())
				{
					if(contains(p))
					{
						return true;
					}
				}
				
				return false;
			}

			Affine.Space s = (Space) a;
			if(s.Dimension() < 0)
			{
				return false;
			}

			return false;
		}
		
		throw new RuntimeException();
	}
	
	@Override
	public default Bounds Bounds()
	{
		return this;
	}
}