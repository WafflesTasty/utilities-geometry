package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ICollideable2D;
import zeno.util.geom.collidables.affine.APoint;

/**
 * The {@code Line2D} class defines a two-dimensional line space.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see ICollideable2D
 * @see LineND
 */
public class Line2D extends LineND implements ICollideable2D
{		
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 */
	public Line2D(float x1, float y1, float x2, float y2)
	{
		this(new Vector2(x1, y1), new Vector2(x2, y2));
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector2
	 */
	public Line2D(Vector2 p1, Vector2 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector2
	 * @see APoint
	 */
	public Line2D(APoint p1, Vector2 p2)
	{
		super(p1, p2);
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see APoint
	 */
	public Line2D(APoint p1, APoint p2)
	{
		super(p1, p2);
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 */
	public Line2D()
	{
		this(-.5f, -.5f, .5f, .5f);
	}
}