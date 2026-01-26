package waffles.utils.geom.collide.response.convex.spheres;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code ISCLSpace} computes an intersection {@code Response} between a sphere and an affine space.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 *
 *
 * @see Response
 */
public class ISCASpace implements Response
{
	private ASpace tgt;
	private Response rsp;
	private HyperSphere src;

	/**
	 * Creates a new {@code ISCLSpace}.
	 *
	 * @param s  a source sphere
	 * @param t  a target space
	 *
	 *
	 * @see HyperSphere
	 * @see ASpace
	 */
	public ISCASpace(HyperSphere s, ASpace t)
	{
		rsp = t.contain(s.Origin());
		src = s; tgt = t;
	}


	@Override
	public boolean hasImpact()
	{
		if(rsp.hasImpact())
		{
			return true;
		}

		float r = src.Radius();
		Vector dst = rsp.Distance();
		float nsq = dst.normSqr();
		return nsq < r * r;
	}
	
	@Override
	public HyperSphere Source()
	{
		return src;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			float r = src.Radius();
			if(rsp.hasImpact())
			{
				RRSVD svd = tgt.SVD();
				Matrix c = svd.Complement();
				return c.Column(0).times(r);
			}
			
			Vector dst = rsp.Distance();
			float d = dst.norm();
			
			return dst.times((r - d) / d);
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector dst = rsp.Distance();
			float r = src.Radius();
			float d = dst.norm();
			
			return dst.times((r - d) / d);
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Point Contact()
	{
		Point c = src.Origin();
		if(rsp.hasImpact())
		{
			return c;
		}
		
		Vector d = rsp.Distance();
		return c.plus(d);
	}

	@Override
	public int cost()
	{
		return 2 * Dimension() + rsp.cost();
	}
}