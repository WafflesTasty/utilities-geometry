package zeno.util.geom.utilities;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collideables.geometry.ICuboid;
import zeno.util.geom.collideables.geometry.IEllipsoid;
import zeno.util.geom.collideables.geometry.ISphere;
import zeno.util.geom.collideables.geometry.Line;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code Containment} class defines containment methods between base geometric shapes.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 */
public final class Containment
{	
	/**
	 * Checks the containment of a cuboid in a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @param d  a cuboid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ICuboid c, ICuboid d)
	{
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
	
	/**
	 * Checks the containment of an ellipsoid in a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ICuboid c, IEllipsoid e)
	{
		return in(c, e.Bounds());
	}

	/**
	 * Checks the containment of a point in a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @param v  a point to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ICuboid c, Vector v)
	{
		for(int i = 0; i < c.Dimension(); i++)
		{
			float vi = v.get(i);
			float si = c.Size().get(i);
			float ci = c.Center().get(i);
			
			if(si < 2 * Floats.abs(vi - ci))
			{
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Checks the containment of a line in a cuboid.
	 * 
	 * @param c  a cuboid to check
	 * @param l  a line to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ICuboid c, Line l)
	{
		return in(c, l.P1())
			&& in(c, l.P2());
	}
	
	
	/**
	 * Checks the containment of a point in an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param v  a point to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(IEllipsoid e, Vector v)
	{
		float val, sum = 0;
		for(int i = 0; i < e.Dimension(); i++)
		{
			val = v.get(i) - e.Center().get(i);
			
			if(val != 0)
			{
				val /= e.Size().get(i);
				sum += val * val;
				if(4 * sum > 1)
				{
					return false;
				}
			}
		}
		
		return true;
	}
		
	/**
	 * Checks the containment of a cuboid in an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param c  a cuboid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(IEllipsoid e, ICuboid c)
	{
		return in(e, c.Minimum())
			&& in(e, c.Maximum());
	}
	
	/**
	 * Checks the containment of an ellipsoid in an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param f  an ellipsoid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(IEllipsoid e, IEllipsoid f)
	{
		int dim = Integers.min(e.Dimension(), f.Dimension());
		
		Vector center = f.Center().minus(e.Center());
		Vector size   = f.Size();
		
		for(int i = 0; i < dim; i++)
		{
			center.set(center.get(i) / e.Size().get(i), i);
			size.set(    size.get(i) / e.Size().get(i), i);
		}
		
		return in(ISphere.unit(dim), IEllipsoid.create(center, size));
	}
		
	/**
	 * Checks the containment of a sphere in an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param s  a sphere to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(IEllipsoid e, ISphere s)
	{
		throw new UnsupportedOperationException("IEllipsoid-sphere containment not implemented yet.");
	}
	
	/**
	 * Checks the containment of a line in an ellipsoid.
	 * 
	 * @param e  an ellipsoid to check
	 * @param l  a line to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(IEllipsoid e, Line l)
	{
		return in(e, l.P1())
			&& in(e, l.P2());
	}
	
	
	/**
	 * Checks the containment of a point in a sphere.
	 * 
	 * @param s  a sphere to check
	 * @param v  a point to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ISphere s, Vector v)
	{
		float rad = s.Radius();
		Vector px = v.minus(s.Center());
		return px.normSqr() <= rad * rad;
	}
	
	/**
	 * Checks the containment of an ellipsoid in a sphere.
	 * 
	 * @param s  a sphere to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ISphere s, IEllipsoid e)
	{
		throw new UnsupportedOperationException("ISphere-ellipsoid containment not implemented yet.");
	}
		
	/**
	 * Checks the containment of a sphere in a sphere.
	 * 
	 * @param s  a sphere to check
	 * @param t  a sphere to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(ISphere s, ISphere t)
	{
		float rad = s.Radius() - t.Radius();
		Vector pq = s.Center().minus(t.Center());
		return pq.normSqr() <= rad * rad;
	}

	
	/**
	 * Checks the containment of a point in a line.
	 * 
	 * @param l  a line to check
	 * @param v  a point to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(Line l, Vector v)
	{
		Vector p1 = l.P1();
		Vector p2 = l.P2();
		
		
		// Check if lambda is between 0 and 1.
		float xmin = Floats.min(p1.get(0), p2.get(0));
		float xmax = Floats.max(p1.get(0), p2.get(0));
		
		if(v.get(0) < xmin || xmax < v.get(0))
		{
			return false;
		}
		
		
		// Check if lambda value is unique.
		for(int i = 0; i < l.Dimension() - 1; i++)
		{
			float val1 = (v.get(i+0) - p1.get(i+0)) * (p2.get(i+1) - p1.get(i+1));
			float val2 = (v.get(i+1) - p1.get(i+1)) * (p2.get(i+0) - p1.get(i+0));

			if(!Floats.isEqual(val1, val2, 6))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks the containment of a cuboid in a line.
	 * 
	 * @param l  a line to check
	 * @param c  a cuboid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(Line l, ICuboid c)
	{
		return false;
	}
	
	/**
	 * Checks the containment of an ellipsoid in a line.
	 * 
	 * @param l  a line to check
	 * @param e  an ellipsoid to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(Line l, IEllipsoid e)
	{
		return false;
	}
		
	/**
	 * Checks the containment of a line in a line.
	 * 
	 * @param l  a line to check
	 * @param m  a line to check
	 * @return  {@code true} if the shape is contained
	 */
	public static boolean in(Line l, Line m)
	{
		throw new UnsupportedOperationException("Line-line containment not implemented yet.");
	}
	
	
	
	private Containment()
	{
		// NOT APPLICABLE
	}
}