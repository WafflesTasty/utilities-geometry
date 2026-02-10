package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid2D;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code Ellipse} implements two-dimensional {@code HyperSpheroid} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperSpheroid2D
 * @see SpheroidND
 */
public class Ellipse extends SpheroidND implements HyperSpheroid2D
{
	/**
	 * Creates a new {@code Ellipse}.
	 *
	 * @param x  an origin x
	 * @param y  an origin y
	 * @param w  a scale width
	 * @param h  a scale height
	 */
	public Ellipse(float x, float y, float w, float h)
	{
		this(new Point(x, y, 1f), new Arrow(w, h));
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 *
	 * @param w  a scale width
	 * @param h  a scale height
	 */
	public Ellipse(float w, float h)
	{
		this(new Arrow(w, h));
	}

	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param c  an origin point
	 * @param s  a scale point
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public Ellipse(Point c, Arrow s)
	{
		super(c, s);
	}

	/**
	 * Creates a new {@code Ellipse}.
	 * 
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arrow
	 */
	public Ellipse(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}
	
	/**
	 * Creates a new {@code Ellipse}.
	 */
	public Ellipse()
	{
		super(2);
	}
}