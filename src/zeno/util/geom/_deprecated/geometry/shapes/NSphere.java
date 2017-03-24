package zeno.util.geom._deprecated.geometry.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom._deprecated.geometry.shapes.lines.Line;
import zeno.util.geom._deprecated.geometry.types.ISphere;

/**
 * The {@code NSphere} class defines an n-dimensional sphere shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 * @see Sphere
 */
public class NSphere extends NEllipsoid implements ISphere
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
	public boolean intersects(NSphere s)
	{
		float rad = Radius() + s.Radius();
		Vector pq = Center().minus(s.Center());
		return pq.normsqr() <= rad * rad;
	}
			
	@Override
	public boolean intersects(NEllipsoid c)
	{
		throw new UnsupportedOperationException("Sphere-ellipsoid intersection not implemented yet.");
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