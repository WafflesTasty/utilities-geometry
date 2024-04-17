package waffles.utils.geom.response.align;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.axial.AxialShape;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.geometric.AxisAligned;
import waffles.utils.geom.spatial.maps.axial.AxialMap;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code ISCAlign} computes the intersection between alignable objects.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCAlign implements Response
{
	private int dim;
	private Response rsp;
	
	/**
	 * Creates a new {@code ISCAlign}.
	 * 
	 * @param src  a source alignable
	 * @param tgt  a target alignable
	 * 
	 * 
	 * @see AxisAligned
	 */
	public ISCAlign(AxisAligned src, AxisAligned tgt)
	{
		AxialMap m1 = tgt.Transform();
		AxialMap m2 = src.Transform();
	
		AxialShape s1 = tgt.Shape().map(m1);
		AxialShape s2 = src.Shape().map(m2);
		
		rsp = s2.intersect(s1);
		dim = src.Dimension();
	}

	
	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return null;
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
	public Point Contact()
	{
		return rsp.Contact();
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost() + 2 * dim;
	}
}