package waffles.utils.geom.spatial.maps.data.structs;

import waffles.utils.geom.spatial.maps.data.Radial;
import waffles.utils.geom.spatial.maps.data.spin.Spin;
import waffles.utils.geom.spatial.maps.linear.Rotation;

/**
 * A {@code Radix} defines a basic {@code Radial} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.1
 *
 *
 * @see Position
 * @see Radial
 */
public class Radix extends Position implements Radial.Mutable
{
	private Spin spin;

	/**
	 * Creates a new {@code Radix}.
	 *
	 * @param dim  a locus dimension
	 */
	public Radix(int dim)
	{
		super(dim);
		spin = Rotation.Default(dim);
	}

	/**
	 * Creates a new {@code Radix}.
	 */
	public Radix()
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