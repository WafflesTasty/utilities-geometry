package waffles.utils.geom.response.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;

/**
 * An {@code RSPTrivial} computes a trivial intersection with the universe.
 *
 * @author Waffles
 * @since 13 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class RSPTrivial implements Response
{
	private Collidable tgt;
	
	/**
	 * Creates a new {@code RSPTrivial}.
	 * 
	 * @param t  a target collidable
	 */
	public RSPTrivial(Collidable t)
	{
		tgt = t;
	}
	
	
	@Override
	public Collidable Shape()
	{
		return tgt;
	}
	
	@Override
	public boolean hasImpact()
	{
		return true;
	}

	@Override
	public Vector Penetration()
	{
		return Vectors.create(tgt.Dimension());
	}
	
	@Override
	public Vector Distance()
	{
		return null;
	}
	
	@Override
	public int Cost()
	{
		return 0;
	}
}