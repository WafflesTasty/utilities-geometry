package waffles.utils.geom.response.spaces.affine;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.square.types.LSHouseholder;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code ISCASpace} computes the intersection response between affine spaces.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCASpace implements Response
{
	private Vector dst;
	private ASpace shape;
	private ASpace src, tgt;
	private Boolean hasImpact;

	private LSHouseholder lsq;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 */
	public ISCASpace(ASpace s, ASpace t)
	{
		src = s;
		tgt = t;
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 */
	public ISCASpace(ASpace s, VSpace t)
	{
		this(s, ASpace.Default(t));
	}

	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return shape;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}

	@Override
	public boolean hasImpact()
	{			
		if(hasImpact == null)
		{
			hasImpact = computeImpact();
		}
		
		return hasImpact;
	}
	
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			int dim = src.Dimension();
			return Vectors.create(dim);
		}
		
		return null;
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return dst;
		}

		return null;
	}
	
	@Override
	public Point Contact()
	{
		return null;
	}
	
	@Override
	public int Cost()
	{
		int rDim = src.Dimension();
		int sDim = src.Generators();
		int tDim = tgt.Generators();

		return Integers.pow(rDim * (sDim + tDim), 4);
	}
	
	
	boolean computeImpact()
	{
		int d1 = src.Generators();
		int d2 = tgt.Generators();
		
		Vector p = src.Origin().Generator();
		Vector q = tgt.Origin().Generator();
		Vector r = q.minus(p);
		
		VSpace v = src.Direction();
		VSpace w = tgt.Direction();
		VSpace u = v.add(w);
		
		
		lsq = new LSHouseholder(u.Generator());

		Vector x = lsq.approx(r);
		Vector y = (Vector) u.evaluate(x);
		
		if(Floats.isZero(r.distSqr(y), d1 + d2))
		{
			VSpace d = (VSpace) v.intersect(w).Shape();
			x = (Vector) v.evaluate(x.resize(d1));
			shape = new ASpace(new Point(x, 1f), d);
			
			return true;
		}
		
		
		Vector x1 = x.resize(d1);
		Vector x2 = Vectors.create(d2);
		for(int k = 0; k < d2; k++)
		{
			float val = x.get(d1 + k);
			x2.set(val, k);
		}

		x1 = (Vector) v.evaluate(x1);
		x2 = (Vector) w.evaluate(x2);
		
		dst = p.plus(x1).minus(q.plus(x2));
		
		return false;
	}
}