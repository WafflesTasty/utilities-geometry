package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.geometry.generic.ICube;

/**
 * The {@code NCube} class defines an n-dimensional cube shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see NCuboid
 * @see ICube
 */
public class NCube extends NCuboid implements ICube
{	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param center  a cube center
	 * @param length  a cube length
	 * 
	 * 
	 * @see Vector
	 */
	public NCube(Vector center, float length)
	{
		super(center, Vectors.create(length, center.Size()));
	}
	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param dim  a cube dimension
	 * @param length  a cube length
	 */
	public NCube(int dim, float length)
	{
		super(Vectors.create(length, dim));
	}
	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param dim  a cube dimension
	 */
	public NCube(int dim)
	{
		this(dim, 1f);
	}
}