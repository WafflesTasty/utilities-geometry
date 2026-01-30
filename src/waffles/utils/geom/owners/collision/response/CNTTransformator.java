package waffles.utils.geom.owners.collision.response;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision.Response;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;

/**
 * A {@code CNTTransformator} computes a containment {@code Response} between a geometrical and a transformator.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class CNTTransformator implements Response
{
	private Response rsp;
	private Geometrical src;
	private Transformator tgt;
	private LinearMap map;
	
	/**
	 * Creates a new {@code CNTTransformator}.
	 * 
	 * @param s  a source geometrical
	 * @param t  a target transformator
	 * 
	 * 
	 * @see Transformator
	 * @see Geometrical
	 */
	public CNTTransformator(Geometrical s, Transformator t)
	{
		src = s;
		tgt = t;
		
		map = s.Transform();
		Affine a = map.unmap(tgt);
		Transformator x = (Transformator) a;
		Geometry g = src.Shape();
		rsp = g.contain(x);
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public boolean hasImpact()
	{
		return rsp.hasImpact();
	}
	
	@Override
	public Geometrical Source()
	{
		return src;
	}
		
	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			Vector p = rsp.Penetration();
			Affine a = map.map(p);
			return (Vector) a;		
		}

		int n = Dimension();
		return Vectors.create(n);
	}

	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			Vector d = rsp.Distance();
			Affine a = map.map(d);
			return (Vector) a;
		}
		
		int n = Dimension();
		return Vectors.create(n);
	}
	
	@Override
	public Point Contact()
	{
		Point c = rsp.Contact();
		if(c != null)
		{
			Affine a = map.map(c);
			if(a instanceof Point)
			{
				return (Point) a;
			}
		}

		int n = Dimension();
		return new Point(n);
	}
	
	@Override
	public int cost()
	{
		int n = Dimension();
		int c = rsp.cost();
		return c + n * n;
	}
}