package zeno.util.geom._refactor.shapes.solids.lateral;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._refactor.shapes.solids.Lateral3D;

/**
 * The {@code Cube} class defines a two-dimensional cube shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see Lateral3D
 */
public class Cube extends Lateral3D
{
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param x  a center x-coördinate
	 * @param y  a center y-coördinate
	 * @param z  a center z-coördinate
	 * @param l  a cube length
	 */
	public Cube(float x, float y, float z, float l)
	{
		super(x, y, z, l, l, l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param center  a center
	 * @param l  a cube length
	 * @see Vector3
	 */
	public Cube(Vector3 center, float l)
	{
		this(center.X(), center.Y(), center.Z(), l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 * 
	 * @param l  a cube length
	 */
	public Cube(float l)
	{
		this(0, 0, 0, l);
	}
	
	/**
	 * Creates a new {@code Cube}.
	 */
	public Cube()
	{
		this(1);
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Cube)
		{
			return super.equals(o);
		}
		
		return false;
	}
	
	/**
	 * Changes the length of the {@code Cube}.
	 * 
	 * @param l  a new cube length
	 */
	public void setLength(float l)
	{
		super.setSize(Math.abs(l), Math.abs(l), Math.abs(l));
	}
			
	/**
	 * Returns the length of the {@code Cube}.
	 * 
	 * @return  the cube's length
	 */
	public float Length()
	{
		return Width();
	}
}