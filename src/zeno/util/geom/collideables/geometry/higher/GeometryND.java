package zeno.util.geom.collideables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collideables.IGeometry;
import zeno.util.geom.collideables.geometry.ICuboid;
import zeno.util.geom.collideables.geometry.higher.shapes.NCuboid;

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
		this(Vectors.create(size.Size()), size);
	}
	
	/**
	 * Creates a new {@code GeometryND}.
	 * 
	 * @param dim  the geometry's dimension
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

	private void setCenter(Vector center)
	{
		this.center = center;
	}
	
	private void setSize(Vector size)
	{
		this.size = size.Absolute();
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