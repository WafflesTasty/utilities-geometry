package waffles.utils.geom.spaces.binary.beps;

import java.util.Iterator;

import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;

/**
 * A {@code BEPSIterator} class defines an iterator across a subsection of a {@code BEPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 * 
 * 
 * @param <E>  an enum type
 * @see BEPSNode
 * @see Iterator
 */
public class BEPSIterator<E extends Enum<E>> implements Iterator<BEPSNode<E>>
{
	private int[] min, max;
	private Queue<BEPSNode<E>> queue;
	
	/**
	 * Creates a new {@code BEPSIterator}.
	 * 
	 * @param t    a source tree
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see BEPSTree
	 */
	public BEPSIterator(BEPSTree<E> t, int[] min, int[] max)
	{
		queue = new JFIFOQueue<>();
		queue.push(t.Root());
		
		this.min = min;
		this.max = max;
	}
	
	
	@Override
	public boolean hasNext()
	{
		return !queue.isEmpty();
	}

	@Override
	public BEPSNode<E> next()
	{
		BEPSNode<E> node = queue.pop();
		if(!node.isLeaf())
		{
			int sDim = node.DimSplit();
			BEPSNode<E> lchild = node.LChild();
			BEPSNode<E> rchild = node.RChild();
			
			if(min[sDim] < rchild.Minimum()[sDim])
				queue.push(lchild);
			if(max[sDim] > lchild.Maximum()[sDim])
				queue.push(rchild);
		}

		return (BEPSNode<E>) node;
	}
}