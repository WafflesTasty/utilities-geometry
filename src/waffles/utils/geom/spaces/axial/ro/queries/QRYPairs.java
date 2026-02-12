package waffles.utils.geom.spaces.axial.ro.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.axial.ro.RCNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.rooted.iterators.BreadthFirst;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYPairs} queries object pairs in an {@code RCNode}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @param <P>  a pair type
 * @see Iterator
 * @see Bounded
 * @see Pair
 */
public class QRYPairs<O extends Bounded, P extends Pair<?, ?>> implements Iterator<P>
{
	private int idx;
	private RCNode<O> curr;
	private Iterator<RCNode<O>> nodes;
	private Iterator<Pair<O, O>> pairs;

	/**
	 * Creates a new {@code QRYPairs}.
	 *
	 * @param n  a parent node
	 *
	 * 
	 * @see RCNode
	 */
	public QRYPairs(RCNode<O> n)
	{
		pairs = new EmptyIterator<>();
		nodes = new BreadthFirst<>(n);

		curr = nodes.next();
		idx = findNext();
	}


	private int findNext()
	{
		if(pairs.hasNext())
		{
			return idx;
		}
		
		if(idx < curr.Count())
		{
			pairs = curr.Pairs(idx++);
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