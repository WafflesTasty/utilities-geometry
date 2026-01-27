package waffles.utils.geom.spatial.maps;

import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.utilities.linear.LazyIdentity;

/**
 * An {@code IdentityMap} defines a one-to-one identity map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class IdentityMap implements GlobalMap
{
	private LazyIdentity map;
	
	/**
	 * Creates a new {@code IdentityMap}.
	 */
	public IdentityMap()
	{
		map = new LazyIdentity();
	}
	
		
	@Override
	public Affine unmap(Affine a)
	{
		return a;
	}
	
	@Override
	public Affine map(Affine a)
	{
		return a;
	}
	
		
	@Override
	public LazyMatrix UTW()
	{
		return map;
	}
	
	@Override
	public LazyMatrix WTU()
	{
		return map;
	}
}