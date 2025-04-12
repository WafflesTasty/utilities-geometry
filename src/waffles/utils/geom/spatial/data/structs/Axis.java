package waffles.utils.geom.spatial.data.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.fixed.linear.Dilation;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * An {@code Axis} defines an {@code Axial.Mutable} implementation.
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
	private Vector size;

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
		super(o);
		size = s;
	}
	
	/**
	 * Creates a new {@code Axis}.
	 * 
	 * @param dim  an axis dimension
	 */
	public Axis(int dim)
	{
		this
		(
			Translation.Default(dim),
			Dilation.Default(dim).times(2f)
		);
	}
	
	/**
	 * Creates a new {@code Axis}.
	 */
	public Axis()
	{
		// NOT APPLICABLE
	}
	

	@Override
	public void setScale(Vector s)
	{
		size = s;
	}

	@Override
	public Vector Scale()
	{
		return size;
	}
}