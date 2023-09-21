package waffles.utils.geom.maps.fixed;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.GlobalMap;

/**
 * An {@code Inverse} defines an inverted map.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class Inverse implements GlobalMap
{
	private GlobalMap map;
	
	/**
	 * Creates a new {@code Composition}.
	 * 
	 * @param m  a global map
	 * 
	 * 
	 * @see GlobalMap
	 */
	public Inverse(GlobalMap m)
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