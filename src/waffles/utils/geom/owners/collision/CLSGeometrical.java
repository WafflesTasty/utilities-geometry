package waffles.utils.geom.owners.collision;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.owners.collision.response.CNTTransformator;
import waffles.utils.geom.owners.collision.response.IHBTransformator;
import waffles.utils.geom.owners.collision.response.ISCConvex;
import waffles.utils.geom.owners.collision.response.ISCGeometrical;
import waffles.utils.geom.owners.collision.response.ISCTransformator;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.linear.LSpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code CLSGeometrical} defines {@code Collision} for a {@code Geometrical}.
 *
 * @author Waffles
 * @since Jul 25, 2019
 * @version 1.0
 * 
 * 
 * @see Collision
 */
public class CLSGeometrical extends Collision
{
	/**
	 * Creates a new {@code CLSGeometrical}.
	 * 
	 * @param s  a source geometrical
	 * 
	 * 
	 * @see Geometrical
	 */
	public CLSGeometrical(Geometrical s)
	{
		super(s);
	}
	
	
	@Override
	public Response inhabit(Collidable c)
	{
		Geometrical s = Source();

		// Eliminate linear spaces.
		if(c instanceof LSpace)
		{
			LSpace t = (LSpace) c;
			return new IHBTransformator(s, t);
		}

		return () -> s;
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		Geometrical s = Source();
		
		// Eliminate geometricals.
		if(c instanceof Geometrical)
		{
			Geometrical t = (Geometrical) c;
			return new ISCGeometrical(s, t);
		}
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTTransformator(s, t);
		}
		
		// Eliminate linear spaces.
		if(c instanceof LSpace)
		{
			LSpace t = (LSpace) c;
			return new ISCTransformator(s, t);
		}
		
		// Eliminate convex sets.
		if(c instanceof ConvexSet)
		{
			ConvexSet t = (ConvexSet) c;
			return new ISCConvex(s, t);
		}
		
		return () -> s;
	}
	
	@Override
	public Response contain(Collidable c)
	{
		Geometrical s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point t = (Point) c;
			return new CNTTransformator(s, t);
		}

		return () -> s;
	}
	
	@Override
	public Geometrical Source()
	{
		return (Geometrical) super.Source();
	}
}