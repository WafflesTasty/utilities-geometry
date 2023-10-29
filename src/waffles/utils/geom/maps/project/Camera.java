package waffles.utils.geom.maps.project;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.AffineMap;
import waffles.utils.geom.spatial.data.Watcher;
import waffles.utils.geom.spatial.data.structs.Eye;
import waffles.utils.geom.spatial.data.unary.Projected;

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
public class Camera implements AffineMap, Watcher.Mutable
{
	private CamToWorld ctw;
	private WorldToCam wtc;
	private Watcher src;

	/**
	 * Creates a new {@code Camera}.
	 * 
	 * @param s  a source watcher
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
	
	/**
	 * Creates a new {@code Camera}.
	 */
	public Camera()
	{
		this(new Eye());
	}

	
	@Override
	public void setOculus(Vector o)
	{
		Projected.Mutable src = Source().Mutator();
		if(src != null)
		{
			src.setOculus(o);
			setChanged();	
		}
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