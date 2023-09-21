package waffles.utils.geom.collision.general.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code RSPVoid} computes the collision response with void geometry.
 *
 * @author Waffles
 * @since 13 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class RSPVoid implements Response
{
	private int dim;
	
	/**
	 * Creates a new {@code RSPVoid}.
	 * 
	 * @param dim  a void dimension
	 */
	public RSPVoid(int dim)
	{
		this.dim = dim;
	}
	
	@Override
	public Collidable Shape()
	{
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{
		return false;
	}

	@Override
	public Vector Penetration()
	{
		return null;
	}
	
	@Override
	public Vector Distance()
	{
		return Vectors.create(dim);
	}
	
	@Override
	public int Cost()
	{
		return 0;
	}
}