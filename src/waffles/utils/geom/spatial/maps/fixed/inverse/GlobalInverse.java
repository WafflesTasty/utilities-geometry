package waffles.utils.geom.spatial.maps.fixed.inverse;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code GlobalInverse} defines an inverse global map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class GlobalInverse implements GlobalMap
{
	private GlobalMap map;
	
	/**
	 * Creates a new {@code GlobalInverse}.
	 * 
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public GlobalInverse(GlobalMap m)
	{
		map = m;
	}
	
	
	@Override
	public LazyMatrix UTW()
	{
		return map.WTU();
	}

	@Override
	public LazyMatrix WTU()
	{
		return map.UTW();
	}
}