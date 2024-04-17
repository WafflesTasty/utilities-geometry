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
 * An {@code ISCGeneral} computes the intersection from an alignable to any object.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.1
 * 
 * 
 * @see Response
 */
public class ISCGeneral implements Response
{
	private int dim;
	private Response rsp;
	
	/**
	 * Creates a new {@code ISCGeneral}.
	 * 
	 * @param s  a source alignable
	 * @param c  a target collidable
	 * 
	 * 
	 * @see Collidable
	 * @see AxisAligned
	 */
	public ISCGeneral(AxisAligned s, Collidable c)
	{
		AxialMap map = s.Transform();
		AxialShape set = s.Shape().map(map);
		rsp = set.intersect(c);
		dim = s.Dimension();
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