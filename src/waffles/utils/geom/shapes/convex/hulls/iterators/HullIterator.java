package waffles.utils.geom.shapes.convex.hulls.iterators;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.convex.hulls.Hull.Factory;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code HullIterator} iterates over points in a {@code Hull}.
 *
 * @author Waffles
 * @since 29 Jan 2026
 * @version 1.1
 *
 * 
 * @see Iterator
 * @see Point
 */
public class HullIterator implements Iterator<Point>
{
	private int curr;
	private Hull hull;
	
	/**
	 * Creates a new {@code HullIterator}.
	 * 
	 * @param h  a source hull
	 * 
	 * 
	 * @see Hull
	 */
	public HullIterator(Hull h)
	{
		hull = h;
	}
	
	
	@Override
	public boolean hasNext()
	{
		Factory fct = hull.Factory();
		return curr < fct.Count();
	}

	@Override
	public Point next()
	{
		Factory fct = hull.Factory();
		return fct.Point(curr++);
	}
}