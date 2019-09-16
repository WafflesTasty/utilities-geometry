package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSSphere} class defines collision for an {@code ISphere}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSSphere extends CLSGeometry
{	
	/**
	 * Creates a new {@code CLSSphere}.
	 * 
	 * @param s  a source ellipsoid
	 * 
	 * 
	 * @see ISphere
	 */
	public CLSSphere(ISphere s)
	{
		super(s);
	}
	
	
	@Override
	protected boolean contains(Point x)
	{
		ISphere s = Source();
		

		float rad = s.Radius();
		Vector p = x.asVector().minus(s.Center());
		int dim = s.Dimension();
		
		float sum = 0f;
		for(int i = 0; i < dim; i++)
		{
			sum += Floats.pow(p.get(i), 2);
			if(sum > rad * rad)
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	protected ISphere Source()
	{
		return (ISphere) super.Source();
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
		
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			return contains((ISphere) c);
		}
		
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return contains((IEllipsoid) c);
		}
		
		// Other shapes have volume.
		return false;
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
		
		// Eliminate affine lines.
		if(c instanceof Line)
		{
			return intersects((Line) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersects((ISegment) c);
		}
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return intersects((ICuboid) c);
		}
		
		// Eliminate spheres.
		if(c instanceof ISphere)
		{
			return intersects((ISphere) c);
		}
		
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return intersects((IEllipsoid) c);
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

		// Eliminate affine lines.
		if(c instanceof Line)
		{
			return intersect((Line) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return intersect((ISegment) c);
		}
		
		return null;
	}
	
	
	private ICollidable intersect(ISegment t)
	{
		ISphere s = Source();
		
		
		Vector o = s.Center();
		int dim = s.Dimension();
		float rad = s.Radius();
		
		Vector p = t.P1();
		Vector q = t.P2();
		
		Vector qp = q.minus(p);
		Vector po = p.minus(o);
		
		float d1 = qp.dot(po);
		float d2 = qp.dot(qp);
		float d3 = po.dot(po);
		
		// Compute the quadratic discriminant.
		float disc = d1 * d1 - d2 * (d3 - rad * rad);
		
		// If the discriminant is negative...
		if(disc < 0)
		{
			// The intersection is empty.
			return Geometries.VOID;
		}
		
		// If the discriminant is zero...
		if(Floats.isZero(disc, 5 + dim / 2))
		{
			// And the solution is contained in the segment...
			if(0 <= -d1 && -d1 <= d2)
			{
				// Return the intersection point.
				Vector v = p.plus(qp.times(-d1 / d2));
				return new Point(v, 1f);
			}
			
			// Otherwise, the intersection is empty.
			return Geometries.VOID;
		}
		
		// Limit the solution to the segment.
		float l1 = Floats.max((-d1 - Floats.sqrt(disc)) / d2, 0);
		float l2 = Floats.min((-d1 + Floats.sqrt(disc)) / d2, 1);
		// If the solution is contained in the segment...
		if(l1 <= l2)
		{
			// return the intersection segment.
			Vector p1 = p.plus(qp.times(l1));
			Vector p2 = p.plus(qp.times(l2));
			return Geometries.segment(p1, p2);
		}
		
		// Otherwise, the intersection is empty.
		return Geometries.VOID;
	}
	
	private ICollidable intersect(Line l)
	{
		ISphere s = Source();
		
		
		Point r = l.Origin();
		Vector p = s.Center();
		int dim = s.Dimension();
		float rad = s.Radius();
		Vector v = l.Vector();
		
		
		Vector rp = r.asVector().minus(p);
		float d1 = rp.dot(v); float d2 = rp.dot(rp);		
		// Compute the quadratic discriminant.
		float disc = d1 * d1 - d2 + rad * rad;
		
		// If the discriminant is negative...
		if(disc < 0)
		{
			// The intersection is empty.
			return Geometries.VOID;
		}
		
		// If the discriminant is zero...
		if(Floats.isZero(disc, 4 + dim / 2))
		{
			// The intersection is a point.
			return r.plus(v.times(-d1));
		}
		
		// Otherwise, the intersection is a segment.
		float l1 = -d1 - Floats.sqrt(disc);
		float l2 = -d1 + Floats.sqrt(disc);
		Vector p1 = p.plus(v.times(l1));
		Vector p2 = p.plus(v.times(l2));
		return Geometries.segment(p1, p2);
	}
	
	
	private boolean intersects(IEllipsoid e)
	{
		throw new Geometries.IntersectingError(Source(), e);
	}
	
	private boolean intersects(ISegment t)
	{
		ISphere s = Source();
		
		
		Vector o = s.Center();
		int dim = s.Dimension();
		float rad = s.Radius();
		
		Vector p = t.P1();
		Vector q = t.P2();
		
		Vector qp = q.minus(p);
		Vector po = p.minus(o);
		
		float d1 = qp.dot(po);
		float d2 = qp.dot(qp);
		float d3 = po.dot(po);
		
		// Compute the quadratic discriminant.
		float disc = d1 * d1 - d2 * (d3 - rad * rad);
		
		// If the discriminant is negative...
		if(disc < 0)
		{
			// The intersection is empty.
			return false;
		}
		
		// If the discriminant is zero...
		if(Floats.isZero(disc, 5 + dim / 2))
		{
			// Verify if the solution is contained in the segment.
			return 0 <= -d1 && -d1 <= d2;
		}
		
		// Limit the solution to the segment.
		float l1 = Floats.max((-d1 - Floats.sqrt(disc)) / d2, 0);
		float l2 = Floats.min((-d1 + Floats.sqrt(disc)) / d2, 1);
		// Verify if the solution is contained in the segment.
		return l1 <= l2;
	}
	
	private boolean intersects(ICuboid c)
	{
		ISphere s = Source();
		
		
		Vector r = c.Size();
		Vector p = c.Center();
		Vector o = s.Center();
		int dim = s.Dimension();
		float rad = s.Radius();
		
		float sum = 0f;
		for(int i = 0; i < dim; i++)
		{
			float val = Floats.abs(o.get(i) - p.get(i));
			val = Floats.max(val - r.get(i) / 2, 0f);
			sum += Floats.pow(val, 2);
			if(sum > rad * rad)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean intersects(ISphere t)
	{
		ISphere s = Source();
		
		
		Vector p1 = s.Center(); Vector p2 = t.Center();
		float r1  = s.Radius(); float r2  = t.Radius();
		
		return p2.minus(p1).normSqr() <= (r1 + r2) * (r1 + r2);
	}
	
	private boolean intersects(Line l)
	{
		ISphere s = Source();
		
		
		Point r = l.Origin();
		Vector p = s.Center();
		float rad = s.Radius();
		Vector v = l.Vector();
		
	
		Vector rp = r.asVector().minus(p);
		float d1 = rp.dot( v);
		float d2 = rp.dot(rp);		
		// Compute the quadratic discriminant.
		float disc = d1 - d2 + rad * rad;
		// Intersection depends on its sign.
		return disc >= 0;
	}
	
	
	private boolean contains(IEllipsoid f)
	{
		throw new Geometries.IntersectingError(Source(), f);
	}
	
	private boolean contains(ISphere t)
	{
		ISphere s = Source();
		
		
		Vector p1 = s.Center(); Vector p2 = t.Center();
		float r1  = s.Radius(); float r2  = t.Radius();
		
		return p2.minus(p1).norm() <= r2 - r1;
	}
}