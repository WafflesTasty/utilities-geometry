package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom._deprecated.collideables.lines.ILine;
import zeno.util.geom.collidables.geometry.higher.NSphere;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.collidables.geometry.spatial.Sphere;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {@code ISphere} interface defines the base for sphere geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IEllipsoid
 */
public interface ISphere extends IEllipsoid
{
	/**
	 * Creates a unit {@code ISphere}.
	 * 
	 * @param dim  a sphere dimension
	 * @return  a unit sphere
	 */
	public static ISphere unit(int dim)
	{
		if(dim <= 1) return null;
		if(dim == 2) return new Circle();
		if(dim == 3) return new Sphere();
		return new NSphere(dim);
	}
	
	/**
	 * Creates a new {@code ISphere}.
	 * 
	 * @param center  a sphere center
	 * @param radius  a sphere radius
	 * @return  a new sphere
	 * 
	 * 
	 * @see Vector
	 */
	public static ISphere create(Vector center, float radius)
	{
		if(center.Size() == 2)
			return new Circle((Vector2) center, radius);
		if(center.Size() == 3)
			return new Sphere((Vector3) center, radius);
		
		return new NSphere(center, radius);
	}
	
	
	@Override
	public default boolean contains(Vector p)
	{
		return Containment.in(this, p);
	}
		
	@Override
	public default boolean contains(IEllipsoid e)
	{
		return Containment.in(this, e);
	}
	
	@Override
	public default boolean contains(ISphere s)
	{
		return Containment.in(this, s);
	}
	
	
	@Override
	public default boolean intersects(ISphere s)
	{
		return Intersection.between(this, s);
	}
	
	@Override
	public default boolean intersects(IEllipsoid e)
	{
		return Intersection.between(this, e);
	}
	
	@Override
	public default boolean intersects(ICuboid c)
	{
		return Intersection.between(this, c);
	}
	
	@Override
	public default boolean intersects(ILine l)
	{
		return Intersection.between(this, l);
	}

	
	@Override
	public default float Diameter()
	{
		return Size().get(0);
	}

	@Override
	public default ISphere Ball()
	{
		return this;
	}
}