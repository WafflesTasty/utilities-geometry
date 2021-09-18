package zeno.util.geom.collidables.collisions.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.ICollision.Response;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.collisions.convex.CLSConvex;
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
 * @see CLSConvex
 */
public class CLSSphere extends CLSConvex
{	
	/**
	 * The {@code RSPLine} class defines collision response with a line.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Response
	 */
	public class RSPLine implements Response
	{
		private Line line;
		private Vector dst, pnt;
		private Float disc, d1, d2;
		private ICollidable shape;
		
		/**
		 * Creates a new {@code RSPLine}.
		 * 
		 * @param line  an affine line
		 */
		public RSPLine(Line line)
		{
			this.line = line;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Discriminant() < 0;
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				disc = Discriminant();
				// If the discriminant is negative...
				if(disc < 0)
				{
					// The intersection is empty.
					shape = Geometries.VOID;
					return shape;
				}
				
				
				ISphere s = Source();
				Vector p = s.Center();
				
				Point r = line.Origin();
				Point v = line.Vector();
				int dim = r.Dimension();
				
				// If the discriminant is zero...
				if(Floats.isZero(disc, 4 + dim / 2))
				{
					// The intersection is a point.
					shape = r.plus(v.times(-d1));
				}
				else
				{
					// Otherwise, the intersection is a segment.
					float l1 = -d1 - Floats.sqrt(disc);
					float l2 = -d1 + Floats.sqrt(disc);
					Vector p1 = p.plus(v.Span().times(l1));
					Vector p2 = p.plus(v.Span().times(l2));
					shape = Geometries.segment(p1, p2);
				}
			}
			
			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(isEmpty()) return null;
			if(pnt == null)
			{
				int dim = Source().Dimension();
				if(Shape() instanceof Point)
				{
					pnt = Vectors.create(dim);
					return pnt;
				}
				
				ISphere s = Source();
				float rad = s.Radius();
				Vector c = s.Center();
				
				
				CLSSegment cls = (CLSSegment) Shape().Collisions();
				Response rsp = cls.contain(new Point(c, 1f));
				if(rsp.isEmpty())
				{
					pnt = rsp.Distance();
					float nrm = pnt.norm();
					nrm = (rad - nrm) / nrm;
					pnt = pnt.times(nrm);
					return pnt;
				}

				
				pnt = rsp.Penetration();
				
				int i = 0, j = 0;
				float iVal = 0f, jVal = 0f;
				for(int k = 0; k < pnt.Size(); k++)
				{
					float abs1 = Floats.abs(iVal);
					float abs2 = Floats.abs(pnt.get(k));
					
					if(abs1 < abs2)
					{
						jVal = iVal;
						iVal = pnt.get(k);
						j = i; i = k;
					}
				}
				
				pnt = Vectors.create(pnt.Size());
				pnt.set(+jVal, i); pnt.set(-iVal, j);
				pnt = pnt.times(rad / pnt.norm());
			}
			
			return pnt;
		}
		
		@Override
		public Vector Distance()
		{
			if(!isEmpty()) return null;
			if(dst == null)
			{
				int dim = Source().Dimension();
				if(Shape() instanceof Point)
				{
					dst = Vectors.create(dim);
					return dst;
				}
				
				ISphere s = Source();
				float rad = s.Radius();
				Vector c = s.Center();
				
				
				CLSSegment cls = (CLSSegment) Shape().Collisions();
				dst = cls.contain(new Point(c, 1f)).Distance();
				dst = dst.times(rad / dst.norm() - 1);
			}
			
			return dst;
		}
		
		Float Discriminant()
		{
			if(disc == null)
			{
				ISphere s = Source();
				float rad = s.Radius();
				Vector p = s.Center();
				
				Vector r = line.Origin().Span();
				Vector v = line.Vector().Span();
				Vector rp = r.minus(p);
				
				// Compute the quadratic discriminant.
				d1 = rp.dot(v); d2 = rp.dot(rp);
				disc = d1 * d1 - d2 + rad * rad;
			}
			
			return disc;
		}
	}
	
	/**
	 * The {@code RSPSegment} class defines collision response with a segment.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see Response
	 */
	public class RSPSegment implements Response
	{
		private ISegment line;
		private Vector dst, pnt;
		private Float disc, d1, d2;
		private ICollidable shape;
		
		/**
		 * Creates a new {@code RSPSegment}.
		 * 
		 * @param line  a line segment
		 * 
		 * 
		 * @see ISegment
		 */
		public RSPSegment(ISegment line)
		{
			this.line = line;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			return Discriminant() < 0;
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				disc = Discriminant();
				// If the discriminant is negative...
				if(disc < 0)
				{
					// The intersection is empty.
					shape = Geometries.VOID;
					return shape;
				}
				
				
				ISphere s = Source();
				int dim = s.Dimension();
				
				Vector p = line.P1();
				Vector q = line.P2();
				Vector qp = q.minus(p);

				// If the discriminant is zero...
				if(Floats.isZero(disc, 5 + dim / 2))
				{
					// And the solution is contained in the segment...
					if(0 <= -d1 && -d1 <= d2)
					{
						// Return the intersection point.
						Vector v = p.plus(qp.times(-d1 / d2));
						shape = new Point(v, 1f);
					}
					else
					{
						// Otherwise, the intersection is empty.
						shape = Geometries.VOID;						
					}
					
					return shape;
				}
				
				// Limit the solution to the segment.
				float l1 = Floats.max((-d1 - Floats.sqrt(disc)) / d2, 0);
				float l2 = Floats.min((-d1 + Floats.sqrt(disc)) / d2, 1);
				// If the solution is not contained in the segment...
				if(l1 > l2)
					// Otherwise, the intersection is empty.
					shape = Geometries.VOID;
				else
				{
					Vector p1 = p.plus(qp.times(l1));
					Vector p2 = p.plus(qp.times(l2));
					
					// return the intersection segment.	
					shape = Geometries.segment(p1, p2);
				}
			}
			
			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(isEmpty()) return null;
			if(pnt == null)
			{
				int dim = Source().Dimension();
				if(Shape() instanceof Point)
				{
					pnt = Vectors.create(dim);
					return pnt;
				}
				
				ISphere s = Source();
				float rad = s.Radius();
				Vector c = s.Center();
				
				
				CLSSegment cls = (CLSSegment) Shape().Collisions();
				Response rsp = cls.contain(new Point(c, 1f));
				if(rsp.isEmpty())
				{
					pnt = rsp.Distance();
					float nrm = pnt.norm();
					nrm = (rad - nrm) / nrm;
					pnt = pnt.times(nrm);
					return pnt;
				}

				
				pnt = rsp.Penetration();
				
				int i = 0, j = 0;
				float iVal = 0f, jVal = 0f;
				for(int k = 0; k < pnt.Size(); k++)
				{
					float abs1 = Floats.abs(iVal);
					float abs2 = Floats.abs(pnt.get(k));
					
					if(abs1 < abs2)
					{
						jVal = iVal;
						iVal = pnt.get(k);
						j = i; i = k;
					}
				}
				
				pnt = Vectors.create(pnt.Size());
				pnt.set(+jVal, i); pnt.set(-iVal, j);
				pnt = pnt.times(rad / pnt.norm());
			}
			
			return pnt;
		}
		
		@Override
		public Vector Distance()
		{
			if(!isEmpty()) return null;
			if(dst == null)
			{
				int dim = Source().Dimension();
				if(Shape() instanceof Point)
				{
					dst = Vectors.create(dim);
					return dst;
				}
				
				ISphere s = Source();
				float rad = s.Radius();
				Vector c = s.Center();
				
				
				CLSSegment cls = (CLSSegment) Shape().Collisions();
				dst = cls.contain(new Point(c, 1f)).Distance();
				dst = dst.times(rad / dst.norm() - 1);
			}
			
			return dst;
		}		
		
		Float Discriminant()
		{
			if(disc == null)
			{
				ISphere s = Source();
				float rad = s.Radius();
				Vector o = s.Center();
				
				Vector p = line.P1();
				Vector q = line.P2();
				
				Vector qp = q.minus(p);
				Vector po = p.minus(o);
				
				d1 = qp.dot(po);
				d2 = qp.dot(qp);

				
				// Compute the quadratic discriminant.
				disc = d1 * d1 - d2 * (po.dot(po) - rad * rad);
			}
			
			return disc;
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
		private Vector x;
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
			x = p.asVector();
		}
		
		
		@Override
		public boolean isEmpty()
		{			
			if(isEmpty == null)
			{
				ISphere s = Source();
				

				float rad = s.Radius();
				Vector p = x.minus(s.Center());
				int dim = s.Dimension();
				
				float sum = 0f;
				for(int i = 0; i < dim; i++)
				{
					sum += Floats.pow(p.get(i), 2);
					if(sum > rad * rad)
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
			return new Point(x);
		}

		@Override
		public Vector Penetration()
		{
			if(isEmpty()) return null;
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
			ISphere s = Source();
			float rad = s.Radius();
			Vector c = s.Center();
			
			
			dst = x.minus(c);
			float nrm = dst.norm();
			nrm = (nrm - rad) / nrm;
			dst = dst.times(nrm);
			return dst;
		}
	}
	
	
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
	public Boolean contains(ICollidable c)
	{
		// Eliminate points.
		if(c instanceof Point)
		{
			return contains((Point) c);
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
		
		return super.contains(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{		
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
		
		return super.intersect(c);
	}
		
	@Override
	public Response contain(Point p)
	{
		return new RSPPoint(p);
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
	
	private boolean contains(ISphere t)
	{
		ISphere s = Source();
		
		
		Vector p1 = s.Center(); Vector p2 = t.Center();
		float r1  = s.Radius(); float r2  = t.Radius();
		
		return p2.minus(p1).norm() <= r2 - r1;
	}

	private boolean contains(Point x)
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
}