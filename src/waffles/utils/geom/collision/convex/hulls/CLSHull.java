package waffles.utils.geom.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.collision.convex.CLSConvex;
import waffles.utils.geom.response.hulls.INHASpace;
import waffles.utils.geom.response.hulls.INHConvex;
import waffles.utils.geom.response.hulls.INHLine;

/**
 * The {@code CLSHull} class defines collision responses for {@code Hull} objects.
 * 
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSHull extends CLSConvex
{
	/**
	 * Creates a new {@code CLSHull}.
	 * 
	 * @param src  a source hull
	 * 
	 * 
	 * @see Hull
	 */
	public CLSHull(Hull src)
	{
		super(src);
	}
	

	@Override
	public Response inhabit(Collidable c)
	{
		Hull s = Source();
		
		// Eliminate lines.
		if(c instanceof Line)
		{
			Line t = (Line) c;
			return new INHLine(s, t);
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new INHASpace(s, t);
		}
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace t = (VSpace) c;
			return new INHASpace(s, t);
		}
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new INHConvex(s, t);
		}
		
		return super.inhabit(c);
	}

	@Override
	public Hull Source()
	{
		return (Hull) super.Source();
	}
}