package waffles.utils.geom.shapes.bounds.fixed;

import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code BNDVoid} defines dynamic {@code Bounds} for {@code Void} geometry.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 *
 * @see Bounds
 */
public class BNDVoid implements Bounds
{
	private Void src;
	
	/**
	 * Creates a new {@code BNDVoid}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public BNDVoid(Void s)
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
		return 0f;
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
		return new Arrow(n);
	}
}