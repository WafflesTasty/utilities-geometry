package zeno.util.geom.collidables.collisions.geometry;

import java.util.List;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.algorithms.LineClipper;
import zeno.util.geom.collidables.Affine;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.affine.spaces.TrivialASpace;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Generator;
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
	protected ICuboid Source()
	{
		return (ICuboid) super.Source();
	}
	
	
	@Override
	public Boolean contains(ICollidable c)
	{
		Boolean cnt = super.contains(c);
		if(cnt != null)
		{
			return cnt;
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
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return contains((ICuboid) c);
		}

		return null;
	}

	@Override
	public Boolean intersects(ICollidable c)
	{
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
		
		// Eliminate segments.
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
		
		// Eliminate segments.
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
	
	
	private ICollidable intersect(Line l)
	{
		ICuboid c = Source();
		
		
		Vector s = c.Size();
		Vector p = c.Center();
		Vector q = l.Origin().VMatrix();
		Vector v = (Vector) l.Direction().Span();
		
		// Compute the lambda boundaries.
		float lmin = Floats.NEG_INFINITY;
		float lmax = Floats.POS_INFINITY;
		for(int i = 0; i < c.Dimension(); i++)
		{
			float l1 = p.get(i) - q.get(i) - s.get(i) / 2;
			float l2 = p.get(i) - q.get(i) + s.get(i) / 2;
			
			lmin = Floats.max(lmin, l1 / v.get(i));
			lmax = Floats.min(lmin, l2 / v.get(i));
			if(lmax < lmin)
			{
				return new TrivialASpace();
			}
		}
		
		Vector p1 = q.plus(v.times(lmin));
		Vector p2 = q.plus(v.times(lmax));
		return Generator.segment(p1, p2);
	}
	
	private ICollidable intersect(ISegment s)
	{
		clipper.setBounds(Source());
		List<Vector> clip = clipper.clip(s);
		if(!clip.isEmpty())
		{
			Vector p1 = clip.get(0);
			Vector p2 = clip.get(1);
			
			return Generator.segment(p1, p2);
		}
		
		return new TrivialASpace();
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
				return new TrivialASpace();
			}
			
			m.set(Floats.min(pi + si / 2, qi + ti / 2), i);
			n.set(Floats.max(pi - si / 2, qi - ti / 2), i);
		}
		
		Vector s = m.minus(n);
		Vector p = m.plus( n).times(0.5f);
		return Generator.cuboid(p, s);
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
	
	private boolean contains(ISegment s)
	{
		return contains(s.P1())
			&& contains(s.P2());
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
	
	private boolean intersects(Line l)
	{
		ICuboid c = Source();
		
		
		Vector s = c.Size();
		Vector p = c.Center();
		Vector q = l.Origin().VMatrix();
		Vector v = (Vector) l.Direction().Span();
		
		// Compute the lambda boundaries.
		float lmin = Floats.NEG_INFINITY;
		float lmax = Floats.POS_INFINITY;
		for(int i = 0; i < c.Dimension(); i++)
		{
			float l1 = p.get(i) - q.get(i) - s.get(i) / 2;
			float l2 = p.get(i) - q.get(i) + s.get(i) / 2;
			
			lmin = Floats.max(lmin, l1 / v.get(i));
			lmax = Floats.min(lmin, l2 / v.get(i));
			if(lmax < lmin)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean contains(Point x)
	{
		ICuboid s = Source();
		
		
		for(int i = 0; i < s.Dimension(); i++)
		{
			float si = s.Size().get(i);
			float ci = s.Center().get(i);
			float xi = x.VMatrix().get(i);
			
			if(si < 2 * Floats.abs(xi - ci))
			{
				return false;
			}
		}

		return true;
	}
}