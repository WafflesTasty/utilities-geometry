package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.dimension.any.shapes.NCuboid;
import zeno.util.geom.dimension.three.shapes.Cuboid;
import zeno.util.geom.dimension.two.shapes.Rectangle;
import zeno.util.geom.tools.Containment;
import zeno.util.geom.tools.Intersection;

/**
 * The {ICuboid} interface defines the base for cuboid geometry.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 * 
 * @see IGeometry
 */
public interface ICuboid extends IGeometry
{
	/**
	 * Creates a new {@code ICuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @return  a new cuboid
	 */
	public static ICuboid create(Vector center, Vector size)
	{
		if(center.size() == 2)
			return new Rectangle((Vector2) center, (Vector2) size);
		if(center.size() == 3)
			return new Cuboid((Vector3) center, (Vector3) size);
		
		return new NCuboid(center, size);
	}
	
	/**
	 * Creates a new {@code ICuboid}.
	 * 
	 * @param dim  the cuboid's dimension
	 * @return  a new cuboid
	 */
	public static ICuboid create(int dim)
	{
		return create(Vector.create(dim), Vector.create(dim));
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
	public default boolean contains(ICuboid c)
	{
		return Containment.in(this, c);
	}
	
	@Override
	public default boolean contains(Line l)
	{
		return Containment.in(this, l);
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