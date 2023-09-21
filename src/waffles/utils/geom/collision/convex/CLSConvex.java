package waffles.utils.geom.collision.convex;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collision.convex.general.CNTPoint;
import waffles.utils.geom.collision.hulls.general.ISCConvex;

/**
 * The {@code CLSConvex} class defines collision responses for {@code ConvexSet} objects.
 * 
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSConvex implements Collision
{
	private ConvexSet src;
	
	/**
	 * Creates a new {@code CLSConvex}.
	 * 
	 * @param s  a source convex
	 * 
	 * 
	 * @see ConvexSet
	 */
	public CLSConvex(ConvexSet s)
	{
		this.src = s;
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		ConvexSet s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTPoint(s, t);
		}
		
		return null;
	}

	@Override
	public Response intersect(Collidable c)
	{
		ConvexSet s = Source();
	
		// Eliminate convex geometry.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}
		
		return () -> false;
	}

	@Override
	public ConvexSet Source()
	{
		return src;
	}
}