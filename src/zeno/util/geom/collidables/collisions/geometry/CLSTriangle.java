package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.calc.optimize.LCLOptimizer;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.collidables.geometry.generic.ITriangle;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSTriangle} class defines collision for an {@code ITriangle}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSGeometry
 */
public class CLSTriangle extends CLSGeometry
{	
	private CLSConvex convex;
	
	/**
	 * Creates a new {@code CLSTriangle}.
	 * 
	 * @param s  a source triangle
	 * 
	 * 
	 * @see ITriangle
	 */
	public CLSTriangle(ITriangle s)
	{
		super(s); convex = new CLSConvex(s);
	}
	
	
	@Override
	protected boolean contains(Point x)
	{
		ITriangle t = Source();
		
		Vector obj = Vectors.create(0f, 0f, 0f);
		Matrix con = Matrices.create(t.Dimension() + 1, 4);
		for(int r = 0; r <= t.Dimension(); r++)
		{
			con.set(t.P1().Span().get(r), r, 0);
			con.set(t.P2().Span().get(r), r, 1);
			con.set(t.P3().Span().get(r), r, 2);
			con.set(x.Span().get(r), r, 3);
		}
		
		LCLOptimizer lcl = new LCLOptimizer(con, obj);
		return lcl.isFeasible();
	}
	
	@Override
	protected ITriangle Source()
	{
		return (ITriangle) super.Source();
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
		
		// Eliminate segments.
		if(c instanceof ISegment)
		{
			Point p1 = new Point(((ISegment) c).P1());
			Point p2 = new Point(((ISegment) c).P2());
			
			return contains(p1)
				&& contains(p2);
		}

		return convex.contains(c);
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
		
		return convex.inhabits(c);
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
		
		return convex.intersects(c);
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
		
		return convex.intersect(c);
	}
	
		
	private ICollidable intersect(ISegment s)
	{
		ITriangle t = Source();
		
		Vector p = s.P1();
		Vector v = s.P2().minus(s.P1());
		
		Vector obj = Vectors.create(0f, 0f, 0f, 1f);
		Matrix con = Matrices.create(t.Dimension() + 1, 5);
		for(int r = 0; r <= t.Dimension(); r++)
		{
			con.set(t.P1().Span().get(r), r, 0);
			con.set(t.P2().Span().get(r), r, 1);
			con.set(t.P3().Span().get(r), r, 2);
			
			if(r < t.Dimension())
			{
				con.set(-v.get(r), r, 3);
				con.set( p.get(r), r, 4);
			}
		}
		con.set(1f, 4, 4);
		

		LCLOptimizer lcl1 = new LCLOptimizer(con, obj);
		LCLOptimizer lcl2 = new LCLOptimizer(con, obj);
		
		Vector min = lcl1.minimize();
		if(min == null) return Geometries.VOID;
		Vector max = lcl2.maximize();
		
		// If the values fall outside the segment, return nothing.
		if(max.get(3) < 0f || 1f < min.get(3))
		{
			return Geometries.VOID;
		}
		
		
		float vMin = Floats.max(min.get(3), 0f);
		float vMax = Floats.min(max.get(3), 1f);
		
		if(Floats.isEqual(vMin, vMax, 3))
		{
			Vector q = v.times(0.5f * (vMin + vMax));
			return new Point(p.plus(q));
		}
		
		Vector q1 = p.plus(v.times(vMin));
		Vector q2 = p.plus(v.times(vMax));
		return Geometries.segment(q1, q2);
	}
	
	private ICollidable intersect(Line l)
	{
		ITriangle t = Source();
		
		Point p = l.Origin();
		Point v = new Point(l.Vector(), 0f);
		
		Vector obj = Vectors.create(0f, 0f, 0f, 1f / p.Mass());
		Matrix con = Matrices.create(t.Dimension() + 1, 5);
		for(int r = 0; r <= t.Dimension(); r++)
		{
			con.set(t.P1().Span().get(r), r, 0);
			con.set(t.P2().Span().get(r), r, 1);
			con.set(t.P3().Span().get(r), r, 2);
			
			con.set(-v.get(r), r, 3);
			con.set( p.get(r), r, 4);
		}
		

		LCLOptimizer lcl1 = new LCLOptimizer(con, obj);
		LCLOptimizer lcl2 = new LCLOptimizer(con, obj);
		
		Vector min = lcl1.minimize();
		if(min == null) return Geometries.VOID;
		Vector max = lcl2.maximize();
		
		float vMin = min.get(3);
		float vMax = max.get(3);
		
		if(Floats.isEqual(vMin, vMax, 3))
		{
			Point q = v.times(0.5f * (vMin + vMax));
			return p.plus(q.times(p.Mass()));
		}
		
		Point q1 = p.plus(v.times(vMin * p.Mass()));
		Point q2 = p.plus(v.times(vMax * p.Mass()));
		return Geometries.segment(q1, q2);
	}
	
	
	private boolean intersects(ISegment s)
	{
		ITriangle t = Source();
		
		Vector p = s.P1();
		Vector v = s.P2().minus(s.P1());
		
		Vector obj = Vectors.create(0f, 0f, 0f, 1f);
		Matrix con = Matrices.create(t.Dimension() + 1, 5);
		for(int r = 0; r <= t.Dimension(); r++)
		{
			con.set(t.P1().Span().get(r), r, 0);
			con.set(t.P2().Span().get(r), r, 1);
			con.set(t.P3().Span().get(r), r, 2);
			
			if(r < t.Dimension())
			{
				con.set(-v.get(r), r, 3);
				con.set( p.get(r), r, 4);
			}
		}
		con.set(1f, 4, 4);
		

		LCLOptimizer lcl1 = new LCLOptimizer(con, obj);
		LCLOptimizer lcl2 = new LCLOptimizer(con, obj);
		
		Vector min = lcl1.minimize();
		if(min == null) return false;
		Vector max = lcl2.maximize();
		
		return 0f <= max.get(3)
			&& min.get(3) <= 1f;
	}
	
	private boolean intersects(Line l)
	{
		ITriangle t = Source();
		
		Point p = l.Origin();
		Point v = new Point(l.Vector(), 0f);
		
		Vector obj = Vectors.create(0f, 0f, 0f, 1f / p.Mass());
		Matrix con = Matrices.create(t.Dimension() + 1, 5);
		for(int r = 0; r <= t.Dimension(); r++)
		{
			con.set(t.P1().Span().get(r), r, 0);
			con.set(t.P2().Span().get(r), r, 1);
			con.set(t.P3().Span().get(r), r, 2);
			
			con.set(-v.get(r), r, 3);
			con.set( p.get(r), r, 4);
		}
		

		LCLOptimizer lcl = new LCLOptimizer(con, obj);
		return lcl.isFeasible();
	}

	
	private boolean inhabits(IGeometry g)
	{
		return g.contains(Source().P1())
			&& g.contains(Source().P2())
			&& g.contains(Source().P3());
	}
}