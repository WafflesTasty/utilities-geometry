package waffles.utils.geom.spaces.index.beps;

import waffles.utils.geom.spaces.index.IndexNodal;
import waffles.utils.sets.arboreal.binary.indexed.BEPNode;

/**
 * A {@code BEPSNode} defines a single node in a {@code BEPSpace}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <E>  an enum type
 * @see IndexNodal
 * @see BEPNode
 */
public class BEPSNode<E extends Enum<E>> extends BEPNode<E> implements IndexNodal
{
	/**
	 * Creates a new {@code BEPSNode}.
	 * 
	 * @param p    a parent space
	 * @param min  a minimum index
	 * @param max  a maximum index
	 * 
	 * 
	 * @see BEPSpace
	 */
	public BEPSNode(BEPSpace<E> p, int[] min, int[] max)
	{
		super(p, min, max);
	}

	
	@Override
	public BEPSNode<E> Arch()
	{
		return this;
	}
	
	@Override
	public BEPSNode<E> Parent()
	{
		return (BEPSNode<E>) super.Parent();
	}

	@Override
	public BEPSpace<E> Set()
	{
		return (BEPSpace<E>) super.Set();
	}
}