package zeno.util.geom.collideables.geometry.higher.shapes;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collideables.geometry.ISphere;

/**
 * The {@code NSphere} class defines an n-dimensional sphere shape.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see NEllipsoid
 * @see ISphere
 */
public class NSphere extends NEllipsoid implements ISphere
{	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param center  the sphere's center
	 * @param radius  the sphere's length
	 * @see Vector
	 */
	public NSphere(Vector center, float radius)
	{
		super(center, Vectors.create(2 * radius, center.Size()));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  the sphere's dimension
	 * @param radius  the sphere's length
	 */
	public NSphere(int dim, float radius)
	{
		super(Vectors.create(2 * radius, dim));
	}
	
	/**
	 * Creates a new {@code NSphere}.
	 * 
	 * @param dim  the sphere's dimension
	 */
	public NSphere(int dim)
	{
		this(dim, 2f);
	}
}