package waffles.utils.geom.collision.spaces.vector;

import waffles.utils.algebra.algorithms.leastsquares.LSQHouseHolder;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.Tall;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code CNTPoint} computes the collision response between a vector space and a point.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private Point tgt;
	private VSpace src;
	
	private Vector dst;
	private Boolean hasImpact;
	private LSQHouseHolder lsq;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source space
	 * @param p  a target point
	 * 
	 * 
	 * @see VSpace
	 * @see Point
	 */
	public CNTPoint(VSpace s, Point p)
	{
		src = s;
		tgt = p;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
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
		int dim = src.Dimension();
		return 2 * dim * (dim + 1);
	}
	
		
	Vector computeDistance()
	{
		Vector x = tgt.Generator();
		Matrix m = src.Generator();
		m.setOperator(Tall.Type());
		
		lsq = new LSQHouseHolder(m);
		Vector v = lsq.approx(x);
		
		return x.minus(m.times(v));
	}
	
	boolean computeImpact()
	{
		if(dst == null)
		{
			dst = computeDistance();
		}

		int dim = tgt.Dimension();
		return Floats.isZero(dst.normSqr(), 2*dim-1);
	}
}