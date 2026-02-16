package waffles.utils.geom.spaces.trees.planar.bipar;

import waffles.utils.geom.spaces.trees.planar.PlanarBoreal;
import waffles.utils.geom.spaces.trees.planar.Plane;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.binary.BiTree;

/**
 * A {@code BPTree} implements a kd-tree as a {@code PlanarBoreal} binary tree.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PlanarBoreal
 * @see Bounded
 * @see BPNode
 * @see BiTree
 */
public class BPTree<O extends Bounded> extends BiTree implements PlanarBoreal<BPNode<O>>
{
	/**
	 * A {@code BPSpace.Factory} generates {@code BPNode} objects.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see Bounded
	 * @see BiTree
	 */
	public static interface Factory extends BiTree.Factory
	{
		@Override
		public default BPNode<?> node(Object... data)
		{
			Plane p = (Plane) data[0];
			return new BPNode<>(Tree(), p);
		}
		
		@Override
		public abstract BPTree<?> Tree();
	}
	
	
	private BPSpace<O> space;
	
	/**
	 * Creates a new {@code BPTree}.
	 * 
	 * @param s  a parent space
	 * 
	 * 
	 * @see BPSpace
	 */
	public BPTree(BPSpace<O> s)
	{
		space = s;
	}
	
	/**
	 * Returns the {@code BPSpace}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see BPSpace
	 */
	public BPSpace<O> Space()
	{
		return space;
	}

	
	@Override
	public BPNode<O> Root()
	{
		return (BPNode<O>) super.Root();
	}
		
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
	
	@Override
	public Bounds Bounds()
	{
		return Space().Bounds();
	}
}