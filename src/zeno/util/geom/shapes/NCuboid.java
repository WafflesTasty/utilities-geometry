package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;
import zeno.util.geom.algorithms.LineClipper;
import zeno.util.tools.primitives.Floats;

public class NCuboid implements IGeometry
{
	private static final LineClipper clipper = new LineClipper();
	
	
	private Vector center, size;
	
	public NCuboid(Vector center, Vector size)
	{
		this.center = center;
		this.size = size;
	}

	public Vector Center()
	{
		return center;
	}
	
	public Vector Size()
	{
		return size;
	}
	
	public Vector Minimum()
	{
		return center.minus(size.times(0.5f));
	}
	
	public Vector Maximum()
	{
		return center.plus(size.times(0.5f));
	}
	
	
	@Override
	public NCuboid Bounds()
	{
		return this;
	}
	
	@Override
	public boolean contains(Line l)
	{
		return contains(l.P())
			&& contains(l.Q());
	}
	
	@Override
	public boolean contains(Vector v)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float vi = v.get(i);
			float si = size.get(i);
			float ci = center.get(i);
			
			if(si < 2 * Floats.abs(vi - ci))
			{
				return false;
			}
		}

		return true;
	}
	
	@Override
	public boolean contains(NCuboid c)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float si = size.get(i);
			float pi = center.get(i);
			float ti = c.Size().get(i);
			float qi = c.Center().get(i);
			
			if(si - ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(NEllipsoid e)
	{
		return contains(e.Bounds());
	}
	
	@Override
	public boolean intersects(Line l)
	{
		clipper.setBounds(this);
		if(clipper.clip(l.P(), l.Q()).isEmpty())
		{
			return false;
		}
		
		return true;
	}

	@Override
	public int Dimension()
	{
		return center.size();
	}

	
	@Override
	public boolean intersects(NCuboid c)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float si = size.get(i);
			float pi = center.get(i);
			float ti = c.Size().get(i);
			float qi = c.Center().get(i);
			
			if(si + ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}

		return true;
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