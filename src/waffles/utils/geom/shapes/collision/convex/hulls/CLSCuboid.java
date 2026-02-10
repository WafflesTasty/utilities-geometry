package waffles.utils.geom.shapes.collision.convex.hulls;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.shapes.response.convex.hulls.cuboid.CNTCuboid;
import waffles.utils.geom.shapes.response.convex.hulls.cuboid.CNTPoint;
import waffles.utils.geom.shapes.response.convex.hulls.cuboid.ISCCuboid;
import waffles.utils.geom.shapes.response.convex.hulls.cuboid.ISCLine;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code CLSCuboid} defines {@code Collision} for a {@code HyperCuboid}.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 *
 *
 * @see CLSHull
 */
public class CLSCuboid extends CLSHull
{
	/**
	 * Creates a new {@code CLSCuboid}.
	 *
	 * @param s  a source cuboid
	 *
	 *
	 * @see HyperCuboid
	 */
	public CLSCuboid(HyperCuboid s)
	{
		this(s, Doubles.pow(2, -8));
	}

	/**
	 * Creates a new {@code CLSCuboid}.
	 *
	 * @param s  a source cuboid
	 * @param e  an error margin
	 * 
	 *
	 * @see HyperCuboid
	 */
	public CLSCuboid(HyperCuboid s, double e)
	{
		super(s, e);
	}

	
	@Override
	public Response contain(Collidable c)
	{
		HyperCuboid s = Source();

		// Eliminate points.
		if(c instanceof Point)
		{
			Point p = (Point) c;
			return new CNTPoint(s, p);
		}

		// Eliminate cuboids.
		if(c instanceof HyperCuboid)
		{
			HyperCuboid t = (HyperCuboid) c;
			return new CNTCuboid(s, t);
		}

		// Eliminate bounded sets.
		if(c instanceof Bounded)
		{
			Bounded t = (Bounded) c;
			return new CNTCuboid(s, t);
		}

		return super.contain(c);
	}
	
	@Override
	public Response intersect(Collidable c)
	{
		HyperCuboid s = Source();

		// Eliminate lines.
		if(c instanceof Line)
		{
			Line t = (Line) c;
			return new ISCLine(s, t);
		}

		// Eliminate cuboids.
		if(c instanceof HyperCuboid)
		{
			HyperCuboid t = (HyperCuboid) c;
			return new ISCCuboid(s, t);
		}

		return super.intersect(c);
	}

	@Override
	public HyperCuboid Source()
	{
		return (HyperCuboid) super.Source();
	}
}