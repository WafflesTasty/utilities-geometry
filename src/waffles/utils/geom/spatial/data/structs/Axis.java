package waffles.utils.geom.spatial.data.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.maps.linear.Dilation;
import waffles.utils.geom.maps.linear.Translation;
import waffles.utils.geom.spatial.data.Axial;

/**
 * An {@code Axis} defines a basic {@code Axial.Mutable} implementation.
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
	 * @param dim  an axis dimension
	 */
	public Axis(int dim)
	{
		size = Dilation.Default(dim).times(2f);
		origin = Translation.Default(dim);
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