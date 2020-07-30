package zeno.util.geom.collidables.collisions.geometry;

import java.util.List;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Geometries;
import zeno.util.geom.utilities.algorithms.LineClipper;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code CLSCuboid} class defines collision for an {@code ICuboid}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSCuboid extends CLSGeometry
{	
	private static final LineClipper clipper = new LineClipper();
	
	
	/**
	 * Creates a new {@code CLSCuboid}.
	 * 
	 * @param s  a source cuboid
	 * 
	 * 
	 * @see ICuboid
	 */
	public CLSCuboid(ICuboid s)
	{
		super(s);
	}
	
	
	@Override
	protected boolean contains(Point x)
	{
		ICuboid c = Source();
		
		float m = x.Mass();
		for(int i = 0; i < c.Dimension(); i++)
		{
			float xi = x.get(i);
			float si = c.Size().get(i);
			float pi = c.Center().get(i);
			
			if(si * Floats.abs(m) < 2 * Floats.abs(xi - m * pi))
			{
				return false;
			}
		}

		return true;
	}
	
	@Override
	protected ICuboid Source()
	{
		return (ICuboid) super.Source();
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
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return contains((ICuboid) c);
		}
		
		// Eliminate ellipsoids.
		if(c instanceof IEllipsoid)
		{
			return contains((IEllipsoid) c);
		}
		
		// Eliminate bounded shapes.
		if(c instanceof IBounded)
		{
			Bounds bounds = ((IBounded) c).Bounds();
			return contains(bounds.Box());
		}

		return null;
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
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return intersect((ICuboid) c);
		}
		
		return null;
	}
	
		
	private ICollidable intersect(ISegment s)
	{
		clipper.setBounds(Source());
		List<Vector> clip = clipper.clip(s);
		if(!clip.isEmpty())
		{
			Vector p1 = clip.get(0);
			Vector p2 = clip.get(1);
			
			return Geometries.segment(p1, p2);
		}
		
		return Geometries.VOID;
	}
	
	private ICollidable intersect(ICuboid d)
	{
		ICuboid c = Source();
		

		int dim = Integers.min(c.Dimension(), d.Dimension());
		Vector m = Vectors.create(dim); Vector n = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			float si = c.Size().get(i);
			float ti = d.Size().get(i);
			
			float pi = c.Center().get(i);
			float qi = d.Center().get(i);
			
			if(si + ti < 2 * Floats.abs(pi - qi))
			{
				return Geometries.VOID;
			}
			
			m.set(Floats.min(pi + si / 2, qi + ti / 2), i);
			n.set(Floats.max(pi - si / 2, qi - ti / 2), i);
		}
		
		Vector s = m.minus(n);
		Vector p = m.plus(n).times(0.5f);
		return Geometries.cuboid(p, s);
	}
	
	private ICollidable intersect(Line l)
	{
		ICuboid c = Source();
		
		
		Point r = l.Origin();
		Vector m1 = c.Minimum();
		Vector m2 = c.Maximum();
		Vector v = l.Vector();
			
		float m = r.Mass();
		float lmin = Floats.NEG_INFINITY;
		float lmax = Floats.POS_INFINITY;
		// For every axis-aligned dimension...
		for(int i = 0; i < c.Dimension(); i++)
		{
			float l1 = m * m1.get(i) - r.get(i);
			float l2 = m * m2.get(i) - r.get(i);
		
			// Compute the minimum and maximum lambda.
			lmin = Floats.max(lmin, l1 / (m * v.get(i)));
			lmax = Floats.min(lmax, l2 / (m * v.get(i)));
			
			// If the halfspaces don't intersect...
			if(lmax < lmin)
			{
				// The intersection is empty.
				return Geometries.VOID;
			}
		}
		
		// Otherwise, compute the intersecting segment.
		Vector p1 = r.plus(v.times(lmin)).asVector();
		Vector p2 = r.plus(v.times(lmax)).asVector();
		return Geometries.segment(p1, p2);
	}
	
	
	private boolean intersects(ISegment s)
	{
		clipper.setBounds(Source());
		return !clipper.clip(s).isEmpty();
	}
	
	private boolean intersects(ICuboid d)
	{
		ICuboid c = Source();


		int dim = Integers.min(c.Dimension(), d.Dimension());
		for(int i = 0; i < dim; i++)
		{
			float si = c.Size().get(i);
			float ti = d.Size().get(i);
			
			float pi = c.Center().get(i);
			float qi = d.Center().get(i);
			
			if(si + ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}
		
		return true;
	}

	private boolean intersects(Line l)
	{
		ICuboid c = Source();
		
		
		Point r = l.Origin();
		Vector m1 = c.Minimum();
		Vector m2 = c.Maximum();
		Vector v = l.Vector();
		
		float m = r.Mass();
		float lmin = Floats.NEG_INFINITY;
		float lmax = Floats.POS_INFINITY;
		// For every axis-aligned dimension...
		for(int i = 0; i < c.Dimension(); i++)
		{
			// Compute the minimum and maximum lambda.
			float l1 = m * m1.get(i) - r.get(i);
			float l2 = m * m2.get(i) - r.get(i);
			
			lmin = Floats.max(lmin, l1 / (m * v.get(i)));
			lmax = Floats.min(lmax, l2 / (m * v.get(i)));
			// If the halfspaces don't intersect...
			if(lmax < lmin)
			{
				// The intersection is empty.
				return false;
			}
		}
		
		// Otherwise, intersection occurs.
		return true;
	}

	
	private boolean inhabits(IGeometry g)
	{
		return g.contains(Source().Minimum())
			&& g.contains(Source().Maximum());
	}

	private boolean contains(IEllipsoid e)
	{
		return contains(new Point(e.Minimum(), 1f))
			&& contains(new Point(e.Maximum(), 1f));
	}
	
	private boolean contains(ICuboid d)
	{
		ICuboid c = Source();


		int dim = Integers.min(c.Dimension(), d.Dimension());
		for(int i = 0; i < dim; i++)
		{
			float si = c.Size().get(i);
			float ti = d.Size().get(i);
			
			float pi = c.Center().get(i);
			float qi = d.Center().get(i);
			
			if(si - ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}
		
		return true;
	}
}