package waffles.utils.geom.spaces.binary.kd.queries;

import java.util.Iterator;

import waffles.utils.algebra.elements.interval.Cut;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spaces.binary.kd.KDNode;
import waffles.utils.geom.spaces.binary.kd.KDTree;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;
import waffles.utils.tools.collections.iterators.EmptyIterator;

/**
 * The {@code QRYPoint} class iterates over all curr in a {@code KDTree} inside a {@code HyperCuboid}.
 *
 * @author Waffles
 * @since 04 Apr 2022
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 */
public class QRYCuboid<O> implements Iterator<O>
{
	private O next;
	private HyperCuboid tgt;
	private Iterator<O> curr;
	private Queue<KDNode<O>> nodes;
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param s  a source tree
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public QRYCuboid(KDTree<O> s, HyperCuboid t)
	{
		tgt = t;

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
		if(!node.isLeaf())
		{
			Cut cut = node.Plane().Cut();
			int dim = node.Plane().Dimension();

			
			Vector min = tgt.Bounds().Minimum();
			Vector max = tgt.Bounds().Maximum();
			
			if(cut.isAbove(min.get(dim)))
				nodes.push(node.LChild());
			if(cut.isBelow(max.get(dim)))
				nodes.push(node.RChild());
			return findNext();
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