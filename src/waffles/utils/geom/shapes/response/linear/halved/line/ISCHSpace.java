package waffles.utils.geom.shapes.response.linear.halved.line;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code ISCHSpace} computes an intersection {@code Response} between a halfline and a halfspace.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class ISCHSpace implements Response
{
	private HLine src;
	private HSpace tgt;
	private Response rsp;

	/**
	 * Creates a new {@code ISCHSpace}.
	 *
	 * @param s  a source line
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 * @see HLine
	 */
	public ISCHSpace(HLine s, HSpace t)
	{
		Point p = s.Origin();
		rsp = t.contain(p);
		src = s; tgt = t;
	}


	@Override
	public HLine Source()
	{
		return src;
	}

	@Override
	public boolean hasImpact()
	{
		if(!rsp.hasImpact())
		{
			Vector v = src.Direction();
			Vector w = tgt.Normal();
			return v.dot(w) >= 0;
		}
		
		return true;
	}

	@Override
	public Vector Distance()
	{
		return rsp.Distance().times(-1f);
	}

	@Override
	public Point Contact()
	{
		Point c = src.Origin();
		if(!rsp.hasImpact())
		{
			Vector d = Distance();
			return c.plus(d);
		}
		
		return c;
	}

	@Override
	public int cost()
	{
		return rsp.cost() + 2 * Dimension() - 1;
	}
}