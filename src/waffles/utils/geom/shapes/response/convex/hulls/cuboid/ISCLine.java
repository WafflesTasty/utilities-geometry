package waffles.utils.geom.shapes.response.convex.hulls.cuboid;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCLine} computes an intersection {@code Response} between a cuboid and a line.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 *
 *
 * @see Response
 */
public class ISCLine implements Response
{	
	private Line tgt;
	private Float l, m;
	private Vector v, w;
	
	private HyperCuboid src;

	/**
	 * Creates a new {@code ISCLine}.
	 *
	 * @param s  a source cuboid
	 * @param t  a target line
	 *
	 *
	 * @see HyperCuboid
	 * @see Line
	 */
	public ISCLine(HyperCuboid s, Line t)
	{
		src = s;
		tgt = t;
	}
	
	
	/**
	 * Returns an intersection {@code Point} x.
	 * This represents the first point in
	 * the intersection segment.
	 * 
	 * @return  an intersection point
	 * 
	 * 
	 * @see Point
	 */
	public Point X()
	{
		Point p = tgt.P1();
		Point q = tgt.P2();
		
		Point o = q.minus(p);
		return p.plus(o.times(L()));
	}
	
	/**
	 * Returns an intersection {@code Point} y.
	 * This represents the second point in
	 * the intersection segment.
	 * 
	 * @return  an intersection point
	 * 
	 * 
	 * @see Point
	 */
	public Point Y()
	{
		Point p = tgt.P1();
		Point q = tgt.P2();
		
		Point o = q.minus(p);
		return p.plus(o.times(M()));
	}
	
	/**
	 * Returns a source {@code Lambda}.
	 * This is the coefficient for the first
	 * point in the intersection segment.
	 * 
	 * @return  a lambda value
	 */
	public float L()
	{
		if(l == null)
		{
			compute();
			int n = Dimension();
			
			l = Floats.MIN_VALUE;
			for(int k = 0; k < n; k++)
			{
				float vk = v.get(k);
				l = Floats.max(l, vk);
			}
		}
		
		return l;
	}
	
	/**
	 * Returns a target {@code Mu}.
	 * This is the coefficient for the second
	 * point in the intersection segment.
	 * 
	 * @return  a mu value
	 */
	public float M()
	{
		if(m == null)
		{
			compute();
			int n = Dimension();
			
			l = Floats.MIN_VALUE;
			for(int k = 0; k < n; k++)
			{
				float vk = v.get(k);
				l = Floats.max(l, vk);
			}
		}
		
		return m;
	}


	private void compute()
	{
		if(v == null && w == null)
		{
			Point p = tgt.P1();
			Point q = tgt.P2();
			
			Point s = src.Scale().times(0.5f);
			Point op = src.Origin().minus(tgt.P1());
			Point qp = tgt.P2().minus(tgt.P1());
			
			
			v = op.minus(s).Vector();
			w = op.plus( s).Vector();

			for(int k = 0; k < Dimension(); k++)
			{
				float rk = qp.aff(k);
				float vk = v.get(k) / rk;
				float wk = w.get(k) / rk;
				
				if(vk <= wk)
				{
					v.set(vk, k);
					w.set(wk, k);
				}
				else
				{
					v.set(wk, k);
					w.set(vk, k);					
				}
			}
		}
	}

	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return Segment.create(X(), Y());
		}

		int n = Dimension();
		return new Void(n);
	}
	
	@Override
	public HyperCuboid Source()
	{
		return src;
	}

	@Override
	public boolean hasImpact()
	{
		return L() <= M();
	}

	@Override
	public Point Contact()
	{
		return X();
	}

	@Override
	public int cost()
	{
		int n = Dimension();
		return 5 * n + n * (n - 1);
	}
}