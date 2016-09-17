package zeno.util.geom.shapes;

import zeno.util.algebra.vectors.fixed.Vector3;
import zeno.util.tools.primitives.Floats;

/**
 * The {@code Geometry3D} class defines a three-dimensional convex shape
 * using a center, width and height.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry3D
 */
public abstract class Geometry3D implements IGeometry3D
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
		
		code = code * 17 + Floats.hashCode(center.X());
		code = code * 37 + Floats.hashCode(center.Y());
		code = code * 13 + Floats.hashCode(center.Z());
		code = code * 43 + Floats.hashCode(size.X());
		code = code * 23 + Floats.hashCode(size.Y());
		code = code * 31 + Floats.hashCode(size.Z());
		
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