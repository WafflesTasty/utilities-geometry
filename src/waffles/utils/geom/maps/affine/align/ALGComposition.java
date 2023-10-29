package waffles.utils.geom.maps.affine.align;

import waffles.utils.geom.maps.AlignedMap;
import waffles.utils.geom.maps.fixed.Composition;
import waffles.utils.geom.spatial.data.Axial;

/**
 * The {@code BasicComposite} class composits two {@code AlignedMaps}.
 *
 * @author Waffles
 * @since 18 Sep 2023
 * @version 1.0
 * 
 * 
 * @see AlignedMap
 * @see Composition
 */
public class ALGComposition extends Composition implements AlignedMap
{
	/**
	 * Creates a new {@code ALGComposition}.
	 * 
	 * @param m2  an aligned map
	 * @param m1  an aligned map
	 * 
	 * 
	 * @see AlignedMap
	 */
	public ALGComposition(AlignedMap m2, AlignedMap m1)
	{
		super(m2, m1);
	}


	@Override
	public Axial Source()
	{
		return null;
	}
}