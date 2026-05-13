package waffles.utils.geom.spaces.index;

import waffles.utils.geom.spaces.arboreal.NodalSpace;
import waffles.utils.sets.arboreal.Arboreal;

/**
 * An {@code IndexBoreal} defines an indexed {@code NodalSpace}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @param <T>  a tile type
 * @see IndexSpace
 * @see NodalSpace
 */
public interface IndexBoreal<N extends IndexNodal, T> extends NodalSpace<N>, IndexSpace<N, T>
{
	/**
	 * An {@code IndexBoreal.Mutable} defines an {@code IndexBoreal} that can change its own root.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a node type
	 * @param <T>  a tile type
	 * @see IndexBoreal
	 * @see IndexSpace
	 * @see Arboreal
	 */
	public static interface Mutable<N extends IndexNodal, T> extends Arboreal.Mutable, IndexSpace.Mutable<N, T>, IndexBoreal<N, T>
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public default int Dimension()
	{
		return IndexSpace.super.Dimension();
	}
		
	@Override
	public default int Count()
	{
		return NodalSpace.super.Count();
	}
}