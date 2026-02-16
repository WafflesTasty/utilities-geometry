package waffles.utils.geom.spaces.trees.planar.bipar.queries;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.planar.bipar.BPNode;
import waffles.utils.geom.spaces.trees.planar.bipar.BPSpace;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.queues.Queue;
import waffles.utils.sets.queues.wrapper.FIFOQueue;

/**
 * A {@code QRYCuboid} queries nodes in a cuboid section of a {@code BPSpace}.
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
public class QRYCuboid<O extends Bounded> implements Iterator<BPNode<O>>
{
	private HyperCuboid cb;
	private Queue<BPNode<O>> nodes;
	
	/**
	 * Creates a new {@code QRYCuboid}.
	 *
	 * @param s  a parent space
	 * @param c  a target cuboid
	 *
	 * 
	 * @see HyperCuboid
	 * @see BPSpace
	 */
	public QRYCuboid(BPSpace<O> s, HyperCuboid c)
	{
		nodes = new FIFOQueue<>();
		nodes.push(s.Root());
		cb = c;
	}

	
	@Override
	public boolean hasNext()
	{
		return !nodes.isEmpty();
	}

	@Override
	public BPNode<O> next()
	{
		BPNode<O> curr = nodes.pop();
		
		Point min = cb.Bounds().Minimum();
		Point max = cb.Bounds().Maximum();
		
		if(!curr.contains(min))
			nodes.push(curr.LChild());		
		if( curr.contains(max))
			nodes.push(curr.RChild());

		return curr;
	}
}