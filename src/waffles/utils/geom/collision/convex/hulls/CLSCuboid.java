package waffles.utils.geom.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.response.fixed.RSPFlip;
import waffles.utils.geom.response.hulls.cuboids.CNTCuboid;
import waffles.utils.geom.response.hulls.cuboids.CNTPoint;
import waffles.utils.geom.response.hulls.cuboids.INHConvex;
import waffles.utils.geom.response.hulls.cuboids.ISCCuboid;
import waffles.utils.geom.response.hulls.cuboids.ISCLine;

/**
 * The {@code CLSSpheroid} class defines collision responses for {@code ICuboid} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSCuboid extends CLSHull
{	
	/**
	 * Creates a new {@code CLSCuboid}.
	 * 
	 * @param s  a source cuboid
	 * 
	 * 
	 * @see ICuboid
	 */
	public CLSCuboid(ICuboid s)
	{
		super(s);
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		ICuboid s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			ICuboid t = (ICuboid) c;
			return new CNTCuboid(s, t);
		}
		
		// Eliminate bounded sets.
		if(c instanceof Bounded)
		{
			Bounded b = (Bounded) c;
			ICuboid t = b.Bounds().Box();
			return new CNTCuboid(s, t);
		}
		
		return super.contain(c);
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		ICuboid s = Source();
		
		// Eliminate lines.
		if(c instanceof Line)
		{
			Line t = (Line) c;
			return new ISCLine(s, t);
		}

		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			ICuboid t = (ICuboid) c;
			return new ISCCuboid(s, t);
		}
		
		return super.intersect(c);
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		ICuboid s = Source();
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			ICuboid t = (ICuboid) c;
			Collision cls = t.Collisions();
			return new RSPFlip(cls.contain(s));
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
	public ICuboid Source()
	{
		return (ICuboid) super.Source();
	}
}