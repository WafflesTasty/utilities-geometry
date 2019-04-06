package zeno.util.geom.collidables.geometry.higher;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.geometry.generic.ISphere;

/**
 * The {@code NSphere} class defines an n-dimensional sphere shape.
 *
 * @author Zeno
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see NEllipsoid
 * @see ISphere
 */
public class NSphere extends NEllipsoid implements ISphere
{	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param center  a sphere center
	 * @param radius  a sphere length
	 * 
	 * 
	 * @see Vector
	 */
	public NSphere(Vector center, float radius)
	{
		super(center, Vectors.create(2 * radius, center.Size()));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  a sphere dimension
	 * @param radius  a sphere length
	 */
	public NSphere(int dim, float radius)
	{
		super(Vectors.create(2 * radius, dim));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  a sphere dimension
	 */
	public NSphere(int dim)
	{
		this(dim, 2f);
	}
}