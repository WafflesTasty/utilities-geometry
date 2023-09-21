package waffles.utils.geom.spaces.binary.bips;

import waffles.utils.geom.spaces.binary.BIPSpace;
import waffles.utils.geom.spaces.index.nodes.IndexNode;
import waffles.utils.sets.trees.indexed.BIPNode;

/**
 * A {@code BIPSNode} defines a single node in a {@code BIPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @see IndexNode
 * @see BIPNode
 */
public class BIPSNode extends BIPNode implements IndexNode
{		
	/**
	 * Creates a new {@code BIPSNode}.
	 * 
	 * @param tree  a parent tree
	 * @param min   a minimum index
	 * @param max   a maximum index
	 * 
	 * 
	 * @see BIPSTree
	 */
	public BIPSNode(BIPSTree<?> tree, int[] min, int[] max)
	{
		super(tree, min, max);
	}


	@Override
	public BIPSNode get(int... coords)
	{
		return (BIPSNode) super.get(coords);
	}
		
	@Override
	public BIPSNode Parent()
	{
		return (BIPSNode) super.Parent();
	}
	
	@Override
	public BIPSNode LChild()
	{
		return (BIPSNode) super.LChild();
	}
	
	@Override
	public BIPSNode RChild()
	{
		return (BIPSNode) super.RChild();
	}
	
	@Override
	public BIPSNode Sibling()
	{
		return (BIPSNode) super.Sibling();
	}
	
	@Override
	public BIPSpace<?> Space()
	{
		return Set().Space();
	}
	
	@Override
	public BIPSTree<?> Set()
	{
		return (BIPSTree<?>) super.Set();
	}
}