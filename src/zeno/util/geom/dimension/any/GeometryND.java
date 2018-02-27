package zeno.util.geom.dimension.any;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.IGeometry;
import zeno.util.geom.dimension.any.shapes.NCuboid;
import zeno.util.geom.shapes.ICuboid;

/**
 * The {@code GeometryND} class is the base class for closed n-dimensional shapes.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IGeometry
 */
public abstract class GeometryND implements IGeometry
{
	private Vector center, size;
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param center  the geometry's center
	 * @param size  the geometry's size
	 * @see Vector
	 */
	public GeometryND(Vector center, Vector size)
	{
		setCenter(center);
		setSize(size);
	}
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param size  the geometry's size
	 * @see Vector
	 */
	public GeometryND(Vector size)
	{
		this(Vector.create(size.size()), size);
	}
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param dim  the geometry's dimension
	 */
	public GeometryND(int dim)
	{
		this(Vector.create(1f, dim));
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

	private void setCenter(Vector center)
	{
		this.center = center;
	}
	
	private void setSize(Vector size)
	{
		this.size = size.absolute();
	}

	
	@Override
	public ICuboid Bounds()
	{
		return new NCuboid(Center(), Size());
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