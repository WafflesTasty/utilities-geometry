package waffles.utils.geom.response.hulls.cuboids;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.lines.HLine;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code ISCLine} computes an intersection response of a cuboid and a line.
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
	private HyperCuboid src;
	
	private Vector p, q;
	private Float lMin, lMax;
	private Boolean hasImpact;
	
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
		this(s, t, Floats.NEG_INFINITY, Floats.POS_INFINITY);
	}

	/**
	 * Creates a new {@code ISCLine}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target half-line
	 * 
	 * 
	 * @see HyperCuboid
	 * @see HLine
	 */
	public ISCLine(HyperCuboid s, HLine t)
	{
		this(s, t.Line(), Floats.POS_INFINITY);
	}
	
	/**
	 * Creates a new {@code ISCLine}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target line
	 * @param max  a maximum lambda
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Line
	 */
	public ISCLine(HyperCuboid s, Line t, float max)
	{
		this(s, t, 0f, max);
	}
	
	/**
	 * Creates a new {@code ISCLine}.
	 * 
	 * @param s  a source cuboid
	 * @param t  a target line
	 * @param min  a minimum lambda
	 * @param max  a maximum lambda
	 * 
	 * 
	 * @see HyperCuboid
	 * @see Line
	 */
	public ISCLine(HyperCuboid s, Line t, float min, float max)
	{
		lMin = min;
		lMax = max;
		
		src = s;
		tgt = t;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			if(p == null && q == null)
			{
				p = tgt.P1().Generator();
				q = tgt.P2().Generator();
				
				p = p.plus(q.minus(p).times(lMin));
				q = p.plus(q.minus(p).times(lMax));
			}
			
			return Geometries.Segment(p, q);
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
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		return null;
	}
	
	@Override
	public Point Contact()
	{
		if(p == null && q == null)
		{
			p = tgt.P1().Generator();
			q = tgt.P2().Generator();
			
			p = p.plus(q.minus(p).times(lMin));
			q = p.plus(q.minus(p).times(lMax));
		}
		
		return new Point(p, 1f);
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return 11 * dim;
	}
	
	
	Collidable computeShape()
	{
		if(hasImpact())
		{
			Vector p = tgt.P1().Generator();
			Vector q = tgt.P2().Generator();
			
			Vector r = p.plus(q.minus(p).times(lMin));
			Vector s = p.plus(q.minus(p).times(lMax));
			
			return Geometries.Segment(r, s);
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	boolean computeImpact()
	{		
		Vector s = src.Size();
		Vector o = src.Origin();
		int dim = src.Dimension();
		
		Vector p = tgt.P1().Generator();
		Vector q = tgt.P2().Generator();
		
		
		for(int k = 0; k < dim; k++)
		{
			float sk = s.get(k) / 2;
			float ok = o.get(k) - p.get(k);
			float qk = q.get(k) - p.get(k);
			
			float v1 = (ok - sk) / qk;
			float v2 = (ok + sk) / qk;
			
			
			float l1 = Floats.min(v1, v2);
			float l2 = Floats.max(v1, v2);
			
			lMin = Floats.max(lMin, l1);
			lMax = Floats.min(lMax, l2);
			
			if(lMax < lMin)
			{
				return false;
			}
		}
		
		return true;
	}
}