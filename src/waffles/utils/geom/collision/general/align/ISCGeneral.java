package waffles.utils.geom.collision.general.align;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.Alignable;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCGeneral} computes the intersection from an alignable to any object.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class ISCGeneral implements Response
{
	private Response rsp;
	private Alignable src;
	
	/**
	 * Creates a new {@code ISCGeneral}.
	 * 
	 * @param s  a source alignable
	 * @param c  a target collidable
	 * 
	 * 
	 * @see Collidable
	 * @see Alignable
	 */
	public ISCGeneral(Alignable s, Collidable c)
	{
		AxialSet set = s.Shape().map(s.Transform());
		rsp = set.intersect(c);
		src = s;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		return rsp.hasImpact();
	}

	@Override
	public Vector Penetration()
	{
		return rsp.Penetration();
	}
	
	@Override
	public Vector Distance()
	{
		return rsp.Distance();
	}
	
	@Override
	public int Cost()
	{
		int dim = src.Dimension();
		return rsp.Cost() + 2 * dim;
	}
}