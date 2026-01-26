package waffles.utils.geom.collide.collision.convex.spheres;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.convex.CLSConvex;
import waffles.utils.geom.collide.response.convex.spheres.CNTPoint;
import waffles.utils.geom.collide.response.convex.spheres.CNTSphere;
import waffles.utils.geom.collide.response.convex.spheres.IHBGeometry;
import waffles.utils.geom.collide.response.convex.spheres.ISCASpace;
import waffles.utils.geom.collide.response.convex.spheres.ISCGeometry;
import waffles.utils.geom.collide.response.convex.spheres.ISCLine;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.linear.affine.ASpace;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSSphere} defines {@code Collision} for a {@code HyperSphere}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSConvex
 */
public class CLSSphere extends CLSConvex
{	
	/**
	 * Creates a new {@code CLSSphere}.
	 *
	 * @param s  a source sphere
	 *
	 *
	 * @see HyperSphere
	 */
	public CLSSphere(HyperSphere s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSSphere}.
	 *
	 * @param s  a source sphere
	 * @param e  an error margin
	 * 
	 *
	 * @see HyperSphere
	 */
	public CLSSphere(HyperSphere s, double e)
	{
		super(s, e);
	}


	@Override
	public Response inhabit(Collidable c)
	{
		HyperSphere s = Source();
		
		// Eliminate geometry.
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new IHBGeometry(s, t);
		}
		
		return super.inhabit(c);
	}
		
	@Override
	public Response intersect(Collidable c)
	{	
		HyperSphere s = Source();

		// Eliminate affine spaces.
		if(c instanceof ASpace)
		{
			ASpace t = (ASpace) c;
			return new ISCASpace(s, t);
		}
		
		// Eliminate lines.
		if(c instanceof Line)
		{
			Line t = (Line) c;
			return new ISCLine(s, t);
		}
				
		// Eliminate geometry.
		if(c instanceof Geometry)
		{
			Geometry t = (Geometry) c;
			return new ISCGeometry(s, t);
		}

		return super.intersect(c);
	}
	
	@Override
	public Response contain(Collidable c)
	{
		HyperSphere s = Source();
		
		// Eliminate points.
		if(c instanceof Point)
		{
			Point x = (Point) c;
			return new CNTPoint(s, x);
		}
		
		// Eliminate spheres.
		if(c instanceof HyperSphere)
		{
			HyperSphere t = (HyperSphere) c;
			return new CNTSphere(s, t);
		}
		
		return super.contain(c);
	}
		
	@Override
	public HyperSphere Source()
	{
		return (HyperSphere) super.Source();
	}
}