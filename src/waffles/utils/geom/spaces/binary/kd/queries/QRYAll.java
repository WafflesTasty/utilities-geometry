package waffles.utils.geom.spaces.binary.kd.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.binary.kd.KDNode;
import waffles.utils.geom.spaces.binary.kd.KDTree;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * The {@code QRYAll} class iterates over all objects in a {@code KDTree}.
 *
 * @author Waffles
 * @since 04 Apr 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class QRYAll<O> implements Iterator<O>
{
	private O next;
	private Iterator<O> curr;
	private Queue<KDNode<O>> nodes;
	
	/**
	 * Creates a new {@code QRYAll}.
	 * 
	 * @param s  a source tree
	 * 
	 * 
	 * @see KDTree
	 */
	public QRYAll(KDTree<O> s)
	{
		curr = new EmptyIterator<>();
		nodes = new JFIFOQueue<>();
		if(s.Root() != null)
		{
			nodes.push(s.Root());
		}
		
		next = findNext();
	}
	
	
	private O findNext()
	{
		if(curr.hasNext())
		{
			return curr.next();
		}
		
		if(nodes.isEmpty())
		{
			return null;
		}
		
		
		KDNode<O> node = nodes.pop();
		curr = node.Objects().iterator();
		return findNext();
	}
	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public O next()
	{
		O curr = (O) next;
		next = findNext();
		return curr;
	}
}