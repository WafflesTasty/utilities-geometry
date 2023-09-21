package waffles.utils.geom.maps.project;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.AffineMap;
import waffles.utils.geom.spatial.Watcher;
import waffles.utils.geom.spatial.structs.Eye;

/**
 * The {@code Camera} class defines a generalized pinhole camera.
 * It projects an m-dimensional vector space onto an oriented n-dimensional
 * subspace, then scales it down to normalized coordinates. These
 * are assumed to fall inside the unit square of size 2.
 * 
 * @author Waffles
 * @since Dec 29, 2018
 * @version 1.0 
 * 
 * 
 * @see AffineMap
 * @see Watcher
 */
public class Camera implements AffineMap, Watcher
{
	private CamToWorld ctw;
	private WorldToCam wtc;
	private Watcher src;

	/**
	 * Creates a new {@code Camera}.
	 * 
	 * @param iDim  a source dimension
	 * @param oDim  a target dimension
	 */
	public Camera(int iDim, int oDim)
	{	
		src = new Eye(iDim, oDim);
		ctw = new CamToWorld(src);
		wtc = new WorldToCam(src);
	}

	
	@Override
	public void setOculus(Vector o)
	{
		src.setOculus(o);
		setChanged();
	}

	@Override
	public Watcher Source()
	{
		return src;
	}

	@Override
	public Vector Oculus()
	{
		return src.Oculus();
	}
	
	
	@Override
	public LazyMatrix UTW()
	{
		return wtc;
	}

	@Override
	public LazyMatrix WTU()
	{
		return ctw;
	}
}