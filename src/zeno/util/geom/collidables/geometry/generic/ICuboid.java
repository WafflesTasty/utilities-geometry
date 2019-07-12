package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {ICuboid} interface defines the base for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ICuboid extends IGeometry
{	
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
	public default ICuboid Box()
	{
		return this;
	}
}