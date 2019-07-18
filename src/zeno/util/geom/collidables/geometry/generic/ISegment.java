package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom._deprecated.Containment;
import zeno.util.geom._deprecated.Intersection;
import zeno.util.geom.collidables.IGeometry;

/**
 * The {@code ISegment} interface defines the collision operations for line segment geometry.
 * 
 * @author Zeno
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ISegment extends IGeometry
{	
	/**
	 * Returns the first point of the {@code ISegment}.
	 * 
	 * @return  the segment's first point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P1();
	
	/**
	 * Returns the second point of the {@code ISegment}.
	 * 
	 * @return  the segment's second point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P2();
	
	
	@Override
	public default boolean contains(Vector p)
	{
		return Containment.in(this, p);
	}
		
	@Override
	public default boolean contains(IEllipsoid e)
	{
		return Containment.in(this, e);
	}
	
	@Override
	public default boolean contains(ICuboid c)
	{
		return Containment.in(this, c);
	}


	@Override
	public default boolean intersects(ICuboid c)
	{
		return Intersection.between(this, c);
	}
	
	@Override
	public default boolean intersects(IEllipsoid e)
	{
		return Intersection.between(this, e);
	}
	
	@Override
	public default boolean intersects(ISegment l)
	{
		return Intersection.between(this, l);
	}
	
	@Override
	public default boolean intersects(ISphere s)
	{
		return Intersection.between(this, s);
	}
	
	
	// Optional Bounds overrides.
	
	@Override
	public default Vector Center()
	{
		return P1().plus(P2()).times(0.5f);
	}
	
	@Override
	public default Vector Size()
	{
		return P2().minus(P1()).Absolute();
	}
}