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
import waffles.utils.tools.collections.iterators.EmptyIterator;
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
	private O next;
	private Iterator<O> obj;
	private Queue<OrtNode<O>> nodes;
	private HyperCuboid tgt;

	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param s  a source tree
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see OrtTree
	 * @see HyperCuboid
	 */
	public QRYCuboid(OrtTree<O> s, HyperCuboid t)
	{
		this(s.Root(), t);
	}
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param n  a root node
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see OrtNode
	 * @see HyperCuboid
	 */
	public QRYCuboid(OrtNode<O> n, HyperCuboid t)
	{
		obj = new EmptyIterator<>();
		nodes = new JFIFOQueue<>();
		nodes.push(n);
		tgt = t;
		
		next = findNext();
	}


	private O findNext()
	{
		if(obj.hasNext())
			return obj.next();
		if(nodes.isEmpty())
			return null;

		
		OrtNode<O> n = nodes.pop();
		obj = n.Objects().iterator();

		Vector c = n.Bounds().Center();
		Vector min = tgt.Bounds().Minimum();
		Vector max = tgt.Bounds().Maximum();
		int dim = n.Dimension();
		
		
		if(!n.isLeaf())
		{
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