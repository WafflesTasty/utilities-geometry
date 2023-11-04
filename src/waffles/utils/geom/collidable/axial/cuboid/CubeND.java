package waffles.utils.geom.collidable.axial.cuboid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;

/**
 * A {@code CuboidND} defines an n-dimensional cube shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see CuboidND
 * @see HyperCube
 */
public class CubeND extends CuboidND implements HyperCube
{	
	/**
	 * Creates a new {@code CubeND}.
	 * 
	 * @param c  a center vector
	 * @param l  a cube length
	 * 
	 * 
	 * @see Vector
	 */
	public CubeND(Vector c, float l)
	{
		super(c, Vectors.create(l, c.Size()));
	}
	
	/**
	 * Creates a new {@code CubeND}.
	 * 
	 * @param dim  a space dimension
	 * @param l    a cube length
	 */
	public CubeND(int dim, float l)
	{
		super(Vectors.create(l, dim));
	}
	
	/**
	 * Creates a new {@code CubeND}.
	 * 
	 * @param dim  a cube dimension
	 */
	public CubeND(int dim)
	{
		super(dim);
	}
}