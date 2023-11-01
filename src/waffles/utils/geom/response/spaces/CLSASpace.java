package waffles.utils.geom.response.spaces;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.response.spaces.affine.CNTASpace;
import waffles.utils.geom.response.spaces.affine.CNTPoint;
import waffles.utils.geom.response.spaces.affine.ISCASpace;

/**
 * The {@code CLSASpace} class defines collision responses for {@code ASpace} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSASpace implements Collision
{
	private ASpace src;
	
	/**
	 * Creates a new {@code CLSASpace}.
	 * 
	 * @param s  a source space
	 * 
	 * 
	 * @see ASpace
	 */
	public CLSASpace(ASpace s)
	{
		src = s;
	}


	@Override
	public Response intersect(Collidable c)
	{
		ASpace s = Source();
		
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
		
		return () -> false;
	}
	
	@Override
	public Response contain(Collidable c)
	{
		ASpace s = Source();
		
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
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace t = (VSpace) c;
			return new CNTASpace(s, t);
		}
		
		return () -> false;
	}

	@Override
	public ASpace Source()
	{
		return src;
	}
}