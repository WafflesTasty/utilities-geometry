package waffles.utils.geom.spaces.trees.axial.orto.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.axial.orto.OrtoBoreal;
import waffles.utils.geom.spaces.trees.axial.orto.OrtoNodal;
import waffles.utils.geom.spaces.trees.axial.orto.OrtoNode;

/**
 * A {@code QRYPoint} queries a point in an {@code OrtoTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <N>  a node type
 * @see OrtoNodal
 * @see Iterator
 */
public class QRYPoint<N extends OrtoNodal> implements Iterator<N>
{
	private Point pnt;
	private OrtoNodal curr;

	/**
	 * Creates a new {@code QRYPoint}.
	 * 
	 * @param t  an orto tree
	 * @param p  a point
	 * 
	 * 
	 * @see OrtoBoreal
	 * @see Point
	 */
	public QRYPoint(OrtoBoreal<N> t, Point p)
	{
		curr = t.Root();
		pnt = p;		
	}


	@Override
	public boolean hasNext()
	{
		return curr != null;
	}
	
	@Override
	public N next()
	{
		N next = (N) curr;
		int idx = next.index(pnt);
		OrtoNode n = next.Arch();
		curr = n.Child(idx);
		return next;
	}
}