package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.dimension.any.shapes.NSphere;
import zeno.util.geom.dimension.three.shapes.Sphere;
import zeno.util.geom.dimension.two.shapes.Circle;
import zeno.util.geom.tools.Containment;
import zeno.util.geom.tools.Intersection;

/**
 * The {@code ISphere} interface defines the base for sphere geometry.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 * 
 * @see IEllipsoid
 */
public interface ISphere extends IEllipsoid
{
	/**
	 * Creates a new unit {@code ISphere}.
	 * 
	 * @param dim  the sphere's dimension
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
	 * @param center  the sphere's center
	 * @param radius  the sphere's radius
	 * @return  a new sphere
	 */
	public static ISphere create(Vector center, float radius)
	{
		if(center.size() == 2)
			return new Circle((Vector2) center, radius);
		if(center.size() == 3)
			return new Sphere((Vector3) center, radius);
		
		return new NSphere(center, radius);
	}
	

	/**
	 * Returns the diameter of the {@code ISphere}.
	 * 
	 * @return  the sphere's diameter
	 */
	public default float Diameter()
	{
		return Size().get(0);
	}
	
	/**
	 * Returns the radius of the {@code ISphere}.
	 * 
	 * @return  the sphere's radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
	}

	
	@Override
	public default boolean contains(Vector v)
	{
		return Containment.in(this, v);
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
	public default boolean intersects(Line l)
	{
		return Intersection.between(this, l);
	}
}