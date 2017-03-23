package zeno.util.geom.geometry.shapes.lines;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.geometry.shapes.surfaces.Rectangle;
import zeno.util.geom.geometry.types.IGeometry2D;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Line2D} class defines a two-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry2D
 * @see Line
 */
public class Line2D extends Line implements IGeometry2D
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
		super(x1, y1, x2, y2);
	}
	
	/**
	 * Creates a new {@code Line2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector2
	 */
	public Line2D(Vector2 p1, Vector2 p2)
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
	
	
	
	/**
	 * Changes the first point of the {@code Line2D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 */
	public void setP1(float x, float y)
	{
		setP1(new Vector2(x, y));
	}
	
	/**
	 * Changes the second point of the {@code Line2D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 */
	public void setP2(float x, float y)
	{
		setP2(new Vector2(x, y));
	}
	
	/**
	 * Changes the first point of the {@code Line2D}.
	 * 
	 * @param p  a point
	 * @see Vector2
	 */
	public void setP1(Vector2 p)
	{
		super.setP1(p);
	}
	
	/**
	 * Changes the second point of the {@code Line2D}.
	 * 
	 * @param p  a point
	 * @see Vector2
	 */
	public void setP2(Vector2 p)
	{
		super.setP2(p);
	}

	
	/**
	 * Returns the first x-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return P1().X();
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return P1().Y();
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return P2().X();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line2D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return P2().Y();
	}

	
	
	@Override
	public Rectangle Bounds()
	{
		return IGeometry2D.super.Bounds();
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

	
	@Override
	public float XMin()
	{
		return Floats.min(X1(), X2());
	}
	
	@Override
	public float XMax()
	{
		return Floats.max(X1(), X2());
	}
	
	@Override
	public float YMin()
	{
		return Floats.min(Y1(), Y2());
	}
	
	@Override
	public float YMax()
	{
		return Floats.max(Y1(), Y2());
	}
}