package waffles.utils.geom.spatial.data.structs;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.maps.linear.Projection;

/**
 * An {@code Eye} defines a basic {@code Watcher} implementation.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Watcher
 * @see Locus
 */
public class Eye extends Locus implements Watcher.Mutable
{
	private Vector ocl;
	
	/**
	 * Creates a new {@code Eye}.
	 * 
	 * @param iDim  a source dimension
	 * @param oDim  a target dimension
	 */
	public Eye(int iDim, int oDim)
	{
		super(iDim); ocl = Projection.Default(iDim, oDim);
	}
		
	
	@Override
	public void setOculus(Vector o)
	{
		ocl = o;
	}

	@Override
	public Vector Oculus()
	{
		return ocl;
	}
}