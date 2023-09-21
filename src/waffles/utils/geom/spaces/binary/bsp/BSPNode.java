package waffles.utils.geom.spaces.binary.bsp;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.spaces.binary.kd.KDNode;
import waffles.utils.sets.indexed.delegate.List;

/**
 * A {@code BSPNode} defines a single node in a {@code BSPTree}.
 * </br> It implements a {@code KDNode} with a variable
 * list of bounded objects.
 *
 * @author Waffles
 * @since 05 Apr 2022
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see Bounded
 * @see KDNode
 */
public class BSPNode<O extends Bounded> extends KDNode<O>
{
	private List<O> objects;
	
	/**
	 * Creates a new {@code BSPNode}.
	 * 
	 * @param tree  a target tree
	 */
	public BSPNode(BSPTree<O> tree)
	{
		super(tree);
		objects = new List<>();
	}
	
	/**
	 * Checks for an item in the {@code BSPNode}.
	 * 
	 * @param item  an item to check
	 * @return  {@code true} if the item is here
	 */
	public boolean contains(O item)
	{
		return objects.contains(item);
	}
	
	/**
	 * Removes an item from the {@code BSPNode}.
	 * 
	 * @param item  an item to remove
	 */
	public void remove(O item)
	{
		objects.remove(item);
	}
	
	/**
	 * Adds an item to the {@code BSPNode}.
	 * 
	 * @param item  an item to add
	 */
	public void add(O item)
	{
		objects.add(item);
	}

	/**
	 * Counts objects in the {@code BSPNode}.
	 * 
	 * @return  an item count
	 */
	public int Count()
	{
		return objects.Count();
	}
	
	
	@Override
	public Iterable<O> Objects()
	{
		return objects;
	}
	
	@Override
	public BSPNode<O> Parent()
	{
		return (BSPNode<O>) super.Parent();
	}
	
	@Override
	public BSPNode<O> LChild()
	{
		return (BSPNode<O>) super.LChild();
	}
	
	@Override
	public BSPNode<O> RChild()
	{
		return (BSPNode<O>) super.RChild();
	}
}