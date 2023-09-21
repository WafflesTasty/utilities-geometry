package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.axial.cuboid.BNDCuboid;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code CuboidND} defines an n-dimensional cuboid shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see AxialSet
 * @see ICuboid
 */
public class CuboidND extends AxialSet implements ICuboid
{	
	/**
	 * Creates a new {@code CuboidND}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public CuboidND(Vector s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code CuboidND}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public CuboidND(Vector c, Vector s)
	{
		super(c, s);
	}
		
	/**
	 * Creates a new {@code CuboidND}.
	 * 
	 * @param dim  a space dimension
	 */
	public CuboidND(int dim)
	{
		super(dim);
	}
	
	
	@Override
	public CuboidND create(Vector o, Vector s)
	{
		return new CuboidND(o, s);
	}
	
	@Override
	public Bounds Bounds(GlobalMap map)
	{
		return new BNDCuboid(this, map);
	}
		
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof CuboidND)
		{
			return super.equals(o);
		}
		
		return false;
	}
}