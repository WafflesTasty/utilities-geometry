package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;
import zeno.util.geom.algorithms.LineClipper;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code NCuboid} class defines an n-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IGeometry
 */
public class NCuboid implements IGeometry
{
	private static final LineClipper clipper = new LineClipper();
	
	
	private Vector center, size;
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector center, Vector size)
	{
		setCenter(center);
		setSize(size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector size)
	{
		this(Vector.create(size.size()), size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param dim  the cuboid's dimension
	 */
	public NCuboid(int dim)
	{
		this(Vector.create(dim));
	}
	
		
	/**
	 * Returns the minimum of the {@code NCuboid}.
	 * 
	 * @return  the cuboid's minimum
	 * @see Vector
	 */
	public Vector Minimum()
	{
		return center.minus(size.times(0.5f));
	}
	
	/**
	 * Returns the maximum of the {@code NCuboid}.
	 * 
	 * @return  the cuboid's maximum
	 * @see Vector
	 */
	public Vector Maximum()
	{
		return center.plus(size.times(0.5f));
	}

	/**
	 * Returns the center of the {@code NCuboid}.
	 * 
	 * @return  the cuboid's center
	 * @see Vector
	 */
	public Vector Center()
	{
		return center;
	}
	
	/**
	 * Returns the size of the {@code NCuboid}.
	 * 
	 * @return  the cuboid's size
	 * @see Vector
	 */
	public Vector Size()
	{
		return size;
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
	public NCuboid Bounds()
	{
		return this;
	}
	
	@Override
	public int Dimension()
	{
		return center.size();
	}

	
	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 59 + center.hashCode();
		code = code * 67 + size.hashCode();
		
		return code;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NCuboid)
		{
			NCuboid oCuboid = (NCuboid) o;
			return center.equals(oCuboid.center)
				&& size.equals(oCuboid.size);
		}
		
		return false;
	}

	
	protected void setSize(Vector size)
	{
		this.size = size.absolute();
	}
	
	protected void setCenter(Vector center)
	{
		this.center = center;
	}
}