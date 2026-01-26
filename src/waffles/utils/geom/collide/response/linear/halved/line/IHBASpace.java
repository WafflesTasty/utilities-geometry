package waffles.utils.geom.collide.response.linear.halved.line;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.linear.halved.lines.HLine;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code IHBASpace} computes an inhabit {@code Response} between a halfline and an affine space.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class IHBASpace implements Response
{
	private HLine src;
	private Response rsp;

	/**
	 * Creates a new {@code IHBASpace}.
	 *
	 * @param s  a source line
	 * @param t  a target space
	 *
	 *
	 * @see ASpace
	 * @see HLine
	 */
	public IHBASpace(HLine s, ASpace t)
	{
		Line l = new Line(s.Factory());
		rsp = t.contain(l);
		src = s;
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
		return rsp.hasImpact();
	}

	@Override
	public Point Contact()
	{
		return src.Origin();
	}

	@Override
	public int cost()
	{
		return rsp.cost();
	}
}