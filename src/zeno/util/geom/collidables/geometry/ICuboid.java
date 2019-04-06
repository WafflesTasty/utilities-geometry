package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom._deprecated.collideables.affine.Point;
import zeno.util.geom._deprecated.collideables.geometry.higher.shapes.NCuboid;
import zeno.util.geom._deprecated.collideables.geometry.planar.shapes.Rectangle;
import zeno.util.geom._deprecated.collideables.geometry.spatial.shapes.Cuboid;
import zeno.util.geom._deprecated.collideables.lines.ILine;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {ICuboid} interface defines the base for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ICuboid extends IGeometry
{
	/**
	 * Creates a unit {@code ICuboid}.
	 * 
	 * @param dim  a cuboid dimension
	 * @return  a unit cuboid
	 */
	public static ICuboid unit(int dim)
	{
		return ICube.unit(dim);
	}
	
	/**
	 * Creates a new {@code ICuboid}.
	 * 
	 * @param center  a cuboid center
	 * @param size    a cuboid size
	 * @return  a new cuboid
	 * 
	 * 
	 * @see Vector
	 */
	public static ICuboid create(Vector center, Vector size)
	{
		if(center.Size() == 2)
			return new Rectangle((Vector2) center, (Vector2) size);
		if(center.Size() == 3)
			return new Cuboid((Vector3) center, (Vector3) size);
		
		return new NCuboid(center, size);
	}
		
	
	@Override
	public default boolean contains(Point p)
	{
		return Containment.in(this, p);
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
	public default ICuboid Box()
	{
		return this;
	}
}