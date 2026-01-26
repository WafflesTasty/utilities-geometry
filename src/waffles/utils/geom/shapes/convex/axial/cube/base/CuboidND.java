package waffles.utils.geom.shapes.convex.axial.cube.base;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.geom.spatial.maps.linear.Dilation;

/**
 * A {@code CuboidND} implements n-dimensional {@code HyperCuboid} geometry.
 *
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 *
 *
 * @see HyperCuboid
 */
public class CuboidND implements HyperCuboid
{
	private Axis axis;
	
	/**
	 * Creates a new {@code CuboidND}.
	 *
	 * @param s  a cuboid scale
	 *
	 *
	 * @see Arrow
	 */
	public CuboidND(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}
	
	/**
	 * Creates a new {@code CuboidND}.
	 * 
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public CuboidND(Point o, Arrow s)
	{
		axis = new Axis(o, s);
	}

	/**
	 * Creates a new {@code CuboidND}.
	 *
	 * @param dim  a space dimension
	 */
	public CuboidND(int dim)
	{
		this(Dilation.Default(dim));
	}

	
	@Override
	public Point Origin()
	{
		return axis.Origin();
	}
	
	@Override
	public Arrow Scale()
	{
		return axis.Scale();
	}
}