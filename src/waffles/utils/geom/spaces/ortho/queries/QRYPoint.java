package waffles.utils.geom.spaces.ortho.queries;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.ortho.OrtNode;
import waffles.utils.geom.spaces.ortho.OrtTree;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code QRYPoint} queries a point within an {@code OrtTree}.
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
public class QRYPoint<O extends Bounded> implements Iterator<O>
{
	private Vector tgt;
	private Iterator<O> obj;
	private OrtNode<O> curr;

	/**
	 * Creates a new {@code QRYPoint}.
	 * 
	 * @param s  a source tree
	 * @param t  a target point
	 * 
	 * 
	 * @see OrtTree
	 * @see Point
	 */
	public QRYPoint(OrtTree<O> s, Point t)
	{
		this(s.Root(), t);
	}
	
	/**
	 * Creates a new {@code QRYPoint}.
	 * 
	 * @param n  a root node
	 * @param p  a target point
	 * 
	 * 
	 * @see OrtNode
	 * @see Point
	 */
	public QRYPoint(OrtNode<O> n, Point p)
	{
		curr = n;
		tgt = p.Generator();
		obj = n.Objects().iterator();
		findNext();
	}
	
	
	private void findNext()
	{
		if(obj.hasNext()) return;		
		if(curr.isLeaf()) return;
		
		
		Bounds bnd = curr.Bounds();
		int dim = bnd.Dimension();
		Vector c = bnd.Center();
		
		int next = 0;
		for(int i = 0; i < dim; i++)
		{
			if(c.get(i) < tgt.get(i))
			{
				next += Integers.pow(2, i);
			}
		}
		
		
		curr = curr.Child(next);
		obj = curr.Objects().iterator();
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