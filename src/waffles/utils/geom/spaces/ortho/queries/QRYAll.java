package waffles.utils.geom.spaces.ortho.queries;

import java.util.Iterator;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.spaces.ortho.OrtNode;
import waffles.utils.geom.spaces.ortho.OrtTree;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * A {@code QRYAll} queries all objects within an {@code OrtTree}.
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
public class QRYAll<O extends Bounded> implements Iterator<O>
{
	private O next;
	private Iterator<O> obj;
	private Iterator<OrtNode<O>> nodes;

	/**
	 * Creates a new {@code QRYAll}.
	 * 
	 * @param s  a source tree
	 * 
	 * 
	 * @see OrtTree
	 */
	public QRYAll(OrtTree<O> s)
	{
		obj = new EmptyIterator<>();
		nodes = s.BFSearch().iterator();
		next = findNext();
	}


	private O findNext()
	{
		if(obj.hasNext())
			return obj.next();
		if(!nodes.hasNext())
			return null;

		
		OrtNode<O> n = nodes.next();
		Iterable<O> objects = n.Objects();
		obj = objects.iterator();
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
		O curr = next;
		next = findNext();
		return curr;
	}
}