package waffles.utils.geom.spatial.maps.project;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.data.structs.Eye;
import waffles.utils.geom.spatial.maps.project.matrix.CamToWorld;
import waffles.utils.geom.spatial.maps.project.matrix.WorldToCam;

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
 * @see WatcherMap
 */
public class Camera implements WatcherMap
{
	private Watcher src;
	private CamToWorld ctw;
	private WorldToCam wtc;

	/**
	 * Creates a new {@code Camera}.
	 * 
	 * @param s  a watcher source
	 * 
	 * 
	 * @see Watcher
	 */
	public Camera(Watcher s)
	{
		src = s;
		
		ctw = new CamToWorld(src);
		wtc = new WorldToCam(src);
	}
	
	/**
	 * Creates a new {@code Camera}.
	 * 
	 * @param iDim  a source dimension
	 * @param oDim  a target dimension
	 */
	public Camera(int iDim, int oDim)
	{	
		this(new Eye(iDim, oDim));
	}

	
	@Override
	public Watcher Source()
	{
		return src;
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