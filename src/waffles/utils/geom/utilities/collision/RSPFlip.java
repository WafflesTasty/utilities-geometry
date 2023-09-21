package waffles.utils.geom.utilities.collision;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;

/**
 * An {@code RSPFlip} defines a collision response which flips the
 * directions of the distance and penetration vector, given
 * another response. All other variables are left as is.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class RSPFlip implements Response
{
	private Response rsp;
	
	/**
	 * Creates a new {@code RSPFlip}.
	 * 
	 * @param r  a response to flip
	 * 
	 * 
	 * @see Response
	 */
	public RSPFlip(Response r)
	{
		rsp = r;
	}

	
	@Override
	public Collidable Shape()
	{
		return rsp.Shape();
	}
	
	@Override
	public boolean hasImpact()
	{			
		return rsp.hasImpact();
	}
	
	@Override
	public Vector Penetration()
	{
		return rsp.Penetration().times(-1f);
	}

	@Override
	public Vector Distance()
	{
		return rsp.Distance().times(-1f);
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost();
	}
}