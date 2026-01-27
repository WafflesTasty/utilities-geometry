package waffles.utils.geom.spatial.maps.data.structs;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.linear.Translation;

/**
 * A {@code Position} defines a basic {@code Positioned} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.1
 * 
 * 
 * @see Positioned
 */
public class Position implements Positioned.Mutable
{
	private Point origin;

	/**
	 * Creates a new {@code Position}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Position(Point o)
	{
		origin = o;
	}
	
	/**
	 * Creates a new {@code Position}.
	 * 
	 * @param o  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Position(Vector o)
	{
		this(new Point(o, 1f));
	}
	
	/**
	 * Creates a new {@code Position}.
	 * 
	 * @param dim  an axis dimension
	 */
	public Position(int dim)
	{
		this(Translation.Default(dim));
	}
	
	/**
	 * Creates a new {@code Position}.
	 */
	public Position()
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public Point Origin()
	{
		return origin;
	}
	
	@Override
	public void setOrigin(Point o)
	{
		origin = o;
	}
	
	@Override
	public int Dimension()
	{
		return origin.Dimension();
	}
}