package waffles.utils.geom.collide.collision.linear.halved;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.response.linear.halved.line.CNTHLine;
import waffles.utils.geom.collide.response.linear.halved.line.CNTHSpace;
import waffles.utils.geom.collide.response.linear.halved.line.CNTHull;
import waffles.utils.geom.collide.response.linear.halved.line.CNTPoint;
import waffles.utils.geom.collide.response.linear.halved.line.IHBASpace;
import waffles.utils.geom.collide.response.linear.halved.line.IHBHSpace;
import waffles.utils.geom.collide.response.linear.halved.line.ISCASpace;
import waffles.utils.geom.collide.response.linear.halved.line.ISCHLine;
import waffles.utils.geom.collide.response.linear.halved.line.ISCHSpace;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * The {@code CLSHLine} class defines collision responses for {@code HLine} objects.
 *
 * @author Waffles
 * @since 27 Sep 2024
 * @version 1.1
 * 
 * 
 * @see Collision
 */
public class CLSHLine extends Collision
{
	/**
	 * Creates a new {@code CLSHLine}.
	 *
	 * @param s  a source line
	 *
	 *
	 * @see HLine
	 */
	public CLSHLine(HLine s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSHLine}.
	 *
	 * @param s  a source line
	 * @param e  an error margin
	 * 
	 *
	 * @see HLine
	 */
	public CLSHLine(HLine s, double e)
	{
		super(s, e);
	}
	
		
	@Override
	public Response contain(Collidable c)
	{
		HLine s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTPoint(s, t);
		}

		// Eliminate halfspaces.
		if(c instanceof HSpace)
		{
			HSpace t = (HSpace) c;
			return new CNTHSpace(s, t);
		}
		
		// Eliminate halflines.
		if(c instanceof HLine)
		{
			HLine t = (HLine) c;
			return new CNTHLine(s, t);
		}
		
		// Eliminate convex hulls.
		if(c instanceof Hull)
		{
			Hull t = (Hull) c;
			return new CNTHull(s, t);
		}

		return () -> s;
	}
		
	@Override
	public Response intersect(Collidable c)
	{
		HLine s = Source();
		
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
		
		// Eliminate halflines.
		if(c instanceof HLine)
		{
			HLine t = (HLine) c;
			return new ISCHLine(s, t);
		}
		
		return () -> s;
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		HLine s = Source();
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new IHBASpace(s, t);
		}
		
		// Eliminate halfspaces.
		if(c instanceof HSpace)
		{
			HSpace t = (HSpace) c;
			return new IHBHSpace(s, t);
		}

		return () -> s;
	}

	@Override
	public HLine Source()
	{
		return (HLine) super.Source();
	}
}