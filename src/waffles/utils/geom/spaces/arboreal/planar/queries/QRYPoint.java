package waffles.utils.geom.spaces.arboreal.planar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNodal;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNode;

/**
 * A {@code QRYPoint} queries a point in a {@code PlanarBoreal}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
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
	 * @param r  a root nodal
	 * @param p  a point
	 *
	 * 
	 * @see Point
	 */
	public QRYPoint(N r, Point p)
	{
		next = r;
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