package waffles.utils.geom.spaces.binary.kd.queries;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.binary.kd.KDNode;
import waffles.utils.geom.spaces.binary.kd.KDTree;
import waffles.utils.geom.spaces.binary.kd.KDNode.Cut;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * The {@code QRYPoint} class iterates over all curr in a {@code KDTree} at a {@code Point}.
 *
 * @author Waffles
 * @since 04 Apr 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class QRYPoint<O> implements Iterator<O>
{
	private O next;
	private KDNode<O> node;
	private Iterator<O> curr;
	private Vector tgt;
	
	/**
	 * Creates a new {@code QRYPoint}.
	 * 
	 * @param s  a source tree
	 * @param t  a target point
	 * 
	 * 
	 * @see KDTree
	 * @see Point
	 */
	public QRYPoint(KDTree<O> s, Point t)
	{
		node = s.Root(); 
		tgt = t.Generator();
		curr = new EmptyIterator<>();
		next = findNext();
	}
	
	
	private O findNext()
	{		
		if(curr.hasNext())
		{
			return curr.next();
		}
		
		if(node == null)
		{
			return null;
		}
		
		
		curr = node.Objects().iterator();
		if(node.isLeaf())
			node = null;
		else
		{
			Cut cut = node.Cut();
			float val = cut.Value();
			int dim = cut.Dimension();

			if(tgt.get(dim) < val)
				node = node.LChild();
			else
				node = node.RChild();
		}

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