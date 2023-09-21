package waffles.utils.geom.spaces.binary.beps;

import waffles.utils.geom.spaces.binary.BEPSpace;
import waffles.utils.geom.spaces.index.nodes.IndexNode;
import waffles.utils.sets.trees.indexed.BEPNode;

/**
 * A {@code BEPSNode} defines a single node in a {@code BEPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <E>  an enum type
 * @see IndexNode
 * @see BEPNode
 */
public class BEPSNode<E extends Enum<E>> extends BEPNode<E> implements IndexNode
{		
	/**
	 * Creates a new {@code BEPSNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BEPSTree
	 */
	public BEPSNode(BEPSTree<E> tree, int[] min, int[] max)
	{
		super(tree, min, max);
	}


	@Override
	public BEPSNode<E> get(int... coords)
	{
		return (BEPSNode<E>) super.get(coords);
	}
		
	@Override
	public BEPSNode<E> Parent()
	{
		return (BEPSNode<E>) super.Parent();
	}
	
	@Override
	public BEPSNode<E> LChild()
	{
		return (BEPSNode<E>) super.LChild();
	}
	
	@Override
	public BEPSNode<E> RChild()
	{
		return (BEPSNode<E>) super.RChild();
	}
	
	@Override
	public BEPSNode<E> Sibling()
	{
		return (BEPSNode<E>) super.Sibling();
	}
	
	@Override
	public BEPSpace<E> Space()
	{
		return Set().Space();
	}
	
	@Override
	public BEPSTree<E> Set()
	{
		return (BEPSTree<E>) super.Set();
	}
}