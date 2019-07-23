package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.tools.Floats;

/**
 * The {@code Geometry} class is the base class for bounded n-dimensional shapes.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public abstract class Geometry implements IGeometry
{		
	private Vector center, size;
		
	/**
	 * Creates a new {@code Geometry}.
	 * 
	 * @param center  a geometry center
	 * @param size    a geometry size
	 * 
	 * 
	 * @see Vector
	 */
	public Geometry(Vector center, Vector size)
	{
		this.size = size.Absolute();
		this.center = center;
	}
	
	/**
	 * Creates a new {@code Geometry}.
	 * 
	 * @param size  a geometry size
	 * 
	 * 
	 * @see Vector
	 */
	public Geometry(Vector size)
	{
		this(Vectors.create(size.Size()), size);
	}
	
	/**
	 * Creates a new {@code Geometry}.
	 * 
	 * @param dim  a geometry dimension
	 */
	public Geometry(int dim)
	{
		this(Vectors.create(2f, dim));
	}
	
	
	@Override
	public int hashCode()
	{
		int code = 1;
		
		code = code * 59 + center.hashCode();
		code = code * 67 + size.hashCode();
		
		return code;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Geometry)
		{
			Geometry oGeometry = (Geometry) o;
			return center.equals(oGeometry.center)
				&& size.equals(oGeometry.size);
		}
		
		return false;
	}

	@Override
	public boolean isEmpty()
	{
		return Floats.isZero(size.norm(), Dimension());
	}
	
	
	// Obligatory Bounds overrides.
	
	@Override
	public float Diameter()
	{
		return size.norm();
	}
	
	@Override
	public Vector Center()
	{
		return center;
	}
	
	@Override
	public Vector Size()
	{
		return size;
	}
}