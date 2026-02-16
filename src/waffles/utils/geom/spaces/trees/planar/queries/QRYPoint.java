package waffles.utils.geom.spaces.trees.planar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.planar.PlanarBoreal;
import waffles.utils.geom.spaces.trees.planar.PlanarNodal;
import waffles.utils.geom.spaces.trees.planar.PlanarNode;
import waffles.utils.geom.spaces.trees.planar.Plane;

/**
 * A {@code QRYPoint} queries a point in a {@code PlanarBoreal}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @see PlanarNodal
 * @see Iterator
 */
public class QRYPoint<N extends PlanarNodal> implements Iterator<N>
{
	private Point pnt;
	private PlanarNodal next;
	
	/**
	 * Creates a new {@code QRYPoint}.
	 *
	 * @param t  a parent tree
	 * @param p  a point
	 *
	 * 
	 * @see PlanarBoreal
	 * @see Point
	 */
	public QRYPoint(PlanarBoreal<N> t, Point p)
	{
		next = t.Root();
		pnt = p;
	}

	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public N next()
	{
		N curr = (N) next;
		
		PlanarNode n = next.Arch();
		if(n.isLeaf())
			next = null;
		else
		{
			Plane p = next.Plane();
			if(p.contains(pnt))
				next = n.RChild();
			else
				next = n.LChild();			
		}
		
		return curr;
	}
}