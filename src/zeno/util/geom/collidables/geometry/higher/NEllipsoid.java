package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.AffineMap;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.geometry.Geometry;
import zeno.util.geom.collidables.geometry.bounds.BNDEllipsoid;
import zeno.util.geom.collidables.geometry.generic.IEllipsoid;

/**
 * The {@code NEllipsoid} class defines an n-dimensional ellipsoid shape.
 * 
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see IEllipsoid
 * @see Geometry
 */
public class NEllipsoid extends Geometry implements IEllipsoid
{	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param center  an ellipsoid center
	 * @param size    an ellipsoid size
	 * 
	 * 
	 * @see Vector
	 */
	public NEllipsoid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param size  an ellipsoid size
	 * 
	 * 
	 * @see Vector
	 */
	public NEllipsoid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NEllipsoid}.
	 * 
	 * @param dim  an ellipsoid dimension
	 */
	public NEllipsoid(int dim)
	{
		super(dim);
	}
	
	
	@Override
	public Bounds Bounds(AffineMap map)
	{
		return new BNDEllipsoid(this, map);
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