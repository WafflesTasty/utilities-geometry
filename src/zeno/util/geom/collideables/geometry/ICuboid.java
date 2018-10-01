package zeno.util.geom.collideables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collideables.IGeometry;
import zeno.util.geom.collideables.geometry.higher.shapes.NCuboid;
import zeno.util.geom.collideables.geometry.planar.shapes.Rectangle;
import zeno.util.geom.collideables.geometry.spatial.shapes.Cuboid;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

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
		if(center.Size() == 2)
			return new Rectangle((Vector2) center, (Vector2) size);
		if(center.Size() == 3)
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
		return create(Vectors.create(dim), Vectors.create(dim));
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