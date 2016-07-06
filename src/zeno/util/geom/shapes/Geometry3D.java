package zeno.util.geom.shapes;

import zeno.util.algebra.Floats;
import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.geom.Geometry;
import zeno.util.geom.tools.bounds.IBound3D;

/**
 * The {@code Geometry3D} class is the base for any convex three-dimensional geometric shape.
 *
 * @author Zeno
 * @since Aug 22, 2015
 * @see IBound3D
 * @see Geometry
 */
public abstract class Geometry3D implements Geometry, IBound3D
{
	private Vector3 center, size;
	
	/**
	 * Creates a new {@code Geometry3D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param w  a geometry width
	 * @param h  a geometry height
	 * @param d  a geometry depth
	 */
	public Geometry3D(float x, float y, float z, float w, float h, float d)
	{
		center = new Vector3(x, y, z);
		size = new Vector3(w, h, d);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 */
	public Geometry3D()
	{
		this(0, 0, 0, 1, 1, 1);
	}
	
	
	/**
	 * Indicates if the {@code Geometry3D} intersects a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid intersects
	 */
	public abstract boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2);
	
	/**
	 * Indicates if the {@code Geometry3D} contains a cuboid.
	 * 
	 * @param x1  the cuboid's first x-coördinate
	 * @param y1  the cuboid's first y-coördinate
	 * @param z1  the cuboid's first z-coördinate
	 * @param x2  the cuboid's second x-coördinate
	 * @param y2  the cuboid's second y-coördinate
	 * @param z2  the cuboid's second z-coördinate
	 * @return  {@code true} if the cuboid contains
	 */
	public abstract boolean contains(float x1, float y1, float z1, float x2, float y2, float z2);
	
	/**
	 * Indicates if the {@code Geometry3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return  {@code true} if the point is contained
	 */
	public abstract boolean contains(float x, float y, float z);
		
	/**
	 * Returns the vertices of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's vertices
	 * @see Vector3
	 */
	@Override
	public abstract Vector3[] Vertices();

		
	/**
	 * Indicates if the {@code Geometry3D} intersects a cuboid.
	 * 
	 * @param cube  a cuboid to check
	 * @return  {@code true} if the cuboid is intersected
	 * @see IBound3D
	 */
	public final boolean intersects(IBound3D cube)
	{
		return intersects(cube.XMin(), cube.YMin(), cube.ZMin(), cube.XMax(), cube.YMax(), cube.ZMax());
	}
		
	/**
	 * Indicates if the {@code Geometry3D} contains a cuboid.
	 * 
	 * @param cube  a cuboid to check
	 * @return  {@code true} if the cuboid is contained
	 * @see IBound3D
	 */
	public final boolean contains(IBound3D cube)
	{
		return contains(cube.XMin(), cube.YMin(), cube.ZMin(), cube.XMax(), cube.YMax(), cube.ZMax());
	}
	
	/**
	 * Indicates if the {@code Geometry3D} contains a point.
	 * 
	 * @param v  a point to check
	 * @return  {@code true} if the point is contained
	 * @see Vector3
	 */
	public final boolean contains(Vector3 v)
	{
		return contains(v.X(), v.Y(), v.Z());
	}

	
	/**
	 * Changes the center of the {@code Geometry3D}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 */
	public void setCenter(float x, float y, float z)
	{
		center = new Vector3(x, y, z);
	}
	
	/**
	 * Changes the center of the {@code Geometry3D}.
	 * 
	 * @param center  a new center
	 * @see Vector3
	 */
	public void setCenter(Vector3 center)
	{
		setCenter(center.X(), center.Y(), center.Z());
	}
	
	/**
	 * Changes the center x of the {@code Geometry3D}.
	 * 
	 * @param x  a new center x
	 */
	public void setX(float x)
	{
		center.setX(x);
	}

	/**
	 * Changes the center y of the {@code Geometry3D}.
	 * 
	 * @param y  a new center y
	 */
	public void setY(float y)
	{
		center.setY(y);
	}
	
	/**
	 * Changes the center z of the {@code Geometry3D}.
	 * 
	 * @param z  a new center z
	 */
	public void setZ(float z)
	{
		center.setZ(z);
	}
	

	/**
	 * Changes the size of the {@code Geometry3D}.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 * @param d  a new depth
	 */
	protected void setSize(float w, float h, float d)
	{
		size = new Vector3(w, h, d);
	}
	
	/**
	 * Changes the size of the {@code Geometry3D}.
	 * 
	 * @param size  a new size
	 * @see Vector3
	 */
	protected void setSize(Vector3 size)
	{
		setSize(size.X(), size.Y(), size.Z());
	}

	/**
	 * Changes the height of the {@code Geometry3D}.
	 * 
	 * @param h  a new height
	 */
	protected void setHeight(float h)
	{
		setSize(Width(), h, Depth());
	}

	/**
	 * Changes the depth of the {@code Geometry3D}.
	 * 
	 * @param d  a new depth
	 */
	protected void setDepth(float d)
	{
		setSize(Width(), Height(), d);
	}
	
	/**
	 * Changes the width of the {@code Geometry3D}.
	 * 
	 * @param w  a new width
	 */
	protected void setWidth(float w)
	{
		setSize(w, Height(), Depth());
	}


	/**
	 * Returns the center of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's center
	 * @see Vector3
	 */
	public Vector3 Center()
	{
		return center;
	}
	
	/**
	 * Returns the center x of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's center x
	 */
	public float X()
	{
		return center.X();
	}
	
	/**
	 * Returns the center y of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's center y
	 */
	public float Y()
	{
		return center.Y();
	}
	
	/**
	 * Returns the center z of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's center z
	 */
	public float Z()
	{
		return center.Z();
	}
	
	
	/**
	 * Returns the size of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's size
	 * @see Vector3
	 */
	protected Vector3 Size()
	{
		return size;
	}
	
	/**
	 * Returns the height of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's height
	 */
	protected float Height()
	{
		return size.Y();
	}
	
	/**
	 * Returns the width of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's width
	 */
	protected float Width()
	{
		return size.X();
	}
	
	/**
	 * Returns the depth of the {@code Geometry3D}.
	 * 
	 * @return  the geometry's depth
	 */
	protected float Depth()
	{
		return size.Z();
	}
			
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Geometry3D)
		{
			Geometry3D oGeom = (Geometry3D) o;
			return Floats.isEqual(Height(), oGeom.Height())
				&& Floats.isEqual(Width(), oGeom.Width())
				&& Floats.isEqual(Depth(), oGeom.Depth())
				&& Floats.isEqual(X(), oGeom.X())
				&& Floats.isEqual(Y(), oGeom.Y())
				&& Floats.isEqual(Z(), oGeom.Z());
		}
		
		return false;
	}
		
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 17 + Float.hashCode(center.X());
		code = code * 37 + Float.hashCode(center.Y());
		code = code * 13 + Float.hashCode(center.Z());
		code = code * 43 + Float.hashCode(size.X());
		code = code * 23 + Float.hashCode(size.Y());
		code = code * 31 + Float.hashCode(size.Z());
		
		return code;
	}

	
	@Override
	public float XMin()
	{
		return X() - Width() / 2;
	}

	@Override
	public float XMax()
	{
		return X() + Width() / 2;
	}

	@Override
	public float YMin()
	{
		return Y() - Height() / 2;
	}

	@Override
	public float YMax()
	{
		return Y() + Height() / 2;
	}
	
	@Override
	public float ZMin()
	{
		return Z() - Depth() / 2;
	}

	@Override
	public float ZMax()
	{
		return Z() + Depth() / 2;
	}
}