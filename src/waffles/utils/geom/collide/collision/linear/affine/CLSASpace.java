package waffles.utils.geom.collide.collision.linear.affine;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.response.linear.affine.CNTASpace;
import waffles.utils.geom.collide.response.linear.affine.CNTGeometry;
import waffles.utils.geom.collide.response.linear.affine.CNTPoint;
import waffles.utils.geom.collide.response.linear.affine.ISCASpace;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSASpace} defines {@code Collision} for an {@code ASpace}.
 * 
 * @author Waffles
 * @since 26 Nov 2025
 * @version 1.1
 * 
 * 
 * @see Collision
 */
public class CLSASpace extends Collision
{
	/**
	 * Creates a new {@code CLSASpace}.
	 *
	 * @param s  a source space
	 *
	 *
	 * @see ASpace
	 */
	public CLSASpace(ASpace s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSASpace}.
	 *
	 * @param s  a source space
	 * @param e  an error margin
	 * 
	 *
	 * @see ASpace
	 */
	public CLSASpace(ASpace s, double e)
	{
		super(s, e);
	}
	
	
	@Override
	public Response intersect(Collidable c)
	{
		ASpace s = Source();

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}

		return () -> s;
	}
	
	@Override
	public Response contain(Collidable c)
	{
		ASpace s = Source();

		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTPoint(s, t);
		}

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new CNTASpace(s, t);
		}
		
		// Eliminate geometry.
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new CNTGeometry(s, t);
		}

		return () -> s;
	}
	
	@Override
	public ASpace Source()
	{
		return (ASpace) super.Source();
	}	
}