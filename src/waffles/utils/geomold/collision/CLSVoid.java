package waffles.utils.geom.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.fixed.Void;
import waffles.utils.geom.response.fixed.RSPVoid;

/**
 * A {@code CLSVoid} defines collision responses for a {@code Void} object.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSVoid implements Collision
{	
	private Void src;
	
	/**
	 * Creates a new {@code CLSVoid}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public CLSVoid(Void s)
	{
		src = s;
	}
	
	
	@Override
	public Response contain(Collidable c)
	{
		return new RSPVoid(c.Dimension());
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		return new RSPVoid(c.Dimension());
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		return new RSPVoid(c.Dimension());
	}
	
	@Override
	public Void Source()
	{
		return src;
	}
}