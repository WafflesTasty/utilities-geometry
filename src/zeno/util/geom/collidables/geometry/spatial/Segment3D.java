package zeno.util.geom.collidables.geometry.spatial;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.IGeometry3D;
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
	 * @param x1  the line's first x-coördinate
	 * @param y1  the line's first y-coördinate
	 * @param z1  the line's first z-coördinate
	 * @param x2  the line's second x-coördinate
	 * @param y2  the line's second y-coördinate
	 * @param z2  the line's second z-coördinate
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
	 * Returns the first x-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first x-coördinate
	 */
	public float X1()
	{
		return P1().X();
	}
	
	/**
	 * Returns the first y-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first y-coördinate
	 */
	public float Y1()
	{
		return P1().Y();
	}
	
	/**
	 * Returns the first z-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's first z-coördinate
	 */
	public float Z1()
	{
		return P1().Z();
	}
	
	/**
	 * Returns the second x-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second x-coördinate
	 */
	public float X2()
	{
		return P2().X();
	}
	
	/**
	 * Returns the second y-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second y-coördinate
	 */
	public float Y2()
	{
		return P2().Y();
	}
	
	/**
	 * Returns the second z-coördinate of the {@code Segment3D}.
	 * 
	 * @return  the line's second z-coördinate
	 */
	public float Z2()
	{
		return P2().Z();
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