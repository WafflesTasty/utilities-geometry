package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
import zeno.util.geom.collidables.geometry.generic.ISegment;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSSegment} class defines collision for an {@code ISegment}.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSSegment extends CLSHull
{	
	/**
	 * The {@code RSPSegment} class defines collision response for line segments.
	 *
	 * @author Waffles
	 * @since 13 May 2021
	 * @version 1.0
	 */
	public class RSPSegment implements Response
	{
		private ISegment t;
		private ICollidable shape;
		private Response rsp;
		
		/**
		 * Creates a new {@code RSPSegment}.
		 * 
		 * @param t  a target segment
		 * 
		 * 
		 * @see RSPSegment
		 */
		public RSPSegment(ISegment t)
		{
			this.t = t;
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
					shape = Geometries.VOID;
					return shape;
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
						Vector v = s.P1().plus(qp.times(lbd1));
						shape = new Point(v, 1f);
						return shape;
					}
					
					// Otherwise, the intersection is empty.
					shape = Geometries.VOID;
					return shape;
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
					Vector p1 = s.P1().plus(qp.times(lmin));
					Vector p2 = s.P1().plus(qp.times(lmax));
					shape = Geometries.segment(p1, p2);
					return shape;
				}
				
				// Otherwise, the intersection is empty.		
				shape = Geometries.VOID;
			}
			
			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(t);
			}
			
			return rsp.Penetration();
		}
		
		@Override
		public Vector Distance()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(t);
			}
			
			return rsp.Distance();
		}
	}
	
	/**
	 * The {@code RSPASpace} class defines collision response for an affine space.
	 *
	 * @author Waffles
	 * @since 13 May 2021
	 * @version 1.0
	 */
	public class RSPASpace implements Response
	{
		private ASpace space;
		private ICollidable shape;
		
		/**
		 * Creates a new {@code RSPASpace}.
		 * 
		 * @param space  a target space
		 * 
		 * 
		 * @see ASpace
		 */
		public RSPASpace(ASpace space)
		{
			this.space = space;
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
				ISegment s = Source();
				
				VSpace dir = space.Direction();
				Vector qp = s.P2().minus(s.P1());
				Vector pr = s.P1().minus(space.Origin().Span());
				// Create the affine intersection space.
				VSpace sum = VSpaces.create(dir.Span(), qp);
				Vector crd = sum.coordinates(pr);
				
				// If the linear system has no solution...
				if(crd == null)
				{
					// The intersection is empty.
					shape = Geometries.VOID;
					return shape;
				}
				
				// If the solution is not unique...
				if(dir.contains(qp))
				{
					// The entire segment is contained.
					shape = Source();
					return shape;
				}
				
				// If the solution is inside the segment...
				float lbd = crd.get(crd.Size() - 1);
				if(0 <= lbd && lbd <= 1)
				{
					// Return the intersection point.
					Vector v = s.P1().plus(qp.times(lbd));
					return new Point(v, 1f);
				}
				
				// Otherwise, the intersection is empty.
				shape = Geometries.VOID;
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
		private Vector x, dst, pnt;
		
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
			x = p.asVector();
		}
		
		
		@Override
		public boolean isEmpty()
		{		
			int dim = 4 + Distance().Size() / 2;
			float norm = Distance().normSqr();
			return Floats.isZero(norm, dim);
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;
			return new Point(x);
		}

		@Override
		public Vector Penetration()
		{
			if(pnt == null)
			{
				Vector p = x.minus(Source().P1());
				Vector q = x.minus(Source().P2());
				
				float np = p.normSqr();
				float nq = q.normSqr();
				
				pnt = np < nq ? p : q;
			}
			
			return pnt;
		}
		
		@Override
		public Vector Distance()
		{
			if(dst == null)
			{
				Vector p = Source().P1();
				Vector q = Source().P2();
				Vector v = q.minus(p);
				
				
				float dot = v.normSqr();
				if(Floats.isZero(dot, v.Size()))
				{
					return q;
				}
				
				float c = x.minus(p).dot(v);
				c = Floats.clamp(c / dot, 0f, 1f);
				dst = q.plus(v.times(c));
				dst = dst.minus(x);
			}
			
			return dst;
		}
	}
	
	
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
	public Boolean contains(ICollidable c)
	{	
		// Eliminate points.
		if(c instanceof Point)
		{
			return contains((Point) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return contains((ISegment) c);
		}
	
		// Eliminate base collision cases.
		Boolean cnt = super.contains(c);
		if(cnt != null)
		{
			return cnt;
		}
		
		// Other shapes have volume.
		return false;
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
	public Response intersect(ICollidable c)
	{
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			return new RSPASpace((ASpace) c);
		}
		
		// Eliminate line segments.
		if(c instanceof ISegment)
		{
			return new RSPSegment((ISegment) c);
		}
		
		// Eliminate base collision cases.
		return super.intersect(c);
	}

	@Override
	public Response contain(Point p)
	{
		return new RSPPoint(p);
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
		Vector pr = s.P1().minus(t.P1());
		Vector ps = s.P1().minus(t.P2());
		
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

	private boolean contains(Point x)
	{
		ISegment s = Source();
		
		
		// Calculate the dot products.
		Vector xp = s.P2().minus(x.asVector());
		Vector qp = s.P2().minus(s.P1());
		
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
}