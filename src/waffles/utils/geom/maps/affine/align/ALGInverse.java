package waffles.utils.geom.maps.affine.align;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.AlignedMap;
import waffles.utils.geom.spatial.data.Axial;

/**
 * The {@code ALGInverse} class inverts an {@code AlignedMap}.
 *
 * @author Waffles
 * @since 18 Sep 2023
 * @version 1.0
 * 
 * 
 * @see AlignedMap
 */
public class ALGInverse implements AlignedMap
{
	private AlignedMap map;
	
	/**
	 * Creates a new {@code ALGInverse}.
	 * 
	 * @param map  a source map
	 * 
	 * 
	 * @see AlignedMap
	 */
	public ALGInverse(AlignedMap map)
	{
		this.map = map;
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

	@Override
	public Axial Source()
	{
		return map.Source();
	}
}