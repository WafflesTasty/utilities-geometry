package zeno.util.geom.utilities;

import zeno.util.algebra.algorithms.solvers.SLVCrout;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom._deprecated.algorithms.LineClipper;
import zeno.util.geom._deprecated.collideables.lines.ILine;
import zeno.util.geom.collidables.geometry.ICuboid;
import zeno.util.geom.collidables.geometry.IEllipsoid;
import zeno.util.geom.collidables.geometry.ISphere;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code Intersection} class defines intersection methods between base geometric shapes.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 */
public final class Intersection
{		
	/**
	 * Checks the intersection between a cuboid and a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @param d  a cuboid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ICuboid c, ICuboid d)
	{
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
	
	/**
	 * Checks the intersection between a cuboid and an ellipsoid.
	 * 
	 * @param c  a cuboid to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ICuboid c, IEllipsoid e)
	{
		int dim = Integers.min(e.Dimension(), c.Dimension());
		
		Vector center = c.Center().minus(e.Center());
		Vector size   = c.Size();
		
		for(int i = 0; i < dim; i++)
		{
			center.set(center.get(i) / e.Size().get(i), i);
			size.set(    size.get(i) / e.Size().get(i), i);
		}
		
		return between(ICuboid.create(center, size), ISphere.unit(dim));
	}
	
	/**
	 * Checks the intersection between a cuboid and a sphere.
	 * 
	 * @param c  a cuboid to check
	 * @param s  a sphere to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ICuboid c, ISphere s)
	{
		throw new UnsupportedOperationException("ISphere-ellipsoid intersection not implemented yet.");
	}
	
	/**
	 * Checks the intersection between a cuboid and a line.
	 * 
	 * @param c  a cuboid to check
	 * @param l  a line to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ICuboid c, ILine l)
	{
		clipper.setBounds(c);
		if(clipper.clip(l).isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
		
	/**
	 * Checks the intersection between an ellipsoid and a cuboid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param c  a cuboid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(IEllipsoid e, ICuboid c)
	{
		return between(c, e);
	}
	
	/**
	 * Checks the intersection between an ellipsoid and an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param f  an ellipsoid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(IEllipsoid e, IEllipsoid f)
	{
		int dim = Integers.min(e.Dimension(), f.Dimension());
		
		Vector center = f.Center().minus(e.Center());
		Vector size   = f.Size();
		
		for(int i = 0; i < dim; i++)
		{
			center.set(center.get(i) / e.Size().get(i), i);
			size.set(    size.get(i) / e.Size().get(i), i);
		}
		
		return between(ISphere.unit(dim), IEllipsoid.create(center, size));
	}
	
	/**
	 * Checks the intersection between an ellipsoid and a sphere.
	 * 
	 * @param e  an ellipsoid to check
	 * @param s  a sphere to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(IEllipsoid e, ISphere s)
	{
		throw new UnsupportedOperationException("ISphere-ellipsoid intersection not implemented yet.");
	}
	
	/**
	 * Checks the intersection between an ellipsoid and a line.
	 * 
	 * @param e  an ellipsoid to check
	 * @param l  a line to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(IEllipsoid e, ILine l)
	{
		int dim = Integers.min(e.Dimension(), l.Dimension());
		
		Vector p1 = l.P1().minus(e.Center());
		Vector p2 = l.P2().minus(e.Center());
		
		for(int i = 0; i < dim; i++)
		{
			p1.set(p1.get(i) / e.Size().get(i), i);
			p2.set(p2.get(i) / e.Size().get(i), i);
		}
		
		return between(ISphere.unit(dim), ILine.create(p1, p2));
	}
	
	
	/**
	 * Checks the intersection between a sphere and a cuboid.
	 * 
	 * @param s  a sphere to check
	 * @param c  a cuboid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ISphere s, ICuboid c)
	{
		return between(c, s);
	}
		
	/**
	 * Checks the intersection between a sphere and an ellipsoid.
	 * 
	 * @param s  a sphere to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ISphere s, IEllipsoid e)
	{
		return between(e, s);
	}
	
	/**
	 * Checks the intersection between a sphere and a sphere.
	 * 
	 * @param s  a sphere to check
	 * @param t  a sphere to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ISphere s, ISphere t)
	{
		float rad = s.Radius() + t.Radius();
		Vector pq = s.Center().minus(t.Center());
		return pq.normSqr() <= rad * rad;
	}

	/**
	 * Checks the intersection between a sphere and a line.
	 * 
	 * @param s  a sphere to check
	 * @param l  a line to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ISphere s, ILine l)
	{
		Vector qt = l.P2().minus(l.P1());
		Vector qp = s.Center().minus(l.P1());
		
		
		float lam = qp.dot(qt);
		if(lam != 0) lam /= qt.dot(qt);
		if(lam < 0 || 1 < lam) return false;
		
		
		float rad = s.Radius();
		Vector v = l.P1().plus(qt.times(lam));
		return v.normSqr() <= rad * rad;
	}
	
	
	/**
	 * Checks the intersection between a line and a cuboid.
	 * 
	 * @param l  a line to check
	 * @param c  a cuboid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ILine l, ICuboid c)
	{
		return between(c, l);
	}
	
	/**
	 * Checks the intersection between a line and an ellipsoid.
	 * 
	 * @param l  a line to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ILine l, IEllipsoid e)
	{
		return between(e, l);
	}

	/**
	 * Checks the intersection between a line and a sphere.
	 * 
	 * @param l  a line to check
	 * @param s  a sphere to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ILine l, ISphere s)
	{
		return between(s, l);
	}
	
	/**
	 * Checks the intersection between a line and a line.
	 * 
	 * @param l  a line to check
	 * @param m  a line to check
	 * @return  {@code true} if the shapes intersect
	 */
	public static boolean between(ILine l, ILine m)
	{
		Vector pq = l.P1().minus(l.P2());
		Vector pr = m.P1().minus(l.P1());
		Vector rs = m.P1().minus(m.P2());
		
		// Maximize components.
		int i = maximize(pq, -1);
		int j = maximize(rs,  i);
		
		
		// Solve linear system.
		Matrix mat = Matrices.create(2, 2);
		Vector vec = Vectors.create(2);
		
		mat.set( pq.get(i), 0, 0);
		mat.set( pq.get(j), 1, 0);
		mat.set(-rs.get(i), 0, 1);
		mat.set(-rs.get(j), 1, 1);
		vec.set( pr.get(i), 0);
		vec.set( pr.get(j), 1);
		
		SLVCrout slv = new SLVCrout(mat);
		Vector v = slv.solve(vec);
		if(v == null)
		{
			return false;
		}
		
		
		float l1 = v.get(0);
		float l2 = v.get(1);
		
		// Create intersection.
		Vector x = l.P1()
				.plus(m.P1())
				.plus(pq.times(l1))
				.plus(rs.times(l2));
		
		return Containment.in(l, x)
			&& Containment.in(m, x);
	}
	
	
	
	private static final LineClipper clipper = new LineClipper();
	
	/**
	 * Find the index of the maximum vector component.
	 * 
	 * @param v  a vector to iterate
	 * @param i  a component to disregard
	 * @return  the maximum component index
	 */
	private static int maximize(Vector v, int i)
	{
		int j = 0;
		float max  = 0;
		for(int k = 0; k < v.Size(); k++)
		{
			if(i == k) continue;
			
			float val = Floats.abs(v.get(k));
			if(max < val)
			{
				max = val;
				j = k;
			}
		}
		
		return j;
	}
	
	
	private Intersection()
	{
		// NOT APPLICABLE
	}
}