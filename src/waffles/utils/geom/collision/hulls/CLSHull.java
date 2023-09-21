package waffles.utils.geom.collision.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.ConvexSet;
import waffles.utils.geom.collidable.hulls.IHull;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.collision.convex.CLSConvex;
import waffles.utils.geom.collision.hulls.general.INHASpace;
import waffles.utils.geom.collision.hulls.general.INHConvex;
import waffles.utils.geom.collision.hulls.general.INHLine;

/**
 * The {@code CLSHull} class defines collision responses for {@code IHull} objects.
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
	 * @see IHull
	 */
	public CLSHull(IHull src)
	{
		super(src);
	}
	

	@Override
	public Response inhabit(Collidable c)
	{
		IHull s = Source();
		
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
	public IHull Source()
	{
		return (IHull) super.Source();
	}
}