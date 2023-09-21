package waffles.utils.geom.spaces.ortho;

import java.util.Iterator;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.delegate.JFIFOQueue;

/**
 * A {@code QRYCuboid} queries a cuboid within an {@code OrtTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 * @see Bounded
 */
public class QRYCuboid<O extends Bounded> implements Iterator<O>
{
	private ICuboid tgt;
	private Iterator<O> obj;
	private Queue<OrtNode<O>> queue;

	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param s  a source tree
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see OrtTree
	 * @see ICuboid
	 */
	public QRYCuboid(OrtTree<O> s, ICuboid t)
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
	 * @see ICuboid
	 */
	public QRYCuboid(OrtNode<O> n, ICuboid t)
	{
		queue = new JFIFOQueue<>();
		
		tgt = t;
		iterate(n);
		findNext();
	}
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param n  a root node
	 * 
	 * 
	 * @see OrtNode
	 */
	public QRYCuboid(OrtNode<O> n)
	{
		queue = new JFIFOQueue<>();
		
		iterate(n);
		findNext();
	}
	
	
	private Iterable<OrtNode<O>> children(OrtNode<O> n)
	{
		if(tgt != null)
		{
			return () -> new QRYChildren<>(n, tgt);
		}
		
		return () -> new QRYChildren<>(n);
	}
	
	private void iterate(OrtNode<O> n)
	{
		obj = n.Objects().iterator();
		if(n.isLeaf()) return;
		
		for(OrtNode<O> child : children(n))
		{
			queue.push(child);
		}
	}
	
	private void findNext()
	{
		if(obj.hasNext()) return;		
		if(queue.isEmpty()) return;
		OrtNode<O> n = queue.pop();
		
		iterate(n);
		findNext();
	}

	
	@Override
	public boolean hasNext()
	{
		return obj.hasNext();
	}
	
	@Override
	public O next()
	{
		O next = obj.next();
		if(!hasNext())
		{
			findNext();
		}
		
		return next;
	}
}