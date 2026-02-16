package waffles.utils.geom.spaces.trees.planar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.planar.PlanarBoreal;
import waffles.utils.geom.spaces.trees.planar.PlanarNodal;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.wrapper.FIFOQueue;

/**
 * A {@code QRYCuboid} queries a cuboid in a {@code PlanarBoreal}.
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
public class QRYCuboid<N extends PlanarNodal> implements Iterator<N>
{
	private HyperCuboid cb;
	private Queue<PlanarNodal> nodes;
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 * 
	 * @param t  a parent tree
	 * @param c  a cuboid
	 * 
	 * 
	 * @see PlanarBoreal
	 * @see HyperCuboid
	 */
	public QRYCuboid(PlanarBoreal<N> t, HyperCuboid c)
	{
		nodes = new FIFOQueue<>();
		nodes.push(t.Root());
		cb = c;
	}

	
	@Override
	public boolean hasNext()
	{
		return !nodes.isEmpty();
	}

	@Override
	public N next()
	{
		N curr = (N) nodes.pop();
		
		Point min = cb.Bounds().Minimum();
		Point max = cb.Bounds().Maximum();
		
		if(!curr.contains(min))
			nodes.push(curr.Arch().LChild());		
		if( curr.contains(max))
			nodes.push(curr.Arch().RChild());

		return curr;
	}
}