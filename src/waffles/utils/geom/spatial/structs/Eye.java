package waffles.utils.geom.spatial.structs;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.maps.linear.Projection;
import waffles.utils.geom.spatial.Watcher;

/**
 * An {@code Eye} defines a basic {@code Watcher} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 */
public class Eye extends Locus implements Watcher
{
	private Vector oculus;
	
	/**
	 * Creates a new {@code Eye}.
	 * 
	 * @param iDim  a source dimension
	 * @param oDim  a target dimension
	 */
	public Eye(int iDim, int oDim)
	{
		super(iDim);
		oculus = Projection.Default(iDim, oDim);
	}
	
	/**
	 * Creates a new {@code Eye}.
	 */
	public Eye()
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public void setOculus(Vector o)
	{
		oculus = o;
	}

	@Override
	public Vector Oculus()
	{
		return oculus;
	}
}