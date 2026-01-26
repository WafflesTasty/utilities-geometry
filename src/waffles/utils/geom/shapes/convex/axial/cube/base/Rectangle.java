package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Rectangle} implements two-dimensional {@code HyperCuboid} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see HyperCuboid2D
 * @see CuboidND
 */
public class Rectangle extends CuboidND implements HyperCuboid2D
{			
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param w  a scale width
	 * @param h  a scale height
	 */
	public Rectangle(float x, float y, float w, float h)
	{
		this(new Point(x, y, 1f), new Arrow(w, h));
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param w  a scale width
	 * @param h  a scale height
	 */
	public Rectangle(float w, float h)
	{
		this(new Arrow(w, h));
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param c  an origin point
	 * @param s  a scale point
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public Rectangle(Point c, Arrow s)
	{
		super(c, s);
	}
		
	/**
	 * Creates a new {@code Rectangle}.
	 * 
	 * @param s  a scale point
	 * 
	 * 
	 * @see Arrow
	 */
	public Rectangle(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}
	
	/**
	 * Creates a new {@code Rectangle}.
	 */
	public Rectangle()
	{
		super(2);
	}
}