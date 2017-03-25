package zeno.util.geom.shapes.other;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.ICube;

/**
 * The {@code NCube} class defines an n-dimensional cube shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NCuboid
 * @see ICube
 */
public class NCube extends NCuboid implements ICube
{	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param center  the cube's center
	 * @param length  the cube's length
	 * @see Vector
	 */
	public NCube(Vector center, float length)
	{
		super(center, Vector.create(length, center.size()));
	}
	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param dim  the cube's dimension
	 * @param length  the cube's length
	 */
	public NCube(int dim, float length)
	{
		super(Vector.create(length, dim));
	}
	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param dim  the cube's dimension
	 */
	public NCube(int dim)
	{
		this(dim, 1f);
	}
}