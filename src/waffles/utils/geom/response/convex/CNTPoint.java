package waffles.utils.geom.response.convex;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collidable.convex.ConvexSet.Extremum;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code CNTPoint} computes the collision response from a convex set to a point.
 * This is an implementation of Wolfe's algorithm for convex minimization.
 * 
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see <a href="http://essay.utwente.nl/81914/1/Petrov_BA_EEMCS.pdf">Wolfe Algorithm</a>
 * @see Response
 */
public class CNTPoint implements Response
{
	/**
	 * A {@code Corral} defines an intermediate minimizing set for Wolfe's algorithm.
	 *
	 * @author Waffles
	 * @since 29 Jul 2025
	 * @version 1.1
	 *
	 * 
	 * @see Affine
	 */
	public class Corral implements Affine
	{
		private int iMin;
		private Point m, n;
		private VSpace space;
		private Matrix span;
		private Vector l;
		
		/**
		 * Creates a new {@code Corral}.
		 */
		public Corral()
		{			
			Extremum ext = src.Extremum();
			m = new Point(src.Origin(), 1f);
			m = ext.along(tgt.minus(m));
						
			l = Vectors.create(1f);
			span = m.Span();
			space = new VSpace(span);
//			System.out.println("Initial Span:");
//			Matrices.print(span);
		}
		
		/**
		 * Computes the distance with the {@code Corral}.
		 * 
		 * @return  a distance vector
		 * 
		 * 
		 * @see Vector
		 */
		public Vector distance()
		{
			while(!isOptimal())
			{
				System.out.println("Expanding corral...");
				expand();
				System.out.println("Span:");
				span.print();
				while(!isConvex())
				{
					System.out.println("Contracting corral...");
					contract();
					System.out.println("Span:");
					span.print();
//					if(iMin == span.Columns())
//						isOptimal is true, deleting the last point.
				}
			}

			System.out.println("Done!");
			int dim = tgt.Dimension() - 1;
			dst = tgt.minus(m).Generator();
			float nrm = dst.normSqr();
			
			if(Floats.isZero(nrm, 2 * dim - 1))
			{
				return null;
			}
			
			return dst;
		}


		@Override
		public Factory Factory()
		{
			return null;
		}

		@Override
		public Matrix Span()
		{
			return span;
		}
		
		
		boolean isOptimal()
		{
			int dim = src.Dimension();
			Extremum ext = src.Extremum();
			Point p = tgt.minus(m);
			n = ext.along(p);
			Point q = n.minus(m);
			System.out.println("Next:");
			n.Span().print();
			System.out.println("Target:");
			tgt.Span().print();
			System.out.println("V:");
			p.Span().print();
			System.out.println("W:");
			q.Span().print();
			int ulps = 2 * dim - 1;
			
			Vector v = p.Generator();
			Vector w = q.Generator();
			
			v = v.normalize();
			w = w.normalize();
			System.out.println("V:");
			v.print();
			System.out.println("W:");
			w.print();
			float err = ulps * Floats.EPSILON * space.Condition();
			System.out.println("Dot: " + p.dot(q) + " | " + err + " | " + v.dot(w));
			return p.dot(q) <= err;
		}
		
		boolean isConvex()
		{
			l = space.approx(tgt.Span());
			m = new Point(span.times(l));
			iMin = -1;
			System.out.println("Lambda:");
			l.print();
			System.out.println("Minimum:");
			m.Generator().print();
			float lMin = 0f;
			for(int i = 0; i < l.Size(); i++)
			{
				if(lMin > l.get(i))
				{
					lMin = l.get(i);
					iMin = i;
				}
			}
			System.out.println("Index: " + iMin);
			return iMin < 0;
		}
		
		
		void contract()
		{
			span = Matrices.omitColumn(span, iMin);
			space = new VSpace(span);
		}
		
		void expand()
		{
			span = Matrices.concat(span, n.Span());
			space = new VSpace(span);
		}
	}
	
	
	private Corral crl;
	private Vector dst, pnt;
	private Boolean hasImpact;
	private ConvexSet src;
	private Point tgt;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source convex
	 * @param p  a target point
	 * 
	 * 
	 * @see ConvexSet
	 * @see Point
	 */
	public CNTPoint(ConvexSet s, Point p)
	{
		src = s; tgt = p;
		crl = new Corral();
	}

	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{		
		if(hasImpact == null)
		{
			dst = crl.distance();
			hasImpact = dst == null;
		}

		return hasImpact;
	}

	@Override
	public Vector Penetration()
	{
		if(hasImpact())
		{
			if(pnt == null)
			{
//				pnt = crl.pnt;
			}
		}
		
		return pnt;
	}
	
	@Override
	public Vector Distance()
	{
		if(!hasImpact())
		{
			return dst;
		}
		
		return null;
	}
	
	@Override
	public Point Contact()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		return null;
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return Integers.pow(dim, 2 * dim);
	}
}