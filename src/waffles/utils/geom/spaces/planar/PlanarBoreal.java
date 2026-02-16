package waffles.utils.geom.spaces.planar;

import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.binary.BiArboreal;

/**
 * A {@code PlanarBoreal} defines a planar splitting {@code Arboreal} structure.
 * It provides a framework for any {@code PlanarNodal} tree.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @param <N>  a nodal type
 * @see PlanarNodal
 * @see BiArboreal
 * @see Bounded
 * @see Space
 */
public interface PlanarBoreal<N extends PlanarNodal, O> extends BiArboreal.Mutable, Bounded, Space<O>
{
	@Override
	public abstract N Root();
	
				
	@Override
	public default Bounds Bounds()
	{
		return Root().Bounds();
	}
	
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
}