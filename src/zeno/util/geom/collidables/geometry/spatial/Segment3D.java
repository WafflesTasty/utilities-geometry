package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.AffineMap;
import zeno.util.geom.collidables.IGeometry3D;
import zeno.util.geom.collidables.bounds.Bounds3D;
import zeno.util.geom.collidables.geometry.higher.NSegment;
import zeno.util.tools.Floats;

/**
 * The {@code Segment3D} class defines a three-dimensional line segment.
 * 
 * @author Zeno
 * @since Jul 5, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry3D
 * @see NSegment
 */
public class Segment3D extends NSegment implements IGeometry3D
{
	/**
	 * Creates a new {@code Segment3D}.
	 * 
	 * @param x1  the line's first x-co�rdinate
	 * @param y1  the line's first y-co�rdinate
	 * @param z1  the line's first z-co�rdinate
	 * @param x2  the line's second x-co�rdinate
	 * @param y2  the line's second y-co�rdinate
	 * @param z2  the line's second z-co�rdinate
	 */
	public Segment3D(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		super(x1, y1, z1, x2, y2, z2);
	}
	
	/**
	 * Creates a new {@code Segment3D}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * 
	 * 
	 * @see Vector3
	 */
	public Segment3D(Vector3 p1, Vector3 p2)
	{
		super(p1, p2);
	}

	/**
	 * Creates a new {@code Segment3D}.
	 */
	public Segment3D()
	{
		this(-1f, -1f, -1f, 1f, 1f, 1f);
	}
	
		
	/**
	 * Returns the first x-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first x-co�rdinate
	 */
	public float X1()
	{
		return P1().get(0);
	}
	
	/**
	 * Returns the first y-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first y-co�rdinate
	 */
	public float Y1()
	{
		return P1().get(1);
	}
	
	/**
	 * Returns the first z-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first z-co�rdinate
	 */
	public float Z1()
	{
		return P1().get(2);
	}
	
	/**
	 * Returns the second x-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second x-co�rdinate
	 */
	public float X2()
	{
		return P2().get(0);
	}
	
	/**
	 * Returns the second y-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second y-co�rdinate
	 */
	public float Y2()
	{
		return P2().get(1);
	}
	
	/**
	 * Returns the second z-co�rdinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second z-co�rdinate
	 */
	public float Z2()
	{
		return P2().get(2);
	}

	
	@Override
	public Bounds3D Bounds(AffineMap map)
	{
		return (Bounds3D) super.Bounds(map);
	}
	
	// Obligatory Bounds overrides.
	
	@Override
	public Vector3 Minimum()
	{
		return IGeometry3D.super.Minimum();
	}
	
	@Override
	public Vector3 Maximum()
	{
		return IGeometry3D.super.Maximum();
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