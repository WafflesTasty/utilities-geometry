package waffles.utils.geom.spaces.axial.ro;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.axial.orto.OrtoTree;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.utilities.keymaps.Pair;

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
 * @see OrtoTree
 * @see Bounded
 * @see RCNode
 */
public class RCTree<O extends Bounded> extends OrtoTree<RCNode<O>>
{
	/**
	 * An {@code RCTree.Factory} generates {@code RCNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see OrtoTree
	 */
	public static interface Factory extends OrtoTree.Factory
	{			
		/**
		 * Constructs a {@code Pair} in the {@code Factory}.
		 * 
		 * @param k  a key object
		 * @param v  a value object
		 * @return   a key-value pair
		 * 
		 * 
		 * @see Pair
		 */
		public default Pair<?, ?> pair(Object k, Object v)
		{
			return new Pair.Base<>(k, v);
		}
		
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
	public Iterable<RCNode<O>> BFSearch()
	{
		return super.BFSearch();
	}
	
	@Override
	public Iterable<RCNode<O>> DFSearch()
	{
		return super.DFSearch();
	}

	@Override
	public Factory Factory()
	{
		return Space().Factory();
	}

	@Override
	public void clear()
	{
		Root().clear();
	}
}