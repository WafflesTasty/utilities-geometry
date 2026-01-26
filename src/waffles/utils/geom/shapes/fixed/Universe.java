package waffles.utils.geom.shapes.fixed;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.fixed.CLSUniverse;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse2D;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code Universe} defines a {@code Geometry} that contains everything.
 *
 * @author Waffles
 * @since 02 Jan 2022
 * @version 1.0
 *
 *
 * @see Geometry
 */
public class Universe implements Geometry
{
	private int dim;

	/**
	 * Creates a new {@code Universe}.
	 *
	 * @param dim  a space dimension
	 */
	public Universe(int dim)
	{
		this.dim = dim;
	}


	@Override
	public Bounds Bounds()
	{
		if(Dimension() == 2)
			return new BNDUniverse2D(this);
		if(Dimension() == 3)
			return new BNDUniverse3D(this);

		return new BNDUniverse(this);
	}

	@Override
	public Bounds Bounds(LinearMap map)
	{
		return Bounds();
	}


	@Override
	public CLSUniverse Collision()
	{
		return new CLSUniverse(this);
	}
	
	@Override
	public int Dimension()
	{
		return dim;
	}
	
	@Override
	public Point Origin()
	{
		return new Point(dim);
	}
}