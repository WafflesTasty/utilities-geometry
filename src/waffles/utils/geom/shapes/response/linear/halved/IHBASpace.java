package waffles.utils.geom.shapes.response.linear.halved;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.halved.HSpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code IHBASpace} computes an inhabit {@code Response} between a halfspace and an affine space.
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
	private HSpace src;
	private ASpace tgt;

	/**
	 * Creates a new {@code IHBASpace}.
	 *
	 * @param s  a source space
	 * @param t  a target space
	 *
	 *
	 * @see HSpace
	 * @see ASpace
	 */
	public IHBASpace(HSpace s, ASpace t)
	{
		src = s;
		tgt = t;
	}


	@Override
	public HSpace Source()
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

		int n = Dimension();
		return new Void(n);
	}

	@Override
	public boolean hasImpact()
	{
		return tgt.rank() == Dimension();
	}

	@Override
	public Point Contact()
	{
		return tgt.Origin();
	}

	@Override
	public int cost()
	{
		Matrix s = tgt.Factory().Span();
		
		int k = s.Columns();
		int n = s.Rows();
		
		return (k + n) * (k + n)
			 * (2 * n - 1) * k;
	}
}