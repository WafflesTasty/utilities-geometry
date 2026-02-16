package waffles.utils.geom.spaces.trees.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.trees.SpatialNodal;
import waffles.utils.geom.spaces.trees.SpatialTree;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYNodes} queries nodes in a {@code SpatialTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <N>  a node type
 * @see SpatialNodal
 * @see Iterator
 */
public class QRYNodes<O, N extends SpatialNodal> implements Iterator<O>
{
	private O next;
	private Iterator<N> nodes;
	private SpatialTree.Query<O, N> qry;
	private Iterator<O> objects;

	/**
	 * Creates a new {@code QRYNodes}.
	 *
	 * @param q  a space query
	 * @param n  a node iterable
	 *
	 * 
	 * @see SpatialTree
	 * @see Iterable
	 */
	public QRYNodes(SpatialTree.Query<O, N> q, Iterable<N> n)
	{	
		objects = new EmptyIterator<>();
		nodes = n.iterator();
		qry = q;
		
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
			N node = nodes.next();
			objects = qry.in(node);
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