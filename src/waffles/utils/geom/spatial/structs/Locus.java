package waffles.utils.geom.spatial.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.maps.linear.Dilation;
import waffles.utils.geom.maps.linear.Rotation;
import waffles.utils.geom.maps.linear.Translation;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.spin.Spin;

/**
 * A {@code Locus} defines a basic {@code Spatial} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Spatial
 */
public class Locus implements Spatial
{
	private Spin spin;
	private Vector size;
	private Vector origin;

	/**
	 * Creates a new {@code Locus}.
	 * 
	 * @param dim  a locus dimension
	 */
	public Locus(int dim)
	{
		size = Dilation.Default(dim).times(2f);
		origin = Translation.Default(dim);
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
	public void setSpin(Spin s)
	{
		spin = s;
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

	@Override
	public Spin Spin()
	{
		return spin;
	}
}