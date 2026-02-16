package waffles.utils.geom.spaces.trees.planar.bipar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.planar.Plane;
import waffles.utils.geom.spaces.trees.planar.bipar.BPNode;
import waffles.utils.geom.spaces.trees.planar.bipar.BPSpace;
import waffles.utils.geom.spatial.bounds.owners.Bounded;

/**
 * A {@code QRYPoint} queries nodes at a point in a {@code BPSpace}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Iterator
 * @see Bounded
 * @see BPNode
 */
public class QRYPoint<O extends Bounded> implements Iterator<BPNode<O>>
{
	private Point pnt;
	private BPNode<O> next;
	
	/**
	 * Creates a new {@code QRYPoint}.
	 *
	 * @param s  a parent space
	 * @param p  a target point
	 *
	 * 
	 * @see BPSpace
	 * @see Point
	 */
	public QRYPoint(BPSpace<O> s, Point p)
	{
		next = s.Root();
		pnt = p;
	}

	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public BPNode<O> next()
	{
		BPNode<O> curr = next;
		if(next.isLeaf())
			next = null;
		else
		{
			Plane p = next.Plane();
			if(p.contains(pnt))
				next = next.RChild();
			else
				next = next.LChild();			
		}
		
		return curr;
	}
}