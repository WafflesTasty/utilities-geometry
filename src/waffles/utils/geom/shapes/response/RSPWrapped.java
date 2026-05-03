package waffles.utils.geom.shapes.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code RSPWrapped} defines a collision response which
 * acts as a wrapper around another {@code Response}.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.1
 *
 *
 * @see Response
 */
public class RSPWrapped implements Response
{
	private Response rsp;

	/**
	 * Creates a new {@code RSPWrapped}.
	 *
	 * @param r  a response
	 *
	 *
	 * @see Response
	 */
	public RSPWrapped(Response r)
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
		return rsp.Penetration();
	}

	@Override
	public Collidable Source()
	{
		return rsp.Source();
	}
	
	@Override
	public Vector Distance()
	{
		return rsp.Distance();
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