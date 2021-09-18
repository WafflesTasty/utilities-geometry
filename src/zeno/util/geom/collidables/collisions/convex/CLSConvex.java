package zeno.util.geom.collidables.collisions.convex;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.IShapeable;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.CLSGeometry;
import zeno.util.geom.collidables.geometry.generic.IConvex;
import zeno.util.geom.collidables.geometry.generic.IConvex.Extremum;
import zeno.util.geom.collidables.geometry.generic.IHull;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code CLSConvex} class defines collision for an {@code IConvex}.
 * </br> The algorithm is an extension of Wolfe's algorithm that minimizes over a Minkowski sum.
 * 
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see <a href="https://www.math.ucla.edu/~jhaddock/Jamie/Dissertation.pdf">Projection Algorithms for Convex and Combinatorial Optimization</a>
 * @see CLSGeometry
 */
public class CLSConvex extends CLSGeometry
{
	private static final float EPSILON = Floats.pow(2f, -6f);
		
	class Face implements Comparable<Face>
	{
		private IHull hull;
		private Vector min;
		private float dist;
		
		public Face(IHull h, Vector p)
		{
			hull = h;
			min = h.contain(p).Distance();
			if(min == null)
			{
				min = Vectors.create(hull.Dimension());
			}
			
			dist = min.normSqr();
		}
		
		public Iterable<Face> split(Vector p, Vector v)
		{
			return () -> new Iterator<>()
			{
				private int index = 0;
				private Matrix mat = hull.Vertices();
				
				@Override
				public boolean hasNext()
				{
					return index < hull.Span().Columns();
				}
				
				@Override
				public Face next()
				{
					int rows = mat.Rows();
					int cols = mat.Columns();
					
					Vector vec;
					Matrix span = Matrices.create(rows, cols);
					for(int c = 0; c < cols; c++)
					{
						if(c != index)
							vec = span.Column(c);
						else
							vec = v;
						
						for(int r = 0; r < rows; r++)
						{
							span.set(vec.get(r), r, c);
						}
					}

					index++;
					IHull hull = Geometries.hull(span);
					return new Face(hull, p);
				}
			};
		}
		
		@Override
		public int compareTo(Face f)
		{
			return (int) Floats.sign(dist - f.dist);
		}
	}
	
	
	/**
	 * The {@code RSPSphere} class defines collision response for spheres.
	 *
	 * @author Waffles
	 * @since 13 May 2021
	 * @version 1.0
	 */
	public class RSPSphere implements Response
	{
		private Response rsp;
		private ISphere sphr;
		
		private Vector dst, pnt;
		private Boolean isEmpty;
		
		/**
		 * Creates a new {@code RSPSphere}.
		 * 
		 * @param s  a target sphere
		 * 
		 * 
		 * @see ISphere
		 */
		public RSPSphere(ISphere s)
		{
			rsp = Source().contain(s.Center());
			sphr = s;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			if(isEmpty == null)
			{
				dst = rsp.Distance();
				if(dst == null)
				{
					pnt = rsp.Penetration();
					
					isEmpty = false;
					return isEmpty;
				}

				float rad = sphr.Radius();
				float nrm = dst.normSqr();
				isEmpty = nrm > rad * rad;
			}
			
			return isEmpty;
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;			
			return null;
		}

		@Override
		public Vector Penetration()
		{
			if(isEmpty())
			{
				return null;
			}
			
			if(dst == null)
			{				
				float nrm = pnt.norm();
				float rad = sphr.Radius();
				
				return pnt.times((nrm + rad) / nrm);
			}

			float nrm = dst.norm();
			float rad = sphr.Radius();
			
			return dst.times((nrm - rad) / nrm);
		}
		
		@Override
		public Vector Distance()
		{			
			if(!isEmpty())
			{
				return null;
			}
			
			float nrm = dst.norm();
			float rad = sphr.Radius();

			return dst.times((nrm - rad) / nrm);
		}
	}
	
	/**
	 * The {@code RSPPoint} class defines collision response for a point.
	 *
	 * @author Waffles
	 * @since 13 May 2021
	 * @version 1.0
	 */
	public class RSPPoint implements Response
	{
		private CLSCorral crl;
		private Boolean isEmpty;
		private Vector dst, pnt, p;
		
		/**
		 * Creates a new {@code RSPPoint}.
		 * 
		 * @param c  a target point
		 * 
		 * 
		 * @see Point
		 */
		public RSPPoint(Point c)
		{
			crl = new CLSCorral(Source(), c);
			p = c.asVector();
		}
		
		/**
		 * Returns the minimum of the {@code RSPPoint}.
		 * 
		 * @return  a convex minimum vector
		 * 
		 * 
		 * @see Vector
		 */
		public Vector Minimum()
		{
			return dst;
		}
		
		
		@Override
		public boolean isEmpty()
		{
			if(dst == null)
				dst = Phase1();
			return isEmpty;
		}

		@Override
		public ICollidable Shape()
		{
			if(isEmpty())
				return Geometries.VOID;			
			return new Point(p);
		}

		@Override
		public Vector Penetration()
		{
			if(dst == null)
				dst = Phase1();
			if(pnt == null)
				pnt = Phase2();
			if(!isEmpty())
			{
				return p.minus(pnt);
			}
			
			return null;
		}
		
		@Override
		public Vector Distance()
		{
			if(isEmpty())
				return p.minus(dst);
			return null;
		}

		
		private int index;

		Vector Phase1()
		{
			index = 0;
			IConvex src = Source();
			Extremum ext = src.Extremum();

			// Find the first candidate.
			Vector ct = src.Center();
			Vector x = p.minus(ct);
			dst = ext.along(x);
			

			Vector px = p.minus(dst);
			Vector y = ext.along(px);
			if(y == null)
			{
//				System.out.println("Initial point is minimal.");
//				System.out.println("Minimal distance found!");
				isEmpty = p.minus(dst).normSqr() >= EPSILON;
				return dst;
			}
			
			Vector yx = y.minus(dst);

//			System.out.println("Starting phase 1.");
//			System.out.println(EPSILON);
			Vector aVal = crl.add(dst);
//			System.out.println("Affine value:");
//			Matrices.print(aVal);
//			System.out.println("Checking point:");
//			Matrices.print(y);
			
			// While an extremum violates minimality...
			while(px.dot(yx) > EPSILON)
			{
				index++;
				if(index > 24)
				{
					throw new RuntimeException();
				}
				
//				System.out.println("Point violates minimality (" + px.dot(yx) + ").");
				// Add the point to the corral.
				aVal = crl.add(y);
//				System.out.println("Affine value:");
//				Matrices.print(aVal);
				
				// While the affine minimum is not convex...
				while(!crl.isConvex(aVal))
				{
//					System.out.println("Affine value is outside the corral.");
					// Project it onto the corral.
					aVal = crl.project(aVal);
//					System.out.println("Affine value:");
//					Matrices.print(aVal);
					
					// If the last point was useless...
					if(crl.isDegenerate())
					{
//						System.out.println("Last point was useless.");
//						System.out.println("Minimal distance found!");
						dst = crl.evaluate(aVal);
						
						// Return it.
						isEmpty = false;
						return dst;
					}
				}
				
				// Otherwise, update the convex minimum.
				dst = crl.evaluate(aVal);
//				System.out.println("Convex point:");
//				Matrices.print(dst);
				
				// If the target point is reached...
				if(p.minus(dst).normSqr() < EPSILON)
				{
//					System.out.println("Point is inside the corral.");
//					System.out.println("Minimal distance found!");
					// Return it.
					isEmpty = false;
					return dst;
				}
								
				px = p.minus(dst);
				y = ext.along(px);
				if(y == null)
				{
//					System.out.println("Point is minimal.");
//					System.out.println("Minimal distance found!");
					isEmpty = px.normSqr() >= EPSILON;
					return dst;
				}
				
				yx = y.minus(dst);
				
//				System.out.println("Checking point:");
//				Matrices.print(y);
			}

//			System.out.println("Point is outside the corral.");
//			System.out.println("Minimal distance found!");
			// The convex minimum has been found.
			isEmpty = true;
			return dst;
		}
		
		Vector Phase2()
		{
			Extremum ext = Source().Extremum();
			Queue<Face> queue = new PriorityQueue<>();
			
			crl.maximize(ext);
			for(IHull hull : crl.Faces())
			{
				Face face = new Face(hull, p);
				queue.add(face);
			}
			
			float min = Floats.MAX_VALUE;
			while(!queue.isEmpty())
			{
				Face face = queue.poll();
				if(min < face.dist)
				{
					break;
				}

				// pnt points from the face to the target point.
				min = face.dist;
				pnt = face.min;
				
				// this one needs to split the face.
				Vector v = ext.along(pnt.times(-1f));
				Vector w = v.minus(p.plus(pnt));
				if(pnt.dot(w) > -EPSILON)
				{
					continue;
				}
				
				for(Face f : face.split(p, v))
				{
					queue.add(f);
				}
			}

			return pnt;
		}
	}
	
	
	/**
	 * Creates a new {@code CLSConvex}.
	 * 
	 * @param src  a source convex
	 * 
	 * 
	 * @see IConvex
	 */
	public CLSConvex(IConvex src)
	{
		super(src);
	}
	
	
	@Override
	public Response contain(Point p)
	{		
		return new RSPPoint(p);
	}
	
	@Override
	public Response intersect(ICollidable c)
	{
		// Eliminate spheroid geometry.
		if(c instanceof ISphere)
		{
			return new RSPSphere((ISphere) c);
		}
				
		// Eliminate convex geometry.
		if(c instanceof IConvex)
		{
			IConvex src = Source();
			IConvex tgt = (IConvex) c;
			
			Vector v = Vectors.create(src.Dimension());
			IConvex cvx = src.minus(tgt);
			return cvx.contain(new Point(v, 1f));
		}
		
		return super.intersect(c);
	}
	
	@Override
	public Boolean intersects(ICollidable c)
	{		
		// Eliminate shapeable geometry.
		if(c instanceof IShapeable)
		{
			IShapeable s = (IShapeable) c;
			if(s.Shape() instanceof IConvex)
			{
				return intersects(Geometries.convex(s));
			}
			
			return null;
		}
		
		// Eliminate spheroid geometry.
		if(c instanceof ISphere)
		{
			return !intersect(c).isEmpty();
		}
		
		// Eliminate convex geometry.
		if(c instanceof IConvex)
		{
			return !intersect(c).isEmpty();
		}
		
		return super.intersects(c);
	}

	@Override
	protected IConvex Source()
	{
		return (IConvex) super.Source();
	}
}