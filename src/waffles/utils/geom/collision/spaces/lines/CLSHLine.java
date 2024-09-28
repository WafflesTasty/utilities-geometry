package waffles.utils.geom.collision.spaces.lines;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collidable.spaces.lines.HLine;
import waffles.utils.geom.response.fixed.RSPFlip;
import waffles.utils.geom.response.hulls.cuboids.ISCLine;
import waffles.utils.geom.response.lines.halved.CNTPoint;
import waffles.utils.geom.response.lines.halved.IHBASpace;

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
public class CLSHLine implements Collision
{
	private HLine src;
	
	/**
	 * Creates a new {@code CLSHLine}.
	 * 
	 * @param l  a source line
	 * 
	 * 
	 * @see HLine
	 */
	public CLSHLine(HLine l)
	{
		src = l;
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		HLine l = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(l, p);
		}

		return () -> false;
	}
		
	@Override
	public Response intersect(Collidable c)
	{
		HLine l = Source();
		
		// Eliminate cuboids.
		if(c instanceof HyperCuboid)
		{
			HyperCuboid t = (HyperCuboid) c;
			return new RSPFlip(new ISCLine(t, l));
		}
		
		return () -> false;
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		HLine l = Source();
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace s = (ASpace) c;
			return new IHBASpace(l, s);
		}
		
		// Eliminate vector spaces.
		if(c instanceof VSpace)
		{
			VSpace s = (VSpace) c;
			return new IHBASpace(l, s);
		}
		
		return () -> false;
	}
	
	@Override
	public HLine Source()
	{
		return src;
	}
}