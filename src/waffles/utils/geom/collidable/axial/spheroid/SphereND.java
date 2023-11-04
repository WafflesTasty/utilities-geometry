package waffles.utils.geom.collidable.axial.spheroid;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;

/**
 * A {@code SphereND} defines an n-dimensional sphere shape.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see SpheroidND
 * @see HyperSphere
 */
public class SphereND extends SpheroidND implements HyperSphere
{	
	/**
	 * Creates a new {@code SphereND}.
	 * 
	 * @param c  a point center
	 * @param r  a sphere radius
	 * 
	 * 
	 * @see Point
	 */
	public SphereND(Point c, float r)
	{
		this(c.Generator(), r);
	}
	
	/**
	 * Creates a new {@code SphereND}.
	 * 
	 * @param c  a center vector
	 * @param r  a sphere radius
	 * 
	 * 
	 * @see Vector
	 */
	public SphereND(Vector c, float r)
	{
		super(c, Vectors.create(2 * r, c.Size()));
	}
	
	/**
	 * Creates a new {@code SphereND}.
	 * 
	 * @param dim  a space dimension
	 * @param r    a sphere radius
	 */
	public SphereND(int dim, float r)
	{
		super(Vectors.create(2 * r, dim));
	}
	
	/**
	 * Creates a new {@code SphereND}.
	 * 
	 * @param dim  a space dimension
	 */
	public SphereND(int dim)
	{
		super(dim);
	}

	
	@Override
	public SphereND create(Vector o, Vector s)
	{
		return new SphereND(o, s.get(0) / 2);
	}
}