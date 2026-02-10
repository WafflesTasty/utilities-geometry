package waffles.utils.geom.shapes.response.fixed;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.collision.Collision.Response;
import waffles.utils.geom.shapes.fixed.Universe;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;

/**
 * An {@code RSPUniverse} computes a collision {@code Response} for {@code Universe} geometry.
 *
 * @author Waffles
 * @since 13 Sep 2023
 * @version 1.0
 *
 *
 * @see Response
 */
public class RSPUniverse implements Response
{
	private Universe src;
	private Geometry tgt;

	/**
	 * Creates a new {@code RSPUniverse}.
	 *
	 * @param s  a universe source
	 * @param t  a collidable target
	 *
	 *
	 * @see Geometry
	 * @see Universe
	 */
	public RSPUniverse(Universe s, Geometry t)
	{
		src = s;
	}


	@Override
	public Universe Source()
	{
		return src;
	}

	@Override
	public Collidable Shape()
	{
		if(hasImpact())
		{
			return src;
		}

		int dim = Dimension();
		return new Void(dim);
	}

	@Override
	public boolean hasImpact()
	{
		return true;
	}

	@Override
	public Point Contact()
	{
		return tgt.Origin();
	}

	@Override
	public int cost()
	{
		return 0;
	}
}