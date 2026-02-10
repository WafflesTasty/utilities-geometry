package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Universe;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDUniverse} defines dynamic {@code Bounds} for {@code Universe} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 *
 * @see Bounds
 */
public class BNDUniverse implements Bounds
{
	private Universe src;

	/**
	 * Creates a new {@code BNDUniverse}.
	 *
	 * @param s  a source universe
	 *
	 *
	 * @see Universe
	 */
	public BNDUniverse(Universe s)
	{
		src = s;
	}


	@Override
	public Factory Factory()
	{
		return m -> this;
	}
	
	@Override
	public float Diameter()
	{
		return Floats.MAX_VALUE;
	}
	
	@Override
	public int Dimension()
	{
		return src.Dimension();
	}
	
	@Override
	public Point Origin()
	{
		int n = Dimension();
		return new Point(n);
	}
	
	@Override
	public Arrow Scale()
	{
		int n = Dimension();
		float v = Floats.MAX_VALUE;
		return Arrow.create(v / 2, n);
	}
}