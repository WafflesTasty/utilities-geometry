package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.IGeometry;

/**
 * The {@code GeometryND} class is the base class for closed n-dimensional shapes.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public abstract class GeometryND implements IGeometry
{		
	private Vector center, size;
		
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param center  a geometry center
	 * @param size    a geometry size
	 * 
	 * 
	 * @see Vector
	 */
	public GeometryND(Vector center, Vector size)
	{
		this.size = size.Absolute();
		this.center = center;
	}
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param size  a geometry size
	 * 
	 * 
	 * @see Vector
	 */
	public GeometryND(Vector size)
	{
		this(Vectors.create(size.Size()), size);
	}
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param dim  a geometry dimension
	 */
	public GeometryND(int dim)
	{
		this(Vectors.create(1f, dim));
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
		if(o instanceof GeometryND)
		{
			GeometryND oGeometry = (GeometryND) o;
			return center.equals(oGeometry.center)
				&& size.equals(oGeometry.size);
		}
		
		return false;
	}

	
	@Override
	public Vector Minimum()
	{
		return center.minus(size.times(0.5f));
	}
	
	@Override
	public Vector Maximum()
	{
		return center.plus(size.times(0.5f));
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