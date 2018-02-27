package zeno.util.geom.dimension.any.shapes;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.dimension.any.GeometryND;
import zeno.util.geom.utilities.shapes.IEllipsoid;

/**
 * The {@code NEllipsoid} class defines an n-dimensional ellipsoid shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IEllipsoid
 * @see GeometryND
 */
public class NEllipsoid extends GeometryND implements IEllipsoid
{	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param center  the ellipsoid's center
	 * @param size  the ellipsoid's size
	 * @see Vector
	 */
	public NEllipsoid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param size  the ellipsoid's size
	 * @see Vector
	 */
	public NEllipsoid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param dim  the ellipsoid's dimension
	 */
	public NEllipsoid(int dim)
	{
		super(dim);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NEllipsoid)
		{
			return super.equals(o);
		}
		
		return false;
	}
}