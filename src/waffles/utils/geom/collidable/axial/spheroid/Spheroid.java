package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.bounds.axial.spheroid.BNDSpheroid3D;
import waffles.utils.geom.collidable.axial.AxialSet3D;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Spheroid} defines a three-dimensional spheroid shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see AxialSet3D
 * @see HyperSpheroid
 */
public class Spheroid extends AxialSet3D implements HyperSpheroid
{	
	/**
	 * Creates a new {@code Spheroid}.
	 * 
	 * @param x  a center x-coordinate
	 * @param y  a center y-coordinate
	 * @param z  a center z-coordinate
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Spheroid(float x, float y, float z, float w, float h, float d)
	{
		this(new Vector3(x, y, z), new Vector3(w, h, d));
	}
		
	/**
	 * Creates a new {@code Spheroid}.
	 * 
	 * @param w  a width
	 * @param h  a height
	 * @param d  a depth
	 */
	public Spheroid(float w, float h, float d)
	{
		this(0, 0, 0, w, h, d);
	}
	
	/**
	 * Creates a new {@code Spheroid}.
	 * 
	 * @param c  a center vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Spheroid(Vector3 c, Vector3 s)
	{
		super(c, s);
	}
	
	/**
	 * Creates a new {@code Spheroid}.
	 * 
	 * @param c  a point center
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 * @see Point
	 */
	public Spheroid(Point c, Vector3 s)
	{
		this(c.Generator(), s);
	}
	
	/**
	 * Creates a new {@code Spheroid}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector3
	 */
	public Spheroid(Vector3 s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Spheroid}.
	 */
	public Spheroid()
	{
		this(2f, 2f, 2f);
	}


	@Override
	public Spheroid create(Vector o, Vector s)
	{
		int dim = s.Size();
		
		float avg = s.norm1() / dim;
		for(int i = 0; i < dim; i++)
		{
			float val = s.get(i);
			if(!Floats.isEqual(val, avg, 3))
			{
				return new Spheroid
				(
					(Vector3) o, 
					(Vector3) s
				);
			}
		}
		
		return new Sphere((Vector3) o, avg / 2);
	}
	
	@Override
	public Bounds3D Bounds(GlobalMap map)
	{
		return new BNDSpheroid3D(this, map);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Spheroid)
		{
			return super.equals(o);
		}
		
		return false;
	}
}