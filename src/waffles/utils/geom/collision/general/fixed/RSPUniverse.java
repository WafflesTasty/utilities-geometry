package waffles.utils.geom.collision.general.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Universe;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code RSPUniverse} computes the containment response with universal geometry.
 *
 * @author Waffles
 * @since 13 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class RSPUniverse implements Response
{
	private Universe src;
	private Collidable tgt;
	
	/**
	 * Creates a new {@code RSPUniverse}.
	 * 
	 * @param s  a source universe
	 * @param t  a target collidable
	 * 
	 * 
	 * @see Collidable
	 * @see Universe
	 */
	public RSPUniverse(Universe s, Collidable t)
	{
		src = s;
		tgt = t;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
		
	@Override
	public boolean hasImpact()
	{
		if(tgt instanceof Universe)
		{
			int sDim = src.Dimension();
			int tDim = tgt.Dimension();
			
			return sDim <= tDim;
		}
		
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
		return null;
	}
	
	@Override
	public int Cost()
	{
		return 0;
	}
}