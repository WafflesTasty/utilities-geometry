package waffles.utils.geom.spaces.index.beps;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.sets.trees.indexed.BEPTree;

/**
 * A {@code BEPSTree} defines the partition tree that backs a {@code BEPSpace}.
 *
 * @author Waffles
 * @since 29 Dec 2022
 * @version 1.0
 *
 * 
 * @param <E>  an enum type
 * @see Bounded
 * @see BEPTree
 */
public class BEPSTree<E extends Enum<E>> extends BEPTree<E> implements Bounded
{		
	private BEPSpace<E> space;
	
	/**
	 * Creates a new {@code BEPSTree}.
	 * 
	 * @param s  a parent space
	 * @param dims   an index dimension
	 * 
	 * 
	 * @see BEPSpace
	 */
	public BEPSTree(BEPSpace<E> s, int... dims)
	{
		super(dims);
		space = s;
	}
	
	/**
	 * Returns the space of the {@code BEPSTree}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see BEPSpace
	 */
	public BEPSpace<E> Space()
	{
		return space;
	}

		
	@Override
	public BEPSNode<E> createNode(Object... vals)
	{
		return Space().createNode(vals);
	}
	
	@Override
	public BEPSNode<E> nodeAt(int... coords)
	{
		return (BEPSNode<E>) super.nodeAt(coords);
	}
	
	@Override
	public BEPSNode<E> Root()
	{
		return (BEPSNode<E>) super.Root();
	}

	@Override
	public Bounds Bounds()
	{
		return Root().Bounds();
	}
}