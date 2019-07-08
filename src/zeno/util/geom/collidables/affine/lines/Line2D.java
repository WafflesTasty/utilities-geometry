package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ICollideable2D;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;

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
	 * @param x1  the line's first x-co�rdinate
	 * @param y1  the line's first y-co�rdinate
	 * @param x2  the line's second x-co�rdinate
	 * @param y2  the line's second y-co�rdinate
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
	 * @see Point
	 */
	public Line2D(Point p1, Point p2)
	{
		super((Point) ASpaces.occupy(p1, 2), (Point) ASpaces.occupy(p2, 2));
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 */
	public Line2D()
	{
		this(-.5f, -.5f, .5f, .5f);
	}
	
	
	/**
	 * Returns the first x-co�rdinate of the {@code Line2D}.
	 * 
	 * @return  the line's first x-co�rdinate
	 */
	public float X1()
	{
		return P1().X();
	}
	
	/**
	 * Returns the first y-co�rdinate of the {@code Line2D}.
	 * 
	 * @return  the line's first y-co�rdinate
	 */
	public float Y1()
	{
		return P1().Y();
	}
	
	/**
	 * Returns the second x-co�rdinate of the {@code Line2D}.
	 * 
	 * @return  the line's second x-co�rdinate
	 */
	public float X2()
	{
		return P2().X();
	}
	
	/**
	 * Returns the second y-co�rdinate of the {@code Line2D}.
	 * 
	 * @return  the line's second y-co�rdinate
	 */
	public float Y2()
	{
		return P2().Y();
	}

	
	@Override
	public Vector2 P1()
	{
		return (Vector2) super.P1();
	}
	
	@Override
	public Vector2 P2()
	{
		return (Vector2) super.P2();
	}
}