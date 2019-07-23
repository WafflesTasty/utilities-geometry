package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Generator;
import zeno.util.tools.Floats;

/**
 * The {@code CLSSegment} class defines collision for an {@code ISegment}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSSegment extends CLSGeometry
{
	private ISegment s;
	
	/**
	 * Creates a new {@code CLSSegment}.
	 * 
	 * @param s  a source segment
	 * 
	 * 
	 * @see ISegment
	 */
	public CLSSegment(ISegment s)
	{
		this.s = s;
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		Point p1 = s.P1();
		Point p2 = s.P2();
		
		// Eliminate degenerate segment.
		if(p1.equals(p2, 1))
		{
			return c.equals(p1, 1);
		}
		
		// Eliminate isolated point sets.
		if(c instanceof Affine.Set)
		{
			Affine.Set set = (Affine.Set) c;
			for(Point p : set)
			{
				if(!contains(p))
				{
					return false;
				}
			}
			
			return true;
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return contains((ISegment) c);
		}

		return super.contains(c);
	}

	@Override
	public Boolean intersects(ICollidable c)
	{
		Point p1 = s.P1();
		Point p2 = s.P2();
		
		// Eliminate degenerate segment.
		if(p1.equals(p2, 1))
		{
			return c.equals(p1, 1);
		}
		
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			return intersects((Affine.Space) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersects((ISegment) c);
		}
		
		return super.intersects(c);
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		Point p1 = s.P1();
		Point p2 = s.P2();
		
		// Eliminate degenerate segment.
		if(p1.equals(p2, 1))
		{
			return c.intersect(p1);
		}
		
		// Eliminate affine spaces.
		if(c instanceof Affine.Space)
		{
			return intersect((Affine.Space) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersect((ISegment) c);
		}
		
		return super.intersect(c);
	}
	
	
	private ICollidable intersect(ISegment t)
	{
		// Eliminate degenerate segment.
		if(t.P1().equals(t.P2(), 1))
		{
			return s.intersect(t.P1());
		}
		
		
		Vector pq = s.P2().minus(s.P1());
		Vector rs = t.P2().minus(t.P1());
		Vector rp = s.P1().minus(t.P1());
		Vector sp = s.P1().minus(t.P2());
		
		VSpace sum = VSpaces.create(pq, rs);
		// Check if a solution exists on the affine lines.
		Vector coords = sum.coordinates(rp);
		if(coords == null)
		{
			return new TrivialASpace();
		}
		
		
		float lbd1 = coords.get(0);
		float lbd2 = coords.get(1);
		
		// Check if the solution is unique.
		if(sum.Dimension() == 2)
		{
			// Find the intersecting point.
			if(0 <= lbd1 && lbd1 <= 1 && 0 <= lbd2 && lbd2 <= 1)
			{
				return s.P1().plus(pq.times(lbd1));
			}
			
			return new TrivialASpace();
		}
		
		
		float d1 =  sp.dot(rs);
		float d2 = -pq.dot(rs);
		float d3 =  rp.dot(rp);
		
		// Find the intersecting segment.
		float lmin = Floats.max(Floats.min(d1 / d2, d3 / d2), 0);
		float lmax = Floats.min(Floats.max(d1 / d2, d3 / d2), 1);
		if(lmin <= lmax)
		{
			Point p1 = s.P1().plus(pq.times(lmin));
			Point p2 = s.P1().plus(pq.times(lmax));
			return Generator.segment(p1, p2);
		}
		
		return new TrivialASpace();
	}
	
	private ICollidable intersect(Affine.Space a)
	{
		VSpace dir = a.Direction();
		Vector pq = s.P2().minus(s.P1());
		Vector rp = s.P1().minus(a.Origin());
		VSpace sum = VSpaces.create(dir.Span(), pq);
		
		// Check if a solution exists on the affine line.
		Vector coords = sum.coordinates(rp);
		if(coords == null)
		{
			return new TrivialASpace();
		}
		
		// Check if the solution is unique.
		if(dir.contains(pq))
		{
			return s;
		}
		
		// Check if 0 < lambda < 1.
		float lambda = coords.get(coords.Size() - 1);
		if(0 <= lambda && lambda <= 1)
		{
			return s.P1().plus(pq.times(lambda));
		}
		
		return new TrivialASpace();
	}
	
	private boolean intersects(Affine.Space a)
	{
		VSpace dir = a.Direction();
		Vector pq = s.P2().minus(s.P1());
		Vector rp = s.P1().minus(a.Origin());
		VSpace sum = VSpaces.create(dir.Span(), pq);
		
		// Check if a solution exists on the affine line.
		Vector coords = sum.coordinates(rp);
		if(coords != null)
		{
			// Check if the solution is unique.
			if(dir.contains(pq))
			{
				return true;
			}
			
			// Check if 0 < lambda < 1.
			float lambda = coords.get(coords.Size() - 1);
			return 0 <= lambda && lambda <= 1;
		}
		
		return false;
	}
	
	private boolean intersects(ISegment t)
	{
		// Eliminate degenerate segment.
		if(t.P1().equals(t.P2(), 1))
		{
			return s.intersects(t.P1());
		}
		
		
		Vector pq = s.P2().minus(s.P1());
		Vector rs = t.P2().minus(t.P1());
		Vector rp = s.P1().minus(t.P1());
		Vector sp = s.P1().minus(t.P2());
		
		VSpace sum = VSpaces.create(pq, rs);
		// Check if a solution exists on the affine lines.
		Vector coords = sum.coordinates(rp);
		if(coords == null)
		{
			return false;
		}
		
		
		float lbd1 = coords.get(0);
		float lbd2 = coords.get(1);
		
		// Check if the solution is unique.
		if(sum.Dimension() == 2)
		{
			return 0 <= lbd1 && lbd1 <= 1
				&& 0 <= lbd2 && lbd2 <= 1;
		}
		
		
		float d1 =  sp.dot(rs);
		float d2 = -pq.dot(rs);
		float d3 =  rp.dot(rp);
		
		// Check if an intersecting segment exists.
		float lmin = Floats.max(Floats.min(d1 / d2, d3 / d2), 0);
		float lmax = Floats.min(Floats.max(d1 / d2, d3 / d2), 1);
		return lmin <= lmax;
	}
	
	private boolean contains(ISegment t)
	{
		// Eliminate degenerate segment.
		if(t.P1().equals(t.P2(), 1))
		{
			return s.contains(t.P1());
		}
		
		
		Vector qp = s.P1().minus(s.P2());
		Vector rs = t.P2().minus(t.P1());
		Vector sp = s.P1().minus(t.P2());
		
		VSpace sum = VSpaces.create(qp, rs);
		// Check if the segments are colinear.
		if(sum.Dimension() == 2)
		{
			return false;
		}
		
		
		float d1 = qp.dot(rs);
		float d2 = qp.dot(sp);
		float d3 = qp.dot(qp);
		
		// Check if the segment is entirely contained.
		return 0 <= d1 && d1 <= d3
			&& 0 <= d2 && d2 <= d3;
	}
	
	private boolean contains(Point x)
	{
		// Calculate the dot products.
		Vector pq = s.P2().minus(s.P1());
		Vector px = s.P2().minus(x);
		
		float d1 = pq.dot(px);
		float d2 = pq.dot(pq);
		
		// Check if 0 < lambda < 1.
		if(0 <= d1 && d1 <= d2)
		{
			pq = pq.times(d1);
			px = px.times(d2);
		
			// Check if lambda solves the linear system.
			int ulps = 4 + s.Dimension() / 2;
			return pq.equals(px, ulps);
		}
		
		return false;
	}
}