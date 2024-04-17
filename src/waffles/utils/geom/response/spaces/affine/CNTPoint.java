package waffles.utils.geom.response.spaces.affine;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code CNTPoint} computes the collision response between an affine space and a point.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class CNTPoint implements Response
{
	private int dim;
	private Point tgt;
	private Response rsp;
	
	/**
	 * Creates a new {@code CNTPoint}.
	 * 
	 * @param s  a source space
	 * @param p  a target point
	 * 
	 * 
	 * @see ASpace
	 * @see Point
	 */
	public CNTPoint(ASpace s, Point p)
	{
		VSpace dir = s.Direction();
		Vector q = p.minus(s.Origin());
		
		rsp = dir.contain(q);
		dim = s.Dimension();
		tgt = p;
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return tgt;
		}
		
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
		return rsp.Cost();
	}
}