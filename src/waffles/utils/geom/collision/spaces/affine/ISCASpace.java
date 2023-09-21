package waffles.utils.geom.collision.spaces.affine;

import waffles.utils.algebra.algorithms.leastsquares.LSQHouseHolder;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
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

	private LSQHouseHolder lsq;
	
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
		
		
		lsq = new LSQHouseHolder(u.Generator());

		Vector x = lsq.approx(r);
		Vector y = (Vector) u.evaluate(x);
		
		if(Floats.isZero(r.distSqr(y), d1 + d2))
		{
			VSpace d = (VSpace) v.intersect(w).Shape();
			x = (Vector) v.evaluate(Vectors.resize(x, d1));
			shape = new ASpace(new Point(x, 1f), d);
			
			return true;
		}
		
		
		Vector x1 = Vectors.resize(x, d1);
		Vector x2 = Vectors.resize(x, d1, d2);

		x1 = (Vector) v.evaluate(x1);
		x2 = (Vector) w.evaluate(x2);
		
		dst = p.plus(x1).minus(q.plus(x2));
		
		return false;
	}
}