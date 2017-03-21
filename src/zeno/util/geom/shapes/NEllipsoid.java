package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.lines.Line;

/**
 * The {@code NEllipsoid} class defines an n-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NGeometry
 */
public class NEllipsoid extends NGeometry
{	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param center  the ellipsoid's center
	 * @param size  the ellipsoid's size
	 * @see Vector
	 */
	public NEllipsoid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param size  the ellipsoid's size
	 * @see Vector
	 */
	public NEllipsoid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param dim  the ellipsoid's dimension
	 */
	public NEllipsoid(int dim)
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
		float val, sum = 0;
		for(int i = 0; i < Dimension(); i++)
		{
			val = v.get(i) - Center().get(i);
			if(val == 0) continue;
			
			val /= Size().get(i);
			sum += val * val;
			if(4 * sum > 1)
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean contains(NCuboid c)
	{
		return contains(c.Minimum())
			&& contains(c.Maximum());
	}
	
	@Override
	public boolean contains(NSphere s)
	{
		throw new UnsupportedOperationException("Ellipsoid-sphere containment not implemented yet.");
	}
	
	@Override
	public boolean contains(NEllipsoid e)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(e));
	}

	@Override
	public boolean intersects(NEllipsoid e)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(e));
	}

	@Override
	public boolean intersects(NSphere s)
	{
		return s.intersects(this);
	}

	@Override
	public boolean intersects(NCuboid c)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(c));
	}

	@Override
	public boolean intersects(Line l)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(l));
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NEllipsoid)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	@Override
	public NCuboid Bounds()
	{
		return new NCuboid(Center(), Size());
	}
	
	
	private NEllipsoid transform(NEllipsoid e)
	{
		Vector uSize = e.Size();
		Vector uCent = e.Center().minus(Center());
		
		for(int i = 0; i < Dimension(); i++)
		{
			uCent.set(uCent.get(i) / Size().get(i), i);
			uSize.set(uSize.get(i) / Size().get(i), i);
		}
		
		return new NEllipsoid(uCent, uSize);
	}
	
	private NCuboid transform(NCuboid e)
	{
		Vector uSize = e.Size();
		Vector uCent = e.Center().minus(Center());
		
		for(int i = 0; i < Dimension(); i++)
		{
			uCent.set(uCent.get(i) / Size().get(i), i);
			uSize.set(uSize.get(i) / Size().get(i), i);
		}
		
		return new NCuboid(uCent, uSize);
	}
	
	private Line transform(Line l)
	{
		Vector p1 = l.P1().minus(Center());
		Vector p2 = l.P2().minus(Center());
		
		for(int i = 0; i < Dimension(); i++)
		{
			p1.set(p1.get(i) / Size().get(i), i);
			p2.set(p2.get(i) / Size().get(i), i);
		}
		
		return new Line(p1, p2);
	}
}