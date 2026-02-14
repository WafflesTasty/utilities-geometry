package waffles.utils.geom.spaces.index;

import waffles.utils.sets.arboreal.Arboreal;

/**
 * An {@code IndexBoreal} defines an {@code IndexSpace} as an {@code IndexNodal} tree.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @param <T>  a tile type
 * @see IndexSpace
 * @see Arboreal
 */
public interface IndexBoreal<N extends IndexNodal, T> extends Arboreal, IndexSpace<N, T>
{
	/**
	 * An {@code IndexBoreal.Mutable} defines a {@code Mutable IndexBoreal}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a node type
	 * @param <T>  a tile type
	 */
	public static interface Mutable<N extends IndexNodal, T> extends Arboreal.Mutable, IndexSpace.Mutable<N, T>, IndexBoreal<N, T>
	{
		// NOT APPLICABLE
	}
	
	
	@Override
	public default int Count()
	{
		return Arboreal.super.Count();
	}
}