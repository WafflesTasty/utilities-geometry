package waffles.utils.geom.collision.convex;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collidable.axial.spheroid.ISphere;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collision.convex.spheres.CNTPoint;
import waffles.utils.geom.collision.convex.spheres.CNTSphere;
import waffles.utils.geom.collision.convex.spheres.ISCASpace;
import waffles.utils.geom.collision.convex.spheres.ISCGeneral;
import waffles.utils.geom.collision.convex.spheres.ISCSphere;

/**
 * The {@code CLSSpheroid} class defines collision responses for {@code ISphere} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSSphere extends CLSConvex
{	
	/**
	 * Creates a new {@code CLSSphere}.
	 * 
	 * @param s  a source sphere
	 * 
	 * 
	 * @see ISphere
	 */
	public CLSSphere(ISphere s)
	{
		super(s);
	}
	
		
	@Override
	public Response intersect(Collidable c)
	{	
		ISphere s = Source();
		
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

		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			ISphere t = (ISphere) c;
			return new ISCSphere(s, t);
		}

		// Use a general method.
		return new ISCGeneral(s, c);
	}
	
	@Override
	public Response contain(Collidable c)
	{
		ISphere s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}
		
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			ISphere t = (ISphere) c;
			return new CNTSphere(s, t);
		}
		
		return super.contain(c);
	}
		
	@Override
	public ISphere Source()
	{
		return (ISphere) super.Source();
	}
}