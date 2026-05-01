package waffles.utils.geom.spaces;

import java.util.Iterator;

import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.keymaps.PairQuery;
import waffles.utils.sets.utilities.keymaps.PairQueryable;

/**
 * A {@code Manifold} defines a {@code Space} that can iterate object pairs.
 *
 * @author Waffles
 * @since 13 Apr 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PairQueryable
 * @see Space
 */
public interface Manifold<O> extends Space<O>, PairQueryable<O>
{
	/**
	 * A {@code Manifold.Query} defines queries for a {@code Manifold}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Space
	 */
	public static interface Query<O> extends Space.Query<O>, PairQuery<O>
	{
		/**
		 * Iterates over all relevant pairs in the {@code Manifold}.
		 * Preferably, this method iterates over all unique
		 * pairs of potentially intersecting objects.
		 *
		 * @return  a pair iterable
		 *
		 *
		 * @see Iterator
		 * @see Pair
		 */
		@Override
		public abstract Iterator<Pair<O, O>> Pairs();
	}


	@Override
	public default Iterable<Pair<O, O>> Pairs()
	{
		return () -> Query().Pairs();
	}
	
	@Override
	public abstract Query<O> Query();
}