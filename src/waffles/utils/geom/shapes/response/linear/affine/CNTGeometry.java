package waffles.utils.geom.shapes.response.linear.affine;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CNTGeometry} computes a containment {@code Response} between an affine space and a geometry.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class CNTGeometry implements Response
{
	private ASpace src;
	private Geometry tgt;
	private Response rsp;
	private Boolean hasImpact;

	/**
	 * Creates a new {@code CNTGeometry}.
	 *
	 * @param s  a source space
	 * @param t  a target geometry
	 *
	 *
	 * @see Geometry
	 * @see ASpace
	 */
	public CNTGeometry(ASpace s, Geometry t)
	{
		rsp = s.contain(t.Origin());
		src = s; tgt = t;
	}


	@Override
	public ASpace Source()
	{
		return src;
	}

	@Override
	public boolean hasImpact()
	{
		if(hasImpact == null)
		{
			hasImpact = rsp.hasImpact();
			if(hasImpact)
			{
				Point p = tgt.Origin();
				int n = tgt.Dimension();

				for(int k = 0; k < n; k++)
				{
					Vector e = Vectors.unit(k, n);
					if(!src.contains(p.plus(e)))
					{
						hasImpact = false;
						break;
					}
				}
			}
		}

		return hasImpact;
	}

	@Override
	public Point Contact()
	{
		return tgt.Origin();
	}

	@Override
	public int cost()
	{
		int dim = tgt.Dimension();
		return dim * rsp.cost();
	}
}