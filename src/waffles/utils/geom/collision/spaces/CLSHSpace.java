package waffles.utils.geom.collision.spaces;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.HSpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.response.spaces.halved.CNTASpace;
import waffles.utils.geom.response.spaces.halved.CNTConvex;
import waffles.utils.geom.response.spaces.halved.CNTPoint;
import waffles.utils.geom.response.spaces.halved.CNTVSpace;
import waffles.utils.geom.response.spaces.halved.ISCConvex;

/**
 * The {@code CLSHSpace} class defines collision responses for {@code HSpace} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSHSpace implements Collision
{
	private HSpace src;
	
	/**
	 * Creates a new {@code CLSHSpace}.
	 * 
	 * @param s  a source space
	 * 
	 * 
	 * @see HSpace
	 */
	public CLSHSpace(HSpace s)
	{
		src = s;
	}

	
	@Override
	public Response intersect(Collidable c)
	{
		HSpace s = Source();
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}

		return () -> false;
	}
	
	@Override
	public Response contain(Collidable c)
	{
		HSpace s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace t = (VSpace) c;
			return new CNTVSpace(s, t);
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new CNTASpace(s, t);
		}
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new CNTConvex(s, t);
		}

		return () -> false;
	}

	@Override
	public HSpace Source()
	{
		return src;
	}
}