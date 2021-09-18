package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.calc.optimize.LCLOptimizer;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
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
 * @see CLSHull
 */
public class CLSTriangle extends CLSHull
{	
	/**
	 * The {@code RSPSegment} class defines collision response for a segment.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPSegment implements Response
	{
		private ISegment s;
		private ICollidable shape;
		private Response rsp;
		
		/**
		 * Creates a new {@code RSPSegment}.
		 * 
		 * @param s  a target segment
		 * 
		 * 
		 * @see ISegment
		 */
		public RSPSegment(ISegment s)
		{
			this.s = s;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Shape().equals(Geometries.VOID);
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
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
				if(min == null)
				{
					shape = Geometries.VOID;
					return shape;
				}
				
				Vector max = lcl2.maximize();
				// If the values fall outside the segment, return nothing.
				if(max.get(3) < 0f || 1f < min.get(3))
				{
					shape = Geometries.VOID;
					return shape;
				}
				
				
				float vMin = Floats.max(min.get(3), 0f);
				float vMax = Floats.min(max.get(3), 1f);
				
				if(Floats.isEqual(vMin, vMax, 3))
				{
					Vector q = v.times(0.5f * (vMin + vMax));
					shape = new Point(p.plus(q));
					return shape;
				}
				
				Vector q1 = p.plus(v.times(vMin));
				Vector q2 = p.plus(v.times(vMax));
				shape = Geometries.segment(q1, q2);
			}

			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(s);
			}
			
			return rsp.Penetration();
		}
		
		@Override
		public Vector Distance()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(s);
			}
			
			return rsp.Distance();
		}
	}
	
	/**
	 * The {@code RSPPoint} class defines collision response for a point.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPPoint implements Response
	{
		private Point x;
		private Vector dst;
		private Boolean isEmpty;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param p  a target point
		 * 
		 * 
		 * @see Point
		 */
		public RSPPoint(Point p)
		{
			x = p;
		}
		
		
		@Override
		public boolean isEmpty()
		{			
			if(isEmpty == null)
			{
				int dim = Source().Dimension();
				Vector c = Source().Center();
				Vector s = Source().Size();
				
				
				float m = x.Mass();
				for(int i = 0; i < dim; i++)
				{
					float xi = x.get(i);
					float si = s.get(i);
					float pi = c.get(i);
					
					if(si * Floats.abs(m) < 2 * Floats.abs(xi - m * pi))
					{
						isEmpty = true;
						return isEmpty;
					}
				}

				isEmpty = false;
			}
			
			return isEmpty;
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;
			return x;
		}

		@Override
		public Vector Penetration()
		{
			if(!isEmpty()) return null;
			if(dst == null)
			{
				dst = compute();
			}
			
			return dst;
		}
		
		@Override
		public Vector Distance()
		{
			if(!isEmpty()) return null;
			if(dst == null)
			{
				dst = compute();
			}
			
			return dst;
		}
		
		Vector compute()
		{
			Vector p0 = x.asVector();
			Vector p1 = Source().P1().asVector();
			Vector p2 = Source().P2().asVector();
			Vector p3 = Source().P3().asVector();
			
			Vector v = p2.minus(p1);
			Vector w = p3.minus(p1);
			Vector p = p1.minus(p0);
			
			
			float vv = v.dot(v), ww = w.dot(w), vw = v.dot(w);
			float pv = p.dot(v), pw = p.dot(w);
			float dn = vv * ww - vw * vw;
			
			if(Floats.isZero(dn, p.Size()))
			{
				float c1 = Floats.clamp(-pv / vv, 0f, 1f);
				float c2 = Floats.clamp(-pw / ww, 0f, 1f);
				
				Vector x1 = p.plus(v.times(c1));
				Vector x2 = p.plus(w.times(c2));
				
				if(x1.dot(x1) < x2.dot(x2))
					dst = x1;
				else
					dst = x2;
				return dst;
			}
			
			float c1 = (pw * vw - pv * ww) / dn;
			float c2 = (pv * vw - pw * vv) / dn;
			
			dst = p.plus(v.times(c1));
			dst = dst.plus(w.times(c2));
			dst = dst.minus(p0);
			return dst;
		}
	}
	
	/**
	 * The {@code RSPLine} class defines collision response for a line.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPLine implements Response
	{
		private Line line;
		private ICollidable shape;
		
		/**
		 * Creates a new {@code RSPLine}.
		 * 
		 * @param line  a target line
		 * 
		 * 
		 * @see Line
		 */
		public RSPLine(Line line)
		{
			this.line = line;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Geometries.VOID.equals(Shape());
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				ITriangle t = Source();
				
				Point p = line.Origin();
				Point v = line.Vector();
				
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
				if(min == null)
				{
					shape = Geometries.VOID;
					return shape;
				}
				
				
				Vector max = lcl2.maximize();
				
				float vMin = min.get(3);
				float vMax = max.get(3);
				
				if(Floats.isEqual(vMin, vMax, 3))
				{
					Point q = v.times(0.5f * (vMin + vMax));
					shape = p.plus(q.times(p.Mass()));
					return shape;
				}
				
				Point q1 = p.plus(v.times(vMin * p.Mass()));
				Point q2 = p.plus(v.times(vMax * p.Mass()));
				shape = Geometries.segment(q1, q2);
			}

			return shape;
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
	}
	
	
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
		super(s);
	}
	
	
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
		// Eliminate segments.
		if(c instanceof ISegment)
		{
			Point p1 = new Point(((ISegment) c).P1());
			Point p2 = new Point(((ISegment) c).P2());
			
			return contains(p1)
				&& contains(p2);
		}

		// Eliminate base collision cases.
		return super.contains(c);
	}

	@Override
	public Boolean inhabits(ICollidable c)
	{
		// Eliminate geometries.
		if(c instanceof IGeometry)
		{
			return inhabits((IGeometry) c);
		}
		
		// Eliminate base collision cases.
		return super.inhabits(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{
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
		
		// Eliminate base collision cases.
		return super.intersects(c);
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate affine lines.
		if(c instanceof Line)
		{
			return new RSPLine((Line) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return new RSPSegment((ISegment) c);
		}
		
		// Eliminate base collision cases.
		return super.intersect(c);
	}
	
//	@Override
//	public Vector minimize()
//	{
//		Vector p = Source().P1().asVector();
//		Vector v = Source().P2().asVector().minus(p);
//		Vector w = Source().P3().asVector().minus(p);
//		
//		float vv = v.dot(v), ww = w.dot(w), vw = v.dot(w);
//		float pv = p.dot(v), pw = p.dot(w);
//		float dn = vv * ww - vw * vw;
//		
//		if(Floats.isZero(dn, p.Size()))
//		{
//			float c1 = Floats.clamp(-pv / vv, 0f, 1f);
//			float c2 = Floats.clamp(-pw / ww, 0f, 1f);
//			
//			Vector x1 = p.plus(v.times(c1));
//			Vector x2 = p.plus(w.times(c2));
//			
//			if(x1.dot(x1) < x2.dot(x2))
//				return x1;
//			else
//				return x2;
//		}
//		
//		float c1 = (pw * vw - pv * ww) / dn;
//		float c2 = (pv * vw - pw * vv) / dn;
//		return p.plus(v.times(c1)).plus(w.times(c2));
//	}
	
		
	private boolean inhabits(IGeometry g)
	{
		return g.contains(Source().P1())
			&& g.contains(Source().P2())
			&& g.contains(Source().P3());
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
		Point v = l.Vector();
		
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
}