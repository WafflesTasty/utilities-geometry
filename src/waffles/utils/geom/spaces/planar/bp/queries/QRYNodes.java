package waffles.utils.geom.spaces.planar.bp.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.planar.bp.BPNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYNodes} queries nodes in a {@code BPSpace}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 * @see Bounded
 */
public class QRYNodes<O extends Bounded> implements Iterator<O>
{
	private O next;
	private Iterator<O> objects;
	private Iterator<BPNode<O>> nodes;

	/**
	 * Creates a new {@code QRYNodes}.
	 *
	 * @param n  a parent iterable
	 *
	 * 
	 * @see Iterable
	 * @see BPNode
	 */
	public QRYNodes(Iterable<BPNode<O>> n)
	{
		objects = new EmptyIterator<>();
		
		nodes = n.iterator();
		next = findNext();
	}


	private O findNext()
	{
		if(objects.hasNext())
		{
			return objects.next();
		}
		
		if(nodes.hasNext())
		{
			BPNode<O> n = nodes.next();
			objects = n.iterator();
			return findNext();
		}

		return null;
	}

	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public O next()
	{
		O curr = next;
		next = findNext();
		return curr;
	}
}