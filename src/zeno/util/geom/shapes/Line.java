package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.matrices.Matrix;
import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;
import zeno.util.tools.primitives.Floats;

public class Line implements IGeometry
{
	/**
	 * Find a maximum vector component.
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
	
	
	private Vector p, q;
	
	public Line(Vector p, Vector q)
	{
		this.p = p;
		this.q = q;
	}
	
	public Vector P()
	{
		return p;
	}
	
	public Vector Q()
	{
		return q;
	}

	
	@Override
	public NCuboid Bounds()
	{
		Vector center = p.plus(q).times(0.5f);
		Vector size = q.minus(p).absolute();
		return new NCuboid(center, size);
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
		float xmin = Floats.min(p.get(0), q.get(0));
		float xmax = Floats.max(p.get(0), q.get(0));
		
		if(v.get(0) < xmin || xmax < v.get(0))
		{
			return false;
		}
		
		
		// Check if lambda value is unique.
		for(int i = 0; i < Dimension() - 1; i++)
		{
			float val1 = (v.get(i+0) - p.get(i+0)) * (q.get(i+1) - p.get(i+1));
			float val2 = (v.get(i+1) - p.get(i+1)) * (q.get(i+0) - p.get(i+0));
			
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
	public boolean intersects(Line l)
	{
		Vector pq =   P().minus(  Q());
		Vector pr = l.P().minus(  P());
		Vector rs = l.P().minus(l.Q());
		
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
		Vector x = P().plus(l.P())
				  .plus(pq.times(l1))
				  .plus(rs.times(l2));
		
		return l.contains(x)
			&&   contains(x);
	}
	
	@Override
	public int Dimension()
	{
		return p.size();
	}

	
	@Override
	public boolean contains(NEllipsoid e)
	{
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
	public boolean intersects(NEllipsoid e)
	{
		if(e != null)
		{
			return e.intersects(this);
		}
		
		return false;
	}
}
