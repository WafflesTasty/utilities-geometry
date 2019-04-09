package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.lines.ILine;
import zeno.util.geom.collidables.geometry.higher.NSegment;
import zeno.util.geom.collidables.geometry.planar.Segment2D;
import zeno.util.geom.collidables.geometry.spatial.Segment3D;
import zeno.util.geom.utilities.Containment;
import zeno.util.geom.utilities.Intersection;

/**
 * The {@code ISegment} interface defines the base for line segment geometry.
 * 
 * @author Zeno
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 * @see ILine
 */
public interface ISegment extends IGeometry, ILine
{
	/**
	 * Creates a new {@code ISegment}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  a new line
	 * 
	 * 
	 * @see Vector
	 */
	public static ISegment create(Vector p1, Vector p2)
	{
		if(p1.Size() == 2)
			return new Segment2D((Vector2) p1, (Vector2) p2);
		if(p1.Size() == 3)
			return new Segment3D((Vector3) p1, (Vector3) p2);
		
		return new NSegment(p1, p2);
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
	public default boolean contains(ICuboid c)
	{
		return Containment.in(this, c);
	}


	@Override
	public default boolean intersects(ICuboid c)
	{
		return Intersection.between(this, c);
	}
	
	@Override
	public default boolean intersects(IEllipsoid e)
	{
		return Intersection.between(this, e);
	}
	
	@Override
	public default boolean intersects(ISegment l)
	{
		return Intersection.between(this, l);
	}
	
	@Override
	public default boolean intersects(ISphere s)
	{
		return Intersection.between(this, s);
	}
	
	
	@Override
	public default Vector Minimum()
	{
		return Center().minus(Size().times(0.5f));
	}

	@Override
	public default Vector Maximum()
	{
		return Center().plus(Size().times(0.5f));
	}
		
	@Override
	public default Vector Center()
	{
		return P1().plus(P2()).times(0.5f);
	}
	
	@Override
	public default Vector Size()
	{
		return P2().minus(P1()).Absolute();
	}
}