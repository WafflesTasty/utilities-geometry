package waffles.utils.geom.spaces;

import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * A {@code Manifold} defines a {@code Space} that can iterate object pairs.
 *
 * @author Waffles
 * @since 13 Apr 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Space
 */
public interface Manifold<O> extends Space<O>
{
	/**
	 * Iterates over all relevant pairs in the {@code Manifold}.
	 * Preferably, this method iterates over all unique
	 * pairs of potentially intersecting objects.
	 *
	 * @return  a pair iterable
	 *
	 *
	 * @see Iterable
	 * @see Pair
	 */
	public abstract Iterable<Pair<O, O>> Pairs();
}