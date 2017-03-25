package zeno.util.geom.shapes.other;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.ICuboid;

/**
 * The {@code NCuboid} class defines an n-dimensional rectangular shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NGeometry
 * @see ICuboid
 */
public class NCuboid extends NGeometry implements ICuboid
{
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param center  the cuboid's center
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector center, Vector size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param size  the cuboid's size
	 * @see Vector
	 */
	public NCuboid(Vector size)
	{
		super(size);
	}
	
	/**
	 * Creates a new {@code NCuboid}.
	 * 
	 * @param dim  the cuboid's dimension
	 */
	public NCuboid(int dim)
	{
		super(dim);
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
	
	@Override
	public NCuboid Bounds()
	{
		return this;
	}
}