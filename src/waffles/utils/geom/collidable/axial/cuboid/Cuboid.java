package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.bounds.axial.cuboid.BNDCuboid3D;
import waffles.utils.geom.collidable.axial.AxialSet3D;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Cuboid} defines a three-dimensional cuboid shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see AxialSet3D
 * @see ICuboid
 */
public class Cuboid extends AxialSet3D implements ICuboid
{	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param z  a center z-coordinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Cuboid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
		
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Cuboid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Cuboid(Vector3 c, Vector3 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Cuboid(Vector3 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Cuboid}.
	 */
	public Cuboid()
	{
		this(2f, 2f, 2f);
	}

	
	@Override
	public Cuboid create(Vector o, Vector s)
	{
		return new Cuboid
		(	(Vector3) o, 
			(Vector3) s
		);
	}
	
	@Override
	public Bounds3D Bounds(GlobalMap map)
	{
		return new BNDCuboid3D(this, map);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Cuboid)
		{
			return super.equals(o);
		}
		
		return false;
	}
}