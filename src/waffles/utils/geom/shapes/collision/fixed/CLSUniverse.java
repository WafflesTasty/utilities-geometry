package waffles.utils.geom.shapes.collision.fixed;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.fixed.Universe;
import waffles.utils.geom.shapes.response.fixed.RSPUniverse;

/**
 * A {@code CLSUniverse} defines {@code Collision} for a {@code Universe}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see Collision
 */
public class CLSUniverse extends Collision
{
	/**
	 * Creates a new {@code CLSUniverse}.
	 *
	 * @param s  a source universe
	 *
	 *
	 * @see Universe
	 */
	public CLSUniverse(Universe s)
	{
		super(s);
	}


	@Override
	public Response contain(Collidable c)
	{
		Universe s = Source();		
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new RSPUniverse(s, t);
		}
		
		return () -> s;
	}

	@Override
	public Response intersect(Collidable c)
	{
		Universe s = Source();		
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new RSPUniverse(s, t);
		}
		
		return () -> s;
	}

	@Override
	public Response inhabit(Collidable c)
	{
		Universe s = Source();		
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new RSPUniverse(s, t);
		}
		
		return () -> s;
	}

	@Override
	public Universe Source()
	{
		return (Universe) super.Source();
	}
}