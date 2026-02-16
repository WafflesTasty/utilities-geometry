package waffles.utils.geom.spaces.axial.ro.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.axial.ro.RCNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYNodes} queries nodes in an {@code RCTree}.
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
	private Iterator<RCNode<O>> nodes;

	/**
	 * Creates a new {@code QRYNodes}.
	 *
	 * @param n  a parent iterable
	 *
	 * 
	 * @see Iterable
	 * @see RCNode
	 */
	public QRYNodes(Iterable<RCNode<O>> n)
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
			RCNode<O> n = nodes.next();
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