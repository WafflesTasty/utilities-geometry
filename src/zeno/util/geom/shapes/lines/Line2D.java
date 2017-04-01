package zeno.util.geom.shapes.lines;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom.IGeometry2D;
import zeno.util.geom.shapes.surfaces.Rectangle;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Line2D} class defines a two-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry2D
 * @see NLine
 */
public class Line2D extends NLine implements IGeometry2D
{
	private Rectangle bounds;
	
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
		if(bounds == null)
		{
			float x = (X1() + X2()) / 2;
			float y = (Y1() + Y2()) / 2;
			
			float w = Floats.abs(X1() - X2());
			float h = Floats.abs(Y1() - Y2());
			
			bounds = new Rectangle(x, y, w, h);
		}
		
		return bounds;
	}
	
	@Override
	public Vector2 Center()
	{
		return (Vector2) super.Center();
	}
	
	@Override
	public Vector2 Size()
	{
		return (Vector2) super.Size();
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