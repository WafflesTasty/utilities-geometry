package waffles.utils.geom.shapes.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.convex.CLSConvex;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.response.convex.hulls.IHBGeometry;
import waffles.utils.geom.shapes.response.convex.hulls.ISCASpace;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSHull} defines {@code Collision} for a {@code Hull}.
 *
 * @author Waffles
 * @since 13 Jan 2026
 * @version 1.1
 *
 * 
 * @see CLSConvex
 */
public class CLSHull extends CLSConvex
{
	/**
	 * Creates a new {@code CLSHull}.
	 *
	 * @param s  a source hull
	 *
	 *
	 * @see Hull
	 */
	public CLSHull(Hull s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSHull}.
	 *
	 * @param s  a source hull
	 * @param e  an error margin
	 * 
	 *
	 * @see Hull
	 */
	public CLSHull(Hull s, double e)
	{
		super(s, e);
	}

	
	@Override
	public Response intersect(Collidable c)
	{
		Hull s = Source();
		
		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}

		return super.intersect(c);
	}
	
	@Override
	public Response inhabit(Collidable c)
	{
		Hull s = Source();
		
		// Eliminate geometry.
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new IHBGeometry(s, t);
		}
		
		return super.inhabit(c);
	}

	@Override
	public Hull Source()
	{
		return (Hull) super.Source();
	}
}
