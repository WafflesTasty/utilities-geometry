package waffles.utils.geom.response.lines.halved;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision.Response;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.collidable.spaces.ASpace;
import waffles.utils.geom.collidable.spaces.VSpace;
import waffles.utils.geom.collidable.spaces.lines.HLine;
import waffles.utils.geom.collidable.spaces.lines.Line;
import waffles.utils.geom.utilities.Geometries;

/**
 * An {@code IHBASpace} computes the inhabitation response between a half-line and an affine space.
 *
 * @author Waffles
 * @since 12 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Response
 */
public class IHBASpace implements Response
{
	private HLine src;
	private Response rsp;
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see ASpace
	 * @see HLine
	 */
	public IHBASpace(HLine s, ASpace t)
	{
		Line l = s.Line();
		rsp = t.contain(l);
		src = s;
	}
	
	/**
	 * Creates a new {@code ISCASpace}.
	 * 
	 * @param s  a source space
	 * @param t  a target space
	 * 
	 * 
	 * @see VSpace
	 * @see HLine
	 */
	public IHBASpace(HLine s, VSpace t)
	{
		this(s, ASpace.Default(t));
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
		return rsp.hasImpact();
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
	public Point Contact()
	{
		return null;
	}
	
	@Override
	public int Cost()
	{
		return rsp.Cost();
	}
}