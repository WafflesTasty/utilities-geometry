package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom._deprecated.collideables.lines.ILine;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.geometry.higher.NEllipsoid;
import zeno.util.geom.collidables.geometry.planar.Ellipse;
import zeno.util.geom.collidables.geometry.spatial.Ellipsoid;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;
import zeno.util.tools.Floats;

/**
 * The {IEllipsoid} interface defines the base for ellipsoid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IEllipsoid extends IGeometry
{
	/**
	 * Creates a new {@code IEllipsoid}.
	 * 
	 * @param center  an ellipsoid center
	 * @param size    an ellipsoid size
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
	public default boolean contains(Vector p)
	{
		return Containment.in(this, p);
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
		return Floats.max(Size().Values());
	}
}