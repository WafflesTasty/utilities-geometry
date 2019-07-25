package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.IGeometry;
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
		super(s);
	}
	
	
	@Override
	protected boolean contains(Point x)
	{
		ISegment s = Source();
		
		
		// Calculate the dot products.
		Vector qp = s.P2().minus(s.P1());
		Vector xp = x.minus(s.P2());
		
		float d1 = qp.dot(xp);
		float d2 = qp.dot(qp);
		
		// Check if 0 < lambda < 1.
		if(0 <= d1 && d1 <= d2)
		{
			qp = qp.times(d1);
			xp = xp.times(d2);
		
			// Check if lambda solves the linear system.
			int ulps = 4 + s.Dimension() / 2;
			return qp.equals(xp, ulps);
		}
		
		return false;
	}
	
	@Override
	protected ISegment Source()
	{
		return (ISegment) super.Source();
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		// Eliminate base collision cases.
		Boolean cnt = super.contains(c);
		if(cnt != null)
		{
			return cnt;
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return contains((ISegment) c);
		}
		
		// Other shapes have volume.
		return false;
	}
	
	@Override
	public Boolean inhabits(ICollidable c)
	{
		// Eliminate base collision cases.
		Boolean cnt = super.inhabits(c);
		if(cnt != null)
		{
			return cnt;
		}
		
		// Eliminate geometries.
		if(c instanceof IGeometry)
		{
			return inhabits((IGeometry) c); 
		}
		
		return null;
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
		// Eliminate base collision cases.
		Boolean isect = super.intersects(c);
		if(isect != null)
		{
			return isect;
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
		
		return null;
	}
	
	@Override
	public ICollidable intersect(ICollidable c)
	{
		// Eliminate base collision cases.
		ICollidable isect = super.intersect(c);
		if(isect != null)
		{
			return isect;
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
		
		return null;
	}

				
	private ICollidable intersect(Affine.Space a)
	{
		ISegment s = Source();
		
		
		VSpace dir = a.Direction();
		Vector qp = s.P2().minus(s.P1());
		Vector pr = s.P1().minus(a.Origin());
		// Create the affine intersection space.
		VSpace sum = VSpaces.create(dir.Span(), qp);
		Vector crd = sum.coordinates(pr);
		
		// If the linear system has no solution...
		if(crd == null)
		{
			// The intersection is empty.
			return new TrivialASpace();
		}
		
		// If the solution is not unique...
		if(dir.contains(qp))
		{
			// The entire segment is contained.
			return s;
		}
		
		// If the solution is inside the segment...
		float lbd = crd.get(crd.Size() - 1);
		if(0 <= lbd && lbd <= 1)
		{
			// Return the intersection point.
			return s.P1().plus(qp.times(lbd));
		}
		
		// Otherwise, the intersection is empty.
		return new TrivialASpace();
	}
	
	private ICollidable intersect(ISegment t)
	{
		ISegment s = Source();
		
		
		Vector qp = s.P2().minus(s.P1());
		Vector sr = t.P2().minus(t.P1());
		Vector pr = s.P1().minus(t.P1());
		Vector ps = s.P1().minus(t.P2());
		// Create the affine intersection space.
		VSpace sum = VSpaces.create(qp, sr);
		
		// If the linear system has no solution...
		Vector crd = sum.coordinates(pr);
		if(crd == null)
		{
			// The intersection is empty.
			return new TrivialASpace();
		}
				
		// If the solution is unique...
		if(sum.Dimension() == 2)
		{
			float lbd1 = crd.get(0);
			float lbd2 = crd.get(1);
			
			// And the solution is contained in both segments...
			if(0 <= lbd1 && lbd1 <= 1 && 0 <= lbd2 && lbd2 <= 1)
			{
				// Return the intersection point.
				return s.P1().plus(qp.times(lbd1));
			}
			
			// Otherwise, the intersection is empty.
			return new TrivialASpace();
		}
		
		
		float d1 =  ps.dot(sr);
		float d2 = -qp.dot(sr);
		float d3 =  pr.dot(pr);
		
		// Limit the solution to both segments.
		float lmin = Floats.max(Floats.min(d1 / d2, d3 / d2), 0);
		float lmax = Floats.min(Floats.max(d1 / d2, d3 / d2), 1);
		// If the solution is valid...
		if(lmin <= lmax)
		{
			// Return the intersection segment.
			Point p1 = s.P1().plus(qp.times(lmin));
			Point p2 = s.P1().plus(qp.times(lmax));
			return Generator.segment(p1, p2);
		}
		
		// Otherwise, the intersection is empty.		
		return new TrivialASpace();
	}
	
	
	private boolean intersects(Affine.Space a)
	{
		ISegment s = Source();
		
		
		VSpace dir = a.Direction();
		Vector pq = s.P2().minus(s.P1());
		Vector rp = s.P1().minus(a.Origin());
		// Create the affine intersection space.
		VSpace sum = VSpaces.create(dir.Span(), pq);
		// If the linear system has a solution...
		Vector coords = sum.coordinates(rp);
		if(coords != null)
		{
			// And the solution is not unique...
			if(dir.contains(pq))
			{
				// The entire segment is contained.
				return true;
			}
			
			// Otherwise, check if the solution is inside the segment.
			float lambda = coords.get(coords.Size() - 1);
			return 0 <= lambda && lambda <= 1;
		}
		
		// Otherwise the intersection is empty.
		return false;
	}
	
	private boolean intersects(ISegment t)
	{
		ISegment s = Source();
		
		
		Vector qp = s.P2().minus(s.P1());
		Vector sr = t.P2().minus(t.P1());
		Vector pr = s.P1().minus(t.P1());
		Vector ps = s.P1().minus(t.P2());
		// Create the affine intersection space.
		VSpace sum = VSpaces.create(qp, sr);
		
		// If the linear system has no solution...
		Vector crd = sum.coordinates(pr);
		if(crd == null)
		{
			// The intersection is empty.
			return false;
		}
				
		// If the solution is unique...
		if(sum.Dimension() == 2)
		{
			float lbd1 = crd.get(0);
			float lbd2 = crd.get(1);
			
			// Verify if the solution is contained in both segments.
			return 0 <= lbd1 && lbd1 <= 1
				&& 0 <= lbd2 && lbd2 <= 1;
		}
		
		
		float d1 =  ps.dot(sr);
		float d2 = -qp.dot(sr);
		float d3 =  pr.dot(pr);
		
		// Limit the solution to both segments.
		float lmin = Floats.max(Floats.min(d1 / d2, d3 / d2), 0);
		float lmax = Floats.min(Floats.max(d1 / d2, d3 / d2), 1);
		// Verify if the solution is valid.
		return lmin <= lmax;
	}
	
	
	private boolean inhabits(IGeometry g)
	{
		return g.contains(Source().P1())
			&& g.contains(Source().P2());
	}
	
	private boolean contains(ISegment t)
	{
		ISegment s = Source();
		
		
		Vector pq = s.P1().minus(s.P2());
		Vector sr = t.P2().minus(t.P1());
		Vector ps = s.P1().minus(t.P1());
		Vector pr = s.P1().minus(t.P2());
		
		VSpace sum = VSpaces.create(pq, sr);
		// If the segments are not collinear...
		if(sum.Dimension() == 2)
		{
			// Containment is impossible.
			return false;
		}
		
		// Check if the segment is entirely contained.
		return pq.dot(sr) <= pr.dot(pr)
			&& ps.dot(sr) <= 0;
	}
}