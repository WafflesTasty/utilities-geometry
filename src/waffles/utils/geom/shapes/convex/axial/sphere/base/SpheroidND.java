package waffles.utils.geom.shapes.convex.axial.sphere.base;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.geom.spatial.maps.linear.Dilation;

/**
 * A {@code SpheroidND} implements n-dimensional {@code HyperSpheroid} geometry.
 * 
 * @author Waffles
 * @since Apr 29, 2016
 * @version 1.0
 * 
 * 
 * @see HyperSpheroid
 */
public class SpheroidND implements HyperSpheroid
{	
	private Axis axis;
	
	/**
	 * Creates a new {@code SpheroidND}.
	 *
	 * @param s  a spheroid scale
	 *
	 *
	 * @see Arrow
	 */
	public SpheroidND(Arrow s)
	{
		this(new Point(s.Dimension()), s);
	}
	
	/**
	 * Creates a new {@code SpheroidND}.
	 * 
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public SpheroidND(Point o, Arrow s)
	{
		axis = new Axis(o, s);
	}

	/**
	 * Creates a new {@code SpheroidND}.
	 *
	 * @param d  a spheroid dimension
	 */
	public SpheroidND(int d)
	{
		this(Dilation.Default(d));
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