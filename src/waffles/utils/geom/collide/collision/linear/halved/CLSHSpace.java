package waffles.utils.geom.collide.collision.linear.halved;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.response.linear.halved.CNTASpace;
import waffles.utils.geom.collide.response.linear.halved.CNTConvex;
import waffles.utils.geom.collide.response.linear.halved.CNTHSpace;
import waffles.utils.geom.collide.response.linear.halved.CNTPoint;
import waffles.utils.geom.collide.response.linear.halved.IHBASpace;
import waffles.utils.geom.collide.response.linear.halved.ISCASpace;
import waffles.utils.geom.collide.response.linear.halved.ISCConvex;
import waffles.utils.geom.collide.response.linear.halved.ISCHSpace;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSHSpace} defines {@code Collision} for a {@code HSpace}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see Collision
 */
public class CLSHSpace extends Collision
{
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
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSHSpace}.
	 *
	 * @param s  a source space
	 * @param e  an error margin
	 * 
	 *
	 * @see HSpace
	 */
	public CLSHSpace(HSpace s, double e)
	{
		super(s, e);
	}
	
	
	@Override
	public Response inhabit(Collidable c)
	{
		HSpace s = Source();
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new IHBASpace(s, t);
		}
		
		return () -> s;
	}

	@Override
	public Response intersect(Collidable c)
	{
		HSpace s = Source();

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}
		
		// Eliminate halfspaces.
		if(c instanceof HSpace)
		{
			HSpace t = (HSpace) c;
			return new ISCHSpace(s, t);
		}
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}

		return () -> s;
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

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new CNTASpace(s, t);
		}
		
		// Eliminate halfspaces.
		if(c instanceof HSpace)
		{
			HSpace t = (HSpace) c;
			return new CNTHSpace(s, t);
		}

		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new CNTConvex(s, t);
		}

		return () -> s;
	}

	@Override
	public HSpace Source()
	{
		return (HSpace) super.Source();
	}
}