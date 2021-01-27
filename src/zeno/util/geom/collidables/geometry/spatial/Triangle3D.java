package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry3D;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.higher.NTriangle;
import zeno.util.tools.Floats;

/**
 * The {@code Triangle3D} class defines a three-dimensional triangle.
 *
 * @author Zeno
 * @since 13 Jan 2021
 * @version 1.0
 * 
 * 
 * @see IGeometry3D
 * @see NTriangle
 */
public class Triangle3D extends NTriangle implements IGeometry3D
{
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param x1  a first x-coordinate
	 * @param y1  a first y-coordinate
	 * @param z1  a first z-coordinate
	 * @param x2  a second x-coordinate
	 * @param y2  a second y-coordinate
	 * @param z2  a second z-coordinate
 	 * @param x3  a third x-coordinate
	 * @param y3  a third y-coordinate
	 * @param z3  a third z-coordinate
	 */
	public Triangle3D(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
	{
		this
		(
			new Point(x1, y1, z1, 1f),
			new Point(x2, y2, z2, 1f),
			new Point(x3, y3, z3, 1f)
		);
	}
	
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param p1  a first point
	 * @param p2  a second point
	 * @param p3  a third point
	 * 
	 * 
	 * @see Vector3
	 */
	public Triangle3D(Vector3 p1, Vector3 p2, Vector3 p3)
	{
		super(p1, p2, p3);
	}
	
	/**
	 * Creates a new {@code Triangle3D}.
	 * 
	 * @param p1  a first point
	 * @param p2  a second point
	 * @param p3  a third point
	 * 
	 * 
	 * @see Point
	 */
	public Triangle3D(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}
	
		
	/**
	 * Returns the first x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a first x-coordinate
	 */
	public float X1()
	{
		return P1().get(0) / P1().Mass();
	}
	
	/**
	 * Returns the first y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a first y-coordinate
	 */
	public float Y1()
	{
		return P1().get(1) / P1().Mass();
	}
	
	/**
	 * Returns the first z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a first z-coordinate
	 */
	public float Z1()
	{
		return P1().get(2) / P1().Mass();
	}
	
	/**
	 * Returns the second x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a second x-coordinate
	 */
	public float X2()
	{
		return P2().get(0) / P2().Mass();
	}
	
	/**
	 * Returns the second y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a second y-coordinate
	 */
	public float Y2()
	{
		return P2().get(1) / P2().Mass();
	}
	
	/**
	 * Returns the second z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a second z-coordinate
	 */
	public float Z2()
	{
		return P2().get(2) / P2().Mass();
	}
	
	/**
	 * Returns the third x-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a third x-coordinate
	 */
	public float X3()
	{
		return P3().get(0) / P3().Mass();
	}
	
	/**
	 * Returns the third y-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a third y-coordinate
	 */
	public float Y3()
	{
		return P3().get(1) / P3().Mass();
	}
	
	/**
	 * Returns the third z-coordinate of the {@code Triangle3D}.
	 * 
	 * @return  a third z-coordinate
	 */
	public float Z3()
	{
		return P3().get(2) / P3().Mass();
	}

	
	@Override
	public Bounds3D Bounds(ITransformation map)
	{
		return (Bounds3D) super.Bounds(map);
	}

	// Obligatory Bounds overrides.
	
	@Override
	public Vector3 Minimum()
	{
		return (Vector3) super.Minimum();
	}
	
	@Override
	public Vector3 Maximum()
	{
		return (Vector3) super.Maximum();
	}
		
	@Override
	public Vector3 Center()
	{
		return (Vector3) super.Center();
	}
	
	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
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
	
	@Override
	public float ZMin()
	{
		return Floats.min(Z1(), Z2(), Z3());
	}
	
	@Override
	public float ZMax()
	{
		return Floats.max(Z1(), Z2(), Z3());
	}
}