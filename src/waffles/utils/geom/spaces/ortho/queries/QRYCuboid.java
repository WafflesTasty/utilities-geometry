package waffles.utils.geom.spaces.ortho.queries;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spaces.ortho.OrtNode;
import waffles.utils.geom.spaces.ortho.OrtTree;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code QRYCuboid} queries a cuboid within an {@code OrtTree}.
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
public class QRYCuboid<O extends Bounded> implements Iterator<O>
{
	private int next;
	private List<O> list;
	private HyperCuboid tgt;
	private Queue<OrtNode<O>> nodes;

	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param s  a source tree
	 * @param t  a target bounded
	 * 
	 * 
	 * @see Bounded
	 * @see OrtTree
	 */
	public QRYCuboid(OrtTree<O> s, Bounded t)
	{
		this(s.Root(), 0, t);
	}
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param n  a root node
	 * @param i  an object index
	 * @param b  a target bounded
	 * 
	 * 
	 * @see Bounded
	 * @see OrtNode
	 */
	public QRYCuboid(OrtNode<O> n, int i, Bounded b)
	{
		list = n.Objects();
		tgt = b.Bounds().Box();
		
		nodes = new JFIFOQueue<>();
		if(!n.isLeaf())
		{
			queue(n);
		}
		
		next = i - 1;
		next = findNext();
	}


	private int findNext()
	{
		next++;
		if(next < list.Count())
			return next;
		if(nodes.isEmpty())
			return -1;
		next = -1;
		
		
		OrtNode<O> n = nodes.pop();		
		if(!n.isLeaf())
		{
			queue(n);
		}

		
		list = n.Objects();
		return findNext();
	}
	
	private void queue(OrtNode<O> n)
	{
		Vector c = n.Bounds().Center();
		Vector min = tgt.Bounds().Minimum();
		Vector max = tgt.Bounds().Maximum();
		int dim = n.Dimension();
		
		
		List<Integer> list = new List<>(0);
		for(int i = 0; i < dim; i++)
		{
			int count = list.Count();
			if(c.get(i) < min.get(i))
			{
				for(int j = 0; j < count; j++)
				{
					int index = list.get(j);
					index += Integers.pow(2, i);
					list.put(index, j);
				}
				continue;
			}

			if(c.get(i) < max.get(i))
			{
				for(int j = 0; j < count; j++)
				{
					int index = list.get(j);
					index += Integers.pow(2, i);
					list.add(index);
				}
			}			
		}
		
		for(int i : list)
		{
			OrtNode<O> child = n.Child(i);
			nodes.push(child);
		}
	}
	
	@Override
	public boolean hasNext()
	{
		return next >= 0;
	}
	
	@Override
	public O next()
	{
		O curr = list.get(next);
		next = findNext();
		return curr;
	}
}