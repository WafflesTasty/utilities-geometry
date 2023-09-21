package waffles.utils.geom.collision.spaces;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collision.spaces.vector.CNTPoint;
import waffles.utils.geom.collision.spaces.vector.CNTVSpace;
import waffles.utils.geom.collision.spaces.vector.ISCVSpace;

/**
 * The {@code CLSVSpace} class defines collision responses for {@code VSpace} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSVSpace implements Collision
{
	private VSpace src;
	
	/**
	 * Creates a new {@code CLSVSpace}.
	 * 
	 * @param s  a source space
	 * 
	 * 
	 * @see VSpace
	 */
	public CLSVSpace(VSpace s)
	{
		src = s;
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace s = Source();
			VSpace t = (VSpace) c;
			
			return new ISCVSpace(s, t);
		}

		return () -> false;
	}

	@Override
	public Response contain(Collidable c)
	{
		// Eliminate points.
		if(c instanceof Point)
		{
			VSpace s = Source();
			Point p = (Point) c;
			
			return new CNTPoint(s, p);
		}
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace s = Source();
			VSpace t = (VSpace) c;
			
			return new CNTVSpace(s, t);
		}
		
		return () -> false;
	}
	
	@Override
	public VSpace Source()
	{
		return src;
	}
}