package waffles.utils.geom.response.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * An {@code RSPFlip} defines a collision response which flips the
 * directions of the distance and penetration vector, given
 * another response. All other variables are left as is.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.1
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
		Vector pen = rsp.Penetration();
		if(pen != null)
		{
			return pen.times(-1f);
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		Vector dst = rsp.Distance();
		if(dst != null)
		{
			return dst.times(-1f);
		}
		
		return null;
	}
	
	@Override
	public Point Contact()
	{
		return rsp.Contact();
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost();
	}
}