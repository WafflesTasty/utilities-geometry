package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.matrices.Matrix;
import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Line} class defines an n-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry
 */
public class Line implements IGeometry
{
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
		for(int k = 0; k < v.size(); k++)
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
	
	
	private Vector p1, p2;
		
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector
	 */
	public Line(Vector p1, Vector p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Creates a new {@code Line}.
	 * The parameter takes in a list containing
	 * the components of both points in sequence.
	 * 
	 * @param vals  a list of values
	 */
	public Line(float... vals)
	{
		Vector[] split = Vector.split(2, vals);
		
		p1 = split[0];
		p2 = split[1];
	}
	
	/**
	 * Creates a new {@code Line}.
	 * The created line fits in the unit cube.
	 * 
	 * @param dim  the line's dimension
	 */
	public Line(int dim)
	{
		this(Vector.create(-0.5f, dim), Vector.create(0.5f, dim));
	}
	
		
	/**
	 * Returns the first point of the {@code Line}.
	 * 
	 * @return  the line's first point
	 * @see Vector
	 */
	public Vector P1()
	{
		return p1;
	}
	
	/**
	 * Returns the second point of the {@code Line}.
	 * 
	 * @return  the line's second point
	 * @see Vector
	 */
	public Vector P2()
	{
		return p2;
	}

	
	
	@Override
	public boolean contains(Line l)
	{
		throw new UnsupportedOperationException("Line-line containment not implemented yet.");
	}

	@Override
	public boolean contains(Vector v)
	{
		// Check if lambda is between 0 and 1.
		float xmin = Floats.min(p1.get(0), p2.get(0));
		float xmax = Floats.max(p1.get(0), p2.get(0));
		
		if(v.get(0) < xmin || xmax < v.get(0))
		{
			return false;
		}
		
		
		// Check if lambda value is unique.
		for(int i = 0; i < Dimension() - 1; i++)
		{
			float val1 = (v.get(i+0) - p1.get(i+0)) * (p2.get(i+1) - p1.get(i+1));
			float val2 = (v.get(i+1) - p1.get(i+1)) * (p2.get(i+0) - p1.get(i+0));
			
			if(Floats.isEqual(val1, val2, 6))
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean contains(NCuboid c)
	{
		return false;
	}
		
	@Override
	public boolean contains(NEllipsoid e)
	{
		return false;
	}
	
	@Override
	public boolean intersects(NEllipsoid e)
	{
		if(e != null)
		{
			return e.intersects(this);
		}
		
		return false;
	}
	
	@Override
	public boolean intersects(NCuboid c)
	{
		if(c != null)
		{
			return c.intersects(this);
		}
		
		return false;
	}
	
	@Override
	public boolean intersects(Line l)
	{
		Vector pq =   p1.minus(  p2);
		Vector pr = l.p1.minus(  p1);
		Vector rs = l.p1.minus(l.p2);
		
		// Maximize components.
		int i = maximize(pq, -1);
		int j = maximize(rs,  i);
		
		
		// Solve linear system.
		Matrix m = (Matrix) Matrix.create(3, 2);
		
		m.set( pq.get(i), 0, 0);
		m.set( pq.get(j), 0, 1);
		m.set(-rs.get(i), 1, 0);
		m.set(-rs.get(j), 1, 1);
		m.set( pr.get(i), 2, 0);
		m.set( pr.get(j), 2, 1);
		
		Vector v = m.solve();
		if(v == null)
		{
			return false;
		}
		
		
		float l1 = v.get(0);
		float l2 = v.get(1);
		
		// Create intersection.
		Vector x = p1.plus(l.p1)
				  .plus(pq.times(l1))
				  .plus(rs.times(l2));
		
		return l.contains(x)
			&&   contains(x);
	}
	
	
	@Override
	public NCuboid Bounds()
	{
		Vector center = p1.plus(p2).times(0.5f);
		Vector size = p2.minus(p1).absolute();
		return new NCuboid(center, size);
	}
	
	@Override
	public int Dimension()
	{
		return p1.size();
	}

	
	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + p1.hashCode();
		code = code * 43 + p2.hashCode();
		
		return code;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Line)
		{
			Line oLine = (Line) o;
			return (p1.equals(oLine.p1)
				 && p2.equals(oLine.p2))
				 ||(p1.equals(oLine.p2)
				 && p2.equals(oLine.p1));
		}
		
		return false;
	}
	
	
	protected void setP1(Vector p1)
	{
		this.p1 = p1;
	}
	
	protected void setP2(Vector p2)
	{
		this.p2 = p2;
	}
}
