package zeno.util.geom.collideables.geometry.higher.shapes;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collideables.geometry.ICube;

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
		super(center, Vectors.create(length, center.Size()));
	}
	
	/**
	 * Creates a new {@code NCube}.
	 * 
	 * @param dim  the cube's dimension
	 * @param length  the cube's length
	 */
	public NCube(int dim, float length)
	{
		super(Vectors.create(length, dim));
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