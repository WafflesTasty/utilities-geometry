package waffles.utils.geom.collide.collision.convex.spheres;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.convex.CLSConvex;
import waffles.utils.geom.collide.response.convex.spheres.spheroid.CNTPoint;
import waffles.utils.geom.collide.response.convex.spheres.spheroid.IHBConvex;
import waffles.utils.geom.collide.response.convex.spheres.spheroid.ISCASpace;
import waffles.utils.geom.collide.response.convex.spheres.spheroid.ISCConvex;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSSpheroid} defines {@code Collision} for a {@code HyperSpheroid}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see CLSConvex
 */
public class CLSSpheroid extends CLSConvex
{
	/**
	 * Creates a new {@code CLSSpheroid}.
	 *
	 * @param s  a source spheroid
	 *
	 *
	 * @see HyperSpheroid
	 */
	public CLSSpheroid(HyperSpheroid s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSSpheroid}.
	 *
	 * @param s  a source spheroid
	 * @param e  an error margin
	 * 
	 *
	 * @see HyperSpheroid
	 */
	public CLSSpheroid(HyperSpheroid s, double e)
	{
		super(s, e);
	}

	
	@Override
	public Response inhabit(Collidable c)
	{
		HyperSpheroid s = Source();
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new IHBConvex(s, t);
		}
		
		return super.inhabit(c);
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		HyperSpheroid s = Source();

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}

		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}

		return super.intersect(c);
	}
	
	@Override
	public Response contain(Collidable c)
	{
		HyperSpheroid s = Source();

		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}

		return super.contain(c);
	}

	@Override
	public HyperSpheroid Source()
	{
		return (HyperSpheroid) super.Source();
	}
}