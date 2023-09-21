package waffles.utils.geom.collidable.fixed;

import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.fixed.BNDUniverse;
import waffles.utils.geom.bounds.fixed.BNDUniverse2D;
import waffles.utils.geom.bounds.fixed.BNDUniverse3D;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collision.CLSUniverse;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Universe} defines a geometry that spans the entire space.
 *
 * @author Waffles
 * @since 02 Jan 2022
 * @version 1.0
 * 
 * 
 * @see Geometry
 */
public class Universe implements Geometry
{
	private int dim;
	
	/**
	 * Creates a new {@code Geometry}.
	 * 
	 * @param dim  a space dimension
	 */
	public Universe(int dim)
	{
		this.dim = dim;
	}
	
	
	@Override
	public int Dimension()
	{
		return dim;
	}
	
	@Override
	public CLSUniverse Collisions()
	{
		return new CLSUniverse(this);
	}
	
	@Override
	public Bounds Bounds(GlobalMap map)
	{
		return Bounds();
	}
	
	@Override
	public Bounds Bounds()
	{
		if(dim == 2)
			return new BNDUniverse2D(this);
		if(dim == 3)
			return new BNDUniverse3D(this);

		return new BNDUniverse(this);
	}
}