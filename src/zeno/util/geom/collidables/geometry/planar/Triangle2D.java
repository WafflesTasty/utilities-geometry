package zeno.util.geom.collidables.geometry.planar;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry2D;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds2D;
import zeno.util.geom.collidables.geometry.higher.NTriangle;
import zeno.util.tools.Floats;

/**
 * The {@code Triangle2D} class defines a two-dimensional triangle.
 *
 * @author Zeno
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see IGeometry2D
 * @see NTriangle
 */
public class Triangle2D extends NTriangle implements IGeometry2D
{
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param x1  a first x-coordinate
	 * @param y1  a first y-coordinate
	 * @param x2  a second x-coordinate
	 * @param y2  a second y-coordinate
 	 * @param x3  a third x-coordinate
	 * @param y3  a third y-coordinate
	 */
	public Triangle2D(float x1, float y1, float x2, float y2, float x3, float y3)
	{
		this
		(
			new Point(x1, y1, 1f),
			new Point(x2, y2, 1f),
			new Point(x3, y3, 1f)
		);
	}
	
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param p1  a first point
	 * @param p2  a second point
	 * @param p3  a third point
	 * 
	 * 
	 * @see Vector2
	 */
	public Triangle2D(Vector2 p1, Vector2 p2, Vector2 p3)
	{
		super(p1, p2, p3);
	}
	
	/**
	 * Creates a new {@code Triangle2D}.
	 * 
	 * @param p1  a first point
	 * @param p2  a second point
	 * @param p3  a third point
	 * 
	 * 
	 * @see Point
	 */
	public Triangle2D(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}
	
		
	/**
	 * Returns the first x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a first x-coordinate
	 */
	public float X1()
	{
		return P1().get(0) / P1().Mass();
	}
	
	/**
	 * Returns the first y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a first y-coordinate
	 */
	public float Y1()
	{
		return P1().get(1) / P1().Mass();
	}
	
	/**
	 * Returns the second x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a second x-coordinate
	 */
	public float X2()
	{
		return P2().get(0) / P2().Mass();
	}
	
	/**
	 * Returns the second y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a second y-coordinate
	 */
	public float Y2()
	{
		return P2().get(1) / P2().Mass();
	}
	
	/**
	 * Returns the third x-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a third x-coordinate
	 */
	public float X3()
	{
		return P3().get(0) / P3().Mass();
	}
	
	/**
	 * Returns the third y-coordinate of the {@code Triangle2D}.
	 * 
	 * @return  a third y-coordinate
	 */
	public float Y3()
	{
		return P3().get(1) / P3().Mass();
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
		return (Vector2) super.Minimum();
	}
	
	@Override
	public Vector2 Maximum()
	{
		return (Vector2) super.Maximum();
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
		return Floats.min(X1(), X2(), X3());
	}
	
	@Override
	public float XMax()
	{
		return Floats.max(X1(), X2(), X3());
	}
	
	@Override
	public float YMin()
	{
		return Floats.min(Y1(), Y2(), Y3());
	}
	
	@Override
	public float YMax()
	{
		return Floats.max(Y1(), Y2(), Y3());
	}
}