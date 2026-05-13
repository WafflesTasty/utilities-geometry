package waffles.utils.geom.spaces.arboreal.planar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNodal;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNode;
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
 * @param <N>  a nodal type
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
	 * @param r  a root nodal
	 * @param c  a cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public QRYCuboid(N r, HyperCuboid c)
	{
		nodes = new FIFOQueue<>();
		nodes.push(r);
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
		PlanarNode n = curr.Arch();		
		Plane p = curr.Plane();

		if(p.contains(cb))
			nodes.push(n.RChild());
		else if(!p.intersects(cb))
			nodes.push(n.LChild());
		else
		{
			nodes.push(n.LChild());
			nodes.push(n.RChild());
		}

		return curr;
	}
}