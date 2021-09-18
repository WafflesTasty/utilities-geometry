package zeno.util.geom.collidables.collisions.geometry;

import java.util.List;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.affine.lines.Line;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.bounds.IBounded;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
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
 * @see CLSHull
 */
public class CLSCuboid extends CLSHull
{	
	private static final LineClipper clipper = new LineClipper();
	
	/**
	 * The {@code RSPCuboid} class defines collision response for a cuboid.
	 *
	 * @author Waffles
	 * @since 12 May 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ICollision
	 */
	public class RSPCuboid implements Response
	{
		private ICuboid tgt;
		private ICollidable shape;
		private Response rsp;
		
		/**
		 * Creates a new {@code RSPCuboid}.
		 * 
		 * @param tgt  a target cuboid
		 * 
		 * 
		 * @see ICuboid
		 */
		public RSPCuboid(ICuboid tgt)
		{
			this.tgt = tgt;
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
				ICuboid src = Source();
				int dim = Integers.min(src.Dimension(), tgt.Dimension());
				Vector m = Vectors.create(dim); Vector n = Vectors.create(dim);
				
				for(int i = 0; i < dim; i++)
				{
					float si = src.Size().get(i);
					float ti = tgt.Size().get(i);
					
					float pi = src.Center().get(i);
					float qi = tgt.Center().get(i);
					
					if(si + ti < 2 * Floats.abs(pi - qi))
					{
						shape = Geometries.VOID;
						return shape;
					}
					
					m.set(Floats.min(pi + si / 2, qi + ti / 2), i);
					n.set(Floats.max(pi - si / 2, qi - ti / 2), i);
				}
				
				Vector s = m.minus(n);
				Vector p = m.plus(n).times(0.5f);
				shape = Geometries.cuboid(p, s);
			}

			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(tgt);
			}
			
			return rsp.Penetration();
		}
		
		@Override
		public Vector Distance()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(tgt);
			}
			
			return rsp.Distance();
		}
	}
	
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
				clipper.setBounds(Source());
				List<Vector> clip = clipper.clip(s);
				if(clip.isEmpty())
					shape = Geometries.VOID;
				else
				{
					Vector p1 = clip.get(0);
					Vector p2 = clip.get(1);
					
					shape = Geometries.segment(p1, p2);
				}
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
		private Point p;
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
			this.p = p;
		}
		
		
		@Override
		public boolean isEmpty()
		{			
			if(isEmpty == null)
			{
				int dim = Source().Dimension();
				Vector c = Source().Center();
				Vector s = Source().Size();
				
				
				float m = p.Mass();
				for(int i = 0; i < dim; i++)
				{
					float xi = p.get(i);
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
			return p;
		}
		
		@Override
		public Vector Penetration()
		{
			return null;
		}

		@Override
		public Vector Distance()
		{
			if(dst == null)
			{
				CLSHull cls = new CLSHull(Source());
				dst = cls.contain(p).Distance();
			}
			
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
		private Response rsp;
		private ICollidable shape;
		private Float lmin, lmax;
		
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
			return Geometries.VOID.equals(Check());
		}

		@Override
		public ICollidable Shape()
		{
			if(shape == null)
			{
				Point r = line.Origin();
				Point v = line.Vector();
				float m = r.Mass();

				// Otherwise, compute the intersecting segment.
				Vector p1 = r.plus(v.times(m * lmin)).asVector();
				Vector p2 = r.plus(v.times(m * lmax)).asVector();
				shape = Geometries.segment(p1, p2);
			}

			return shape;
		}

		@Override
		public Vector Penetration()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(line);
			}
			
			return rsp.Penetration();
		}
		
		@Override
		public Vector Distance()
		{
			if(rsp == null)
			{
				CLSHull cls = new CLSHull(Source());
				rsp = cls.intersect(line);
			}
			
			return rsp.Distance();
		}
		
		ICollidable Check()
		{
			if(lmin == null)
			{
				ICuboid c = Source();
				
				Point r = line.Origin();
				Point v = line.Vector();
				Vector m1 = c.Minimum();
				Vector m2 = c.Maximum();
					
				
				float m = r.Mass();
				lmin = Floats.NEG_INFINITY;
				lmax = Floats.POS_INFINITY;
				// For every axis-aligned dimension...
				for(int i = 0; i < c.Dimension(); i++)
				{
					float l1 = m * m1.get(i) - r.get(i);
					float l2 = m * m2.get(i) - r.get(i);
				
					// Compute the minimum and maximum lambda.
					lmin = Floats.max(lmin, l1 * v.Mass() / (m * v.get(i)));
					lmax = Floats.min(lmax, l2 * v.Mass() / (m * v.get(i)));
					
					// If the halfspaces don't intersect...
					if(lmax < lmin)
					{
						// The intersection is empty.
						shape = Geometries.VOID;
					}
				}
			}
			
			return shape;
		}
	}

	
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
	public Boolean contains(ICollidable c)
	{		
		// Eliminate points.
		if(c instanceof Point)
		{
			return contains((Point) c);
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
		
		// Eliminate cuboids.
		if(c instanceof ICuboid)
		{
			return new RSPCuboid((ICuboid) c);
		}
		
		// Eliminate base collision cases.
		return super.intersect(c);
	}
	
	@Override
	public Response contain(Point p)
	{
		return new RSPPoint(p);
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

	private boolean contains(Point x)
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
}