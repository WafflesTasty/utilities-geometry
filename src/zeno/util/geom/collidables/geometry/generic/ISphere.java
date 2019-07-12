package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {@code ISphere} interface defines the base for sphere geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IEllipsoid
 */
public interface ISphere extends IEllipsoid
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
	public default boolean contains(ISphere s)
	{
		return Containment.in(this, s);
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
	
	
	@Override
	public default float Diameter()
	{
		return Size().get(0);
	}

	@Override
	public default ISphere Ball()
	{
		return this;
	}
}