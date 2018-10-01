package zeno.util.geom.collideables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collideables.IGeometry;
import zeno.util.geom.collideables.geometry.higher.shapes.NEllipsoid;
import zeno.util.geom.collideables.geometry.planar.shapes.Ellipse;
import zeno.util.geom.collideables.geometry.spatial.shapes.Ellipsoid;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {IEllipsoid} interface defines the base for ellipsoid geometry.
 * 
 * @since Mar 24, 2017
 * @author Zeno
 * 
 * @see IGeometry
 */
public interface IEllipsoid extends IGeometry
{
	/**
	 * Creates a new {@code IEllipsoid}.
	 * 
	 * @param center  the ellipsoid's center
	 * @param size  the ellipsoid's size
	 * @return  a new ellipsoid
	 */
	public static IEllipsoid create(Vector center, Vector size)
	{
		if(center.Size() == 2)
			return new Ellipse((Vector2) center, (Vector2) size);
		if(center.Size() == 3)
			return new Ellipsoid((Vector3) center, (Vector3) size);
		
		return new NEllipsoid(center, size);
	}
	
	
	@Override
	public default boolean contains(Vector v)
	{
		return Containment.in(this, v);
	}
		
	@Override
	public default boolean contains(ISphere s)
	{
		return Containment.in(this, s);
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
