package zeno.util.geom.shapes.lines;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.interfaces.IGeometry3D;
import zeno.util.geom.shapes.solids.Cuboid;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Line3D} class defines a three-dimensional line segment.
 * 
 * @since Jul 5, 2016
 * @author Zeno
 * 
 * @see IGeometry3D
 * @see Line
 */
public class Line3D extends Line implements IGeometry3D
{
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
	 */
	public Line3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		super(x1, y1, z1, x2, y2, z2);
	}
	
	/**
	 * Creates a new {@code Line3D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @see Vector3
	 */
	public Line3D(Vector3 p1, Vector3 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Line3D}.
	 */
	public Line3D()
	{
		this(-.5f, -.5f, -.5f, .5f, .5f, .5f);
	}
	
	
	
	/**
	 * Changes the first point of the {@code Line3D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 */
	public void setP1(float x, float y, float z)
	{
		setP1(new Vector3(x, y, z));
	}
	
	/**
	 * Changes the second point of the {@code Line3D}.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 */
	public void setP2(float x, float y, float z)
	{
		setP2(new Vector3(x, y, z));
	}
	
	/**
	 * Changes the first point of the {@code Line3D}.
	 * 
	 * @param p  a point
	 * @see Vector3
	 */
	public void setP1(Vector3 p)
	{
		super.setP1(p);
	}
	
	/**
	 * Changes the second point of the {@code Line3D}.
	 * 
	 * @param p  a point
	 * @see Vector3
	 */
	public void setP2(Vector3 p)
	{
		super.setP2(p);
	}

	
	/**
	 * Returns the first x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return P1().X();
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return P1().Y();
	}
	
	/**
	 * Returns the first z-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's first z-coördinate
	 */
	public float Z1()
	{
		return P1().Z();
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return P2().X();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return P2().Y();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Line3D}.
	 * 
	 * @return  the line's second z-coördinate
	 */
	public float Z2()
	{
		return P2().Z();
	}

	
	
	@Override
	public Cuboid Bounds()
	{
		return IGeometry3D.super.Bounds();
	}
	
	@Override
	public Vector3 P1()
	{
		return (Vector3) super.P1();
	}
	
	@Override
	public Vector3 P2()
	{
		return (Vector3) super.P2();
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

	@Override
	public float ZMin()
	{
		return Floats.min(Z1(), Z2());
	}
	
	@Override
	public float ZMax()
	{
		return Floats.max(Z1(), Z2());
	}
}