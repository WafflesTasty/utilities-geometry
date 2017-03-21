package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.lines.Line;

/**
 * The {@code NSphere} class defines an n-dimensional sphere shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 */
public class NSphere extends NEllipsoid
{	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param center  the sphere's center
	 * @param radius  the sphere's length
	 * @see Vector
	 */
	public NSphere(Vector center, float radius)
	{
		super(center, Vector.create(2 * radius, center.size()));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  the sphere's dimension
	 * @param radius  the sphere's length
	 */
	public NSphere(int dim, float radius)
	{
		super(Vector.create(2 * radius, dim));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  the sphere's dimension
	 */
	public NSphere(int dim)
	{
		this(dim, 1f);
	}
	
		
	/**
	 * Returns the diameter of the {@code NSphere}.
	 * 
	 * @return  the sphere's diameter
	 */
	public float Diameter()
	{
		return Size().get(0);
	}
	
	/**
	 * Returns the radius of the {@code NSphere}.
	 * 
	 * @return  the sphere's radius
	 */
	public float Radius()
	{
		return Diameter() / 2;
	}
	
	
	@Override
	public boolean contains(Vector v)
	{
		float rad = Radius();
		Vector px = v.minus(Center());
		return px.normsqr() <= rad * rad;
	}
	
	@Override
	public boolean contains(NSphere s)
	{
		float rad = Radius() - s.Radius();
		Vector pq = Center().minus(s.Center());
		return pq.normsqr() <= rad * rad;
	}
	
	@Override
	public boolean contains(NEllipsoid e)
	{
		throw new UnsupportedOperationException("Sphere-ellipsoid containment not implemented yet.");
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
	
	@Override
	public boolean intersects(NCuboid c)
	{
		throw new UnsupportedOperationException("Sphere-cuboid intersection not implemented yet.");
	}
	
	@Override
	public boolean intersects(Line l)
	{
		Vector qt = l.P2().minus(l.P1());
		Vector qp = Center().minus(l.P1());
		
		
		float lam = qp.dot(qt);
		if(lam != 0) lam /= qt.dot(qt);
		if(lam < 0 || 1 < lam) return false;
		
		
		float rad = Radius();
		Vector v = l.P1().plus(qt.times(lam));
		return v.normsqr() <= rad * rad;
	}
}