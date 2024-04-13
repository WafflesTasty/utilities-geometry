package waffles.utils.geom.spaces;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.sets.keymaps.Pair;
import waffles.utils.sets.mutable.AtomicSet;

/**
 * A {@code Manifold} defines a bounded space containing unique objects.
 *
 * @author Waffles
 * @since 13 Apr 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see AtomicSet
 * @see Bounded
 * @see Space
 */
public interface Manifold<O> extends Bounded, AtomicSet<O>, Space<O>
{
	/**
	 * Iterates over all relevant pairs in the {@code Manifold}.
	 * Preferably, this method iterates over all unique
	 * pairs of potentially intersectin objects.
	 * 
	 * @return  a pair iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Pair
	 */
	public abstract Iterable<Pair<O, O>> Pairs();
}