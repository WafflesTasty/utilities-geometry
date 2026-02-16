package waffles.utils.geom.spaces.trees.axial.orto.recube;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.axial.orto.OrtoBoreal;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;

/**
 * An {@code RCTree} defines a recursive cuboid tree.
 * Each node represents a cuboid shape in space and can be
 * subdivided into equally sized child nodes. This generalizes
 * the concept of quadtrees and octrees to any dimension.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see OrtoBoreal
 * @see Bounded
 * @see RCNode
 * @see Tree
 */
public class RCTree<O extends Bounded> extends Tree implements OrtoBoreal<RCNode<O>>
{
	/**
	 * An {@code RCTree.Factory} generates {@code RCNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arboreal
	 */
	public static interface Factory extends Arboreal.Factory
	{			
		@Override
		public default RCNode<?> node(Object... data)
		{
			Point o = (Point) data[0];
			Arrow s = (Arrow) data[1];

			return new RCNode<>(Tree(), o, s);
		}
		
		@Override
		public abstract RCTree<?> Tree();
	}
	
	
	private RCSpace<O> space;

	/**
	 * Creates a new {@code RCTree}.
	 * 
	 * @param s  a parent space
	 * 
	 * 
	 * @see RCSpace
	 */
	public RCTree(RCSpace<O> s)
	{
		space = s;
	}
	
	/**
	 * Returns the {@code RCSpace}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see RCSpace
	 */
	public RCSpace<O> Space()
	{
		return space;
	}
	
		
	@Override
	public Factory Factory()
	{
		return Space().Factory();
	}

	@Override
	public RCNode<O> Root()
	{
		return (RCNode<O>) super.Root();
	}
	
	@Override
	public void clear()
	{
		Root().clear();
	}
}