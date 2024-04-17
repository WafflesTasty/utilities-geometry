package waffles.utils.geom.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.convex.hulls.Hull;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.response.CNTPoint;
import waffles.utils.geom.response.ISCGeometric;
import waffles.utils.geom.response.ISCHull;
import waffles.utils.geom.response.spaces.ISCASpace;

/**
 * The {@code CLSGeometrical} class defines collision responses for {@code Geometrical} objects.
 *
 * @author Waffles
 * @since Jul 25, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSGeometrical implements Collision
{
	private Geometrical src;
	
	/**
	 * Creates a new {@code CLSGeometrical}.
	 * 
	 * @param s  a geometrical source
	 * 
	 * 
	 * @see Geometrical
	 */
	public CLSGeometrical(Geometrical s)
	{
		src = s;
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		Geometrical s = Source();
		
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
		
		// Eliminate convex hulls.
		if(c instanceof Hull)
		{
			Hull t = (Hull) c;
			return new ISCHull(s, t);
		}
		
		// Eliminate geometricals.
		if(c instanceof Geometrical)
		{
			Geometrical t = (Geometrical) c;
			return new ISCGeometric(s, t);
		}
		
		return () -> false;
	}
	
	@Override
	public Response contain(Collidable c)
	{
		Geometrical s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}
		
		return () -> false;
	}
	
	@Override
	public Geometrical Source()
	{
		return src;
	}
}