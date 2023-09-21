package waffles.utils.geom.collision.spaces.affine;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * A {@code CNTASpace} computes the collision response for affine space containment.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTASpace implements Response
{
	private ASpace src, tgt;
	private Response rsp1, rsp2;
	
	/**
	 * Creates a new {@code CNTASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 */
	public CNTASpace(ASpace s, ASpace t)
	{
		src = s;
		tgt = t;
	}
	
	/**
	 * Creates a new {@code CNTASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 * @see VSpace
	 */
	public CNTASpace(ASpace s, VSpace t)
	{
		this(s, ASpace.Default(t));
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
		int dim = src.Dimension();
		return Geometries.Void(dim);
	}
	
	@Override
	public boolean hasImpact()
	{			
		if(rsp1 == null)
		{
			computeResponse();
		}
		
		return rsp1.hasImpact()
			&& rsp2.hasImpact();
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
		return rsp1.Cost()
			 + rsp2.Cost();
	}
	
	
	void computeResponse()
	{
		VSpace s1 = src.Direction();
		VSpace s2 = tgt.Direction();
		
		Point p1 = src.Origin();
		Point p2 = tgt.Origin();
		
		
		Vector px = p2.minus(p1);
		VSpace sx = s1.add(s2);
		
		rsp1 = sx.contain(px);
		rsp2 = s1.contain(s2);
	}
}