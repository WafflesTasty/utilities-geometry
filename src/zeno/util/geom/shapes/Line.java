package zeno.util.geom.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.dimension.any.lines.NLine;
import zeno.util.geom.dimension.three.lines.Line3D;
import zeno.util.geom.dimension.two.lines.Line2D;
import zeno.util.geom.tools.Containment;
import zeno.util.geom.tools.Intersection;

/**
 * The {@code Line} interface defines the base for line geometry.
 * 
 * @since Mar 25, 2017
 * @author Zeno
 * 
 * @see IGeometry
 */
public interface Line extends IGeometry
{
	/**
	 * Creates a new {@code Line}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  a new line
	 */
	public static Line create(Vector p1, Vector p2)
	{
		if(p1.size() == 2)
			return new Line2D((Vector2) p1, (Vector2) p2);
		if(p1.size() == 3)
			return new Line3D((Vector3) p1, (Vector3) p2);
		
		return new NLine(p1, p2);
	}
	
	
	/**
	 * Returns the first point of the {@code Line}.
	 * 
	 * @return  the line's first point
	 * @see Vector
	 */
	public abstract Vector P1();
	
	/**
	 * Returns the second point of the {@code Line}.
	 * 
	 * @return  the line's second point
	 * @see Vector
	 */
	public abstract Vector P2();

	
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

		
	@Override
	public default Vector Center()
	{
		return P1().plus(P2()).times(0.5f);
	}
	
	@Override
	public default Vector Size()
	{
		return P2().minus(P1());
	}
}