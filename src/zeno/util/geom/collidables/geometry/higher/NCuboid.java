package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.geometry.Geometry;
import zeno.util.geom.collidables.geometry.bounds.BNDCuboid;
import zeno.util.geom.collidables.geometry.generic.ICuboid;

/**
 * The {@code NCuboid} class defines an n-dimensional rectangular shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see ICuboid
 */
public class NCuboid extends Geometry implements ICuboid
{
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param center  a cuboid center
	 * @param size    a cuboid size
	 * 
	 * 
	 * @see Vector
	 */
	public NCuboid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param size  a cuboid size
	 * 
	 * 
	 * @see Vector
	 */
	public NCuboid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param dim  a cuboid dimension
	 */
	public NCuboid(int dim)
	{
		super(dim);
	}
	
	
	@Override
	public Bounds Bounds(ITransformation map)
	{
		return new BNDCuboid(this, map);
	}
		
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof NCuboid)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	// Optional Bounds overrides.
	
	@Override
	public NCuboid Box()
	{
		return this;
	}
}