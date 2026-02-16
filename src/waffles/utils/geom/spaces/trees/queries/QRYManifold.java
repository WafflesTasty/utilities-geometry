package waffles.utils.geom.spaces.trees.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.trees.array.ArrayNodal;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYManifold} queries manifold pairs in a {@code SpatialTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @param <N>  a nodal type
 * @param <P>  a pair type
 * @see ArrayNodal
 * @see Iterator
 * @see Pair
 */
public class QRYManifold<O, N extends ArrayNodal<O>, P extends Pair<?, ?>> implements Iterator<P>
{
	private int idx;
	private ArrayNodal<O> curr;
	private Iterator<Pair<O, O>> pairs;
	private Iterator<N> nodes;

	/**
	 * Creates a new {@code QRYManifold}.
	 *
	 * @param n  a node iterable
	 *
	 * 
	 * @see Iterable
	 */
	public QRYManifold(Iterable<N> n)
	{
		nodes = n.iterator();
		pairs = new EmptyIterator<>();
		curr = nodes.next();
		idx = findNext();
	}


	private int findNext()
	{
		if(pairs.hasNext())
		{
			return idx;
		}
		
		if(idx < curr.Data().Count())
		{
			pairs = new QRYPairs<>(curr, idx++);
			return findNext();
		}
		
		idx = 0;
		if(nodes.hasNext())
		{
			curr = nodes.next();
			return findNext();
		}

		return -1;
	}

	@Override
	public boolean hasNext()
	{
		return 0 <= idx;
	}

	@Override
	public P next()
	{
		P curr = (P) pairs.next();
		idx = findNext();
		return curr;
	}
}