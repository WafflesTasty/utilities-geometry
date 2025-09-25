package waffles.utils.geom.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Universe;
import waffles.utils.geom.response.fixed.RSPTrivial;
import waffles.utils.geom.response.fixed.RSPUniverse;

/**
 * A {@code CLSUniverse} defines collision responses for a {@code Universe} object.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSUniverse implements Collision
{	
	private Universe src;
	
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
		src = s;
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		return new RSPTrivial(c);
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		return new RSPTrivial(c);
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		return new RSPUniverse(Source(), c);
	}

	@Override
	public Universe Source()
	{
		return src;
	}
}