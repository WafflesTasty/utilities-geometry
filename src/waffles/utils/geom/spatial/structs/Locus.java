package waffles.utils.geom.spatial.structs;

import waffles.utils.geom.spatial.data.Spatial;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.maps.fixed.linear.Rotation;

/**
 * A {@code Locus} defines a basic {@code Spatial} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Spatial
 * @see Axis
 */
public class Locus extends Axis implements Spatial.Mutable
{
	private Spin spin;

	/**
	 * Creates a new {@code Locus}.
	 * 
	 * @param dim  a locus dimension
	 */
	public Locus(int dim)
	{
		super(dim);
		spin = Rotation.Default(dim);
	}
	
	/**
	 * Creates a new {@code Locus}.
	 */
	public Locus()
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public void setSpin(Spin s)
	{
		spin = s;
	}

	@Override
	public Spin Spin()
	{
		return spin;
	}
}