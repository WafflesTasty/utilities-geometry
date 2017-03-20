package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;

public class NSphere extends NEllipsoid
{
	public NSphere(Vector center, float diameter)
	{
		super(center, Vector.create(diameter, center.size()));
	}

	public NSphere(int dim)
	{
		this(Vector.create(dim), 1f);
	}
	
	@Override
	public boolean contains(Vector v)
	{
		float rad = Radius();
		Vector px = v.minus(Center());
		return px.normsqr() <= rad * rad;
	}
	
	@Override
	public boolean contains(NEllipsoid e)
	{
		throw new UnsupportedOperationException("Sphere-ellipsoid containment not implemented yet.");
	}
	
	@Override
	public boolean contains(NSphere s)
	{
		float rad = Radius() - s.Radius();
		Vector pq = Center().minus(s.Center());
		return pq.normsqr() <= rad * rad;
	}
	
	@Override
	public boolean intersects(Line l)
	{
		Vector qt = l.Q().minus(l.P());
		Vector qp = Center().minus(l.P());
		
		float lam = qp.dot(qt);
		if(lam != 0) lam /= qt.dot(qt);
		if(lam < 0 || 1 < lam) return false;
		
		
		float rad = Radius();
		Vector v = l.P().plus(qt.times(lam));
		return v.normsqr() <= rad * rad;
	}
	
	@Override
	public boolean intersects(NCuboid c)
	{
		throw new UnsupportedOperationException("Sphere-cuboid intersection not implemented yet.");
	}
	
	@Override
	public boolean intersects(NEllipsoid c)
	{
		throw new UnsupportedOperationException("Sphere-ellipsoid intersection not implemented yet.");
	}
	
	@Override
	public boolean intersects(NSphere s)
	{
		float rad = Radius() + s.Radius();
		Vector pq = Center().minus(s.Center());
		return pq.normsqr() <= rad * rad;
	}

	public float Radius()
	{
		return Size().get(0);
	}
}
