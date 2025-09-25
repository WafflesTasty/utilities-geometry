package waffles.utils.geom.collision.convex;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.axial.spheroid.HyperSpheroid;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.response.convex.spheroid.CNTPoint;
import waffles.utils.geom.response.convex.spheroid.ISCASpace;
import waffles.utils.geom.response.convex.spheroid.ISCHull;

/**
 * The {@code CLSSpheroid} class defines collision responses for {@code HyperSpheroid} objects.
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
	 * @param s  a source ellipsoid
	 * 
	 * 
	 * @see HyperSpheroid
	 */
	public CLSSpheroid(HyperSpheroid s)
	{
		super(s);
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		HyperSpheroid s = Source();
		
		// Eliminate convex hulls.
		if(c instanceof Hull)
		{
			Hull t = (Hull) c;
			return new ISCHull(s, t);
		}
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace t = (VSpace) c;
			return new ISCASpace(s, t);
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