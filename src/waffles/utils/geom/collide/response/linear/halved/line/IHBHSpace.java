package waffles.utils.geom.collide.response.linear.halved.line;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code IHBHSpace} computes an inhabit {@code Response} between a halfline and a halfspace.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class IHBHSpace implements Response
{
	private HLine src;
	private HSpace tgt;
	private Response rsp;
	private Float dot;
	
	/**
	 * Creates a new {@code IHBHSpace}.
	 *
	 * @param s  a source line
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 * @see HLine
	 */
	public IHBHSpace(HLine s, HSpace t)
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
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
		}

		int dim = Dimension();
		return new Void(dim);
	}

	@Override
	public boolean hasImpact()
	{
		if(rsp.hasImpact())
		{
			if(dot == null)
			{
				Vector v = src.Direction();
				Vector w = tgt.Normal();
				dot = v.dot(w);
			}
			
			return dot >= 0;
		}
		
		return false;
	}

	@Override
	public Point Contact()
	{
		return src.Origin();
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		return rsp.cost() + 2 * n - 1;
	}
}