package waffles.utils.geom.collide.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code RSPFlipped} defines a collision response which
 * flips the direction of the distance and penetration vector
 * computed from another {@code Response}.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.1
 *
 *
 * @see Response
 */
public class RSPFlipped implements Response
{
	private Response rsp;
	private Vector dst, pnt;

	/**
	 * Creates a new {@code RSPFlipped}.
	 *
	 * @param r  a response
	 *
	 *
	 * @see Response
	 */
	public RSPFlipped(Response r)
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
		if(pnt == null)
		{
			pnt = rsp.Penetration();
			pnt = pnt.times(-1f);
		}
		
		return pnt;
	}

	@Override
	public Collidable Source()
	{
		return rsp.Source();
	}
	
	@Override
	public Vector Distance()
	{
		if(dst == null)
		{
			dst = rsp.Distance();
			dst = dst.times(-1f);
		}
		
		return dst;
	}

	@Override
	public Point Contact()
	{
		return rsp.Contact();
	}

	@Override
	public int cost()
	{
		return rsp.cost();
	}
}