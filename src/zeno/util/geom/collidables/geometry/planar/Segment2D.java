package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry2D;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.higher.NSegment;
import zeno.util.tools.Floats;

/**
 * The {@code Segment2D} class defines a two-dimensional line segment.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry2D
 * @see NSegment
 */
public class Segment2D extends NSegment implements IGeometry2D
{			
	/**
	 * Creates a new {@code Segment2D}.
	 * 
	 * @param x1  the line's first x-co�rdinate
	 * @param y1  the line's first y-co�rdinate
	 * @param x2  the line's second x-co�rdinate
	 * @param y2  the line's second y-co�rdinate
	 */
	public Segment2D(float x1, float y1, float x2, float y2)
	{
		super(x1, y1, x2, y2);
	}
	
	/**
	 * Creates a new {@code Segment2D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector2
	 */
	public Segment2D(Vector2 p1, Vector2 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Segment2D}.
	 */
	public Segment2D()
	{
		this(-1f, -1f, 1f, 1f);
	}
	
	
	/**
	 * Returns the first x-co�rdinate of the {@code Segment2D}.
	 * 
	 * @return  the line's first x-co�rdinate
	 */
	public float X1()
	{
		return P1().get(0);
	}
	
	/**
	 * Returns the first y-co�rdinate of the {@code Segment2D}.
	 * 
	 * @return  the line's first y-co�rdinate
	 */
	public float Y1()
	{
		return P1().get(1);
	}
	
	/**
	 * Returns the second x-co�rdinate of the {@code Segment2D}.
	 * 
	 * @return  the line's second x-co�rdinate
	 */
	public float X2()
	{
		return P2().get(0);
	}
	
	/**
	 * Returns the second y-co�rdinate of the {@code Segment2D}.
	 * 
	 * @return  the line's second y-co�rdinate
	 */
	public float Y2()
	{
		return P2().get(1);
	}

	
	@Override
	public Bounds2D Bounds(ITransformation map)
	{
		return (Bounds2D) super.Bounds(map);
	}
	
	// Obligatory Bounds overrides.
	
	@Override
	public Vector2 Minimum()
	{
		return IGeometry2D.super.Minimum();
	}
	
	@Override
	public Vector2 Maximum()
	{
		return IGeometry2D.super.Maximum();
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

	
	// Optional Bounds overrides.
	
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