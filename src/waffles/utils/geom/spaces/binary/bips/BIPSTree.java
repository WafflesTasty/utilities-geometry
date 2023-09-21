package waffles.utils.geom.spaces.binary.bips;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.spaces.binary.BIPSpace;
import waffles.utils.sets.trees.indexed.BIPNode;
import waffles.utils.sets.trees.indexed.BIPTree;

/**
 * A {@code BIPSTree} defines the partition tree that backs a {@code BIPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <N>  a node type
 * @see BIPSNode
 * @see BIPTree
 * @see Bounded
 */
public class BIPSTree<N extends BIPSNode> extends BIPTree<N> implements Bounded
{		
	private BIPSpace<N> space;
	
	/**
	 * Creates a new {@code BIPSTree}.
	 * 
	 * @param s  a parent space
	 * @param dims   an index dimension
	 * 
	 * 
	 * @see BIPSpace
	 */
	public BIPSTree(BIPSpace<N> s, int... dims)
	{
		super(dims);
		space = s;
	}
	
	/**
	 * Returns the space of the {@code BIPSTree}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see BIPSpace
	 */
	public BIPSpace<?> Space()
	{
		return space;
	}

	
	@Override
	public Bounds Bounds()
	{
		return Root().Bounds();
	}
	
	@Override
	public BIPSNode nodeAt(int... crds)
	{
		return (BIPSNode) super.nodeAt(crds);
	}
	
	@Override
	public BIPSNode createNode(Object... vals)
	{
		return Space().createNode(vals);
	}
		
	@Override
	public N valueOf(BIPNode node)
	{
		return (N) node;
	}
	
	@Override
	public BIPSNode Root()
	{
		return (BIPSNode) super.Root();
	}
}