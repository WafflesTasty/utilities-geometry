package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.algorithms.LineClipper;
import zeno.util.geom.shapes.lines.Line;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code NCuboid} class defines an n-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NGeometry
 */
public class NCuboid extends NGeometry
{
	private static final LineClipper clipper = new LineClipper();
	
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param dim  the cuboid's dimension
	 */
	public NCuboid(int dim)
	{
		super(dim);
	}
	
		
	@Override
	public boolean contains(Line l)
	{
		return contains(l.P1())
			&& contains(l.P2());
	}
	
	@Override
	public boolean contains(Vector v)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float vi = v.get(i);
			float si = Size().get(i);
			float ci = Center().get(i);
			
			if(si < 2 * Floats.abs(vi - ci))
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
	public boolean contains(NCuboid c)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float si =   Size().get(i);
			float ti = c.Size().get(i);
			
			float pi =   Center().get(i);
			float qi = c.Center().get(i);
			
			if(si - ti < 2 * Floats.abs(pi - qi))
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
	
	@Override
	public boolean intersects(NCuboid c)
	{
		for(int i = 0; i < Dimension(); i++)
		{
			float si =   Size().get(i);
			float ti = c.Size().get(i);
			
			float pi =   Center().get(i);
			float qi = c.Center().get(i);
			
			if(si + ti < 2 * Floats.abs(pi - qi))
			{
				return false;
			}
		}

		return true;
	}
	
	@Override
	public boolean intersects(Line l)
	{
		clipper.setBounds(this);
		if(clipper.clip(l).isEmpty())
		{
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NCuboid)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	@Override
	public NCuboid Bounds()
	{
		return this;
	}
}