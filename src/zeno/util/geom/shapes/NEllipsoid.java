package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;

public class NEllipsoid implements IGeometry
{
	private Vector center, size;
	
	public NEllipsoid(Vector center, Vector size)
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

	@Override
	public int Dimension()
	{
		return center.size();
	}

	@Override
	public NCuboid Bounds()
	{
		return new NCuboid(center, size);
	}

	@Override
	public boolean contains(Vector v)
	{
		float val, sum = 0;
		for(int i = 0; i < Dimension(); i++)
		{
			val = v.get(i) - center.get(i);
			if(val == 0) continue;
			val /= size.get(i);
			
			sum += val * val;
			if(4 * sum > 1)
			{
				return false;
			}
		}
		
		return true;
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
	public boolean contains(NCuboid c)
	{
		return contains(c.Minimum())
			&& contains(c.Maximum());
	}

	@Override
	public boolean contains(Line l)
	{
		return contains(l.P())
			&& contains(l.Q());
	}

	@Override
	public boolean intersects(Line l)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(l));
	}

	@Override
	public boolean intersects(NCuboid c)
	{
		NSphere unit = new NSphere(Dimension());
		return unit.intersects(transform(c));
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
	
	
	private NEllipsoid transform(NEllipsoid e)
	{
		Vector uSize = e.Size();
		Vector uCent = e.Center().minus(center);
		
		for(int i = 0; i < Dimension(); i++)
		{
			uCent.set(uCent.get(i) / size.get(i), i);
			uSize.set(uSize.get(i) / size.get(i), i);
		}
		
		return new NEllipsoid(uCent, uSize);
	}
	
	private NCuboid transform(NCuboid e)
	{
		Vector uSize = e.Size();
		Vector uCent = e.Center().minus(center);
		
		for(int i = 0; i < Dimension(); i++)
		{
			uCent.set(uCent.get(i) / size.get(i), i);
			uSize.set(uSize.get(i) / size.get(i), i);
		}
		
		return new NCuboid(uCent, uSize);
	}
	
	private Line transform(Line l)
	{
		Vector p = l.P().minus(center);
		Vector q = l.Q().minus(center);
		
		for(int i = 0; i < Dimension(); i++)
		{
			p.set(p.get(i) / size.get(i), i);
			q.set(q.get(i) / size.get(i), i);
		}
		
		return new Line(p, q);
	}
}