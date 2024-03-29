package waffles.utils.geom.spatial.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.maps.fixed.linear.Dilation;
import waffles.utils.geom.spatial.maps.fixed.linear.Translation;

/**
 * An {@code Axis} defines an {@code Axial.Mutable} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Axial
 */
public class Axis implements Axial.Mutable
{
	private Vector size;
	private Vector origin;

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
		origin = o;
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
	public void setOrigin(Vector o)
	{
		origin = o;
	}

	@Override
	public void setSize(Vector s)
	{
		size = s;
	}

	
	@Override
	public Vector Origin()
	{
		return origin;
	}

	@Override
	public Vector Size()
	{
		return size;
	}
}