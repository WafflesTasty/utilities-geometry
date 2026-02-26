package waffles.utils.geom.spatial.maps.data.structs;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.Axial;
import waffles.utils.geom.spatial.maps.linear.Dilation;

/**
 * An {@code Axis} defines a basic {@code Axial} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.1
 *
 *
 * @see Position
 * @see Axial
 */
public class Axis extends Position implements Axial.Mutable
{
	private Arrow size;

	/**
	 * Creates a new {@code Axis}.
	 *
	 * @param o  an origin vector
	 * @param s  a size vector
	 *
	 *
	 * @see Vector
	 */
	public Axis(Vector o, Vector s)
	{
		this(new Point(o, 1f), new Arrow(s));
	}
	
	/**
	 * Creates a new {@code Axis}.
	 *
	 * @param o  an origin point
	 * @param s  a size arrow
	 *
	 *
	 * @see Arrow
	 */
	public Axis(Point o, Arrow s)
	{
		super(o);
		size = s.absolute();
	}

	/**
	 * Creates a new {@code Axis}.
	 *
	 * @param dim  an axis dimension
	 */
	public Axis(int dim)
	{
		super(dim);
		size = Dilation.Default(dim);
	}

	/**
	 * Creates a new {@code Axis}.
	 */
	public Axis()
	{
		// NOT APPLICABLE
	}


	@Override
	public void setScale(Point s)
	{
		size = s.arrow();
	}

	@Override
	public Arrow Scale()
	{
		return size;
	}
}