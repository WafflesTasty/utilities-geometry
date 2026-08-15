package waffles.utils.geom.spaces.index.beps;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.NodalSpace;
import waffles.utils.geom.spaces.index.IndexBoreal;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.arboreal.binary.BiArboreal;
import waffles.utils.sets.arboreal.binary.indexed.BEPTree;
import waffles.utils.sets.utilities.indexed.coords.Coordinator;
import waffles.utils.tools.collections.Iterables;

/**
 * A {@code BEPSpace} defines a {@code BEPTree} as an {@code IndexBoreal}.
 *
 * @author Waffles
 * @since 13 Feb 2026
 * @version 1.1
 * 
 * 
 * @param <E>  an enum type
 * @see IndexBoreal
 * @see BEPSNode
 * @see BEPTree
 */
public class BEPSpace<E extends Enum<E>> extends BEPTree<E> implements IndexBoreal.Mutable<BEPSNode<E>, E>
{
	/**
	 * A {@code BEPSpace.Query} defines tree traversal queries for a {@code BEPSpace}.
	 *
	 * @author Waffles
	 * @since May 13, 2026
	 * @version 1.1
	 *
	 *
	 * @param <E>  an enum type
	 * @see BiArboreal
	 * @see NodalSpace
	 */
	public static interface Query<E extends Enum<E>> extends BiArboreal.Query<BEPSNode<E>>, NodalSpace.Query<BEPSNode<E>>
	{
		@Override
		public abstract BEPSpace<E> Tree();
	}
	
	/**
	 * A {@code BEPSpace.Factory} generates {@code BEPSNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see BEPTree
	 */
	public static interface Factory extends BEPTree.Factory
	{	
		@Override
		public default BEPSNode<?> node(Object... data)
		{
			int[] min = (int[]) data[0];
			int[] max = (int[]) data[1];

			return new BEPSNode<>(Tree(), min, max);
		}
		
		@Override
		public abstract BEPSpace<?> Tree();
	}
	
	/**
	 * A {@code BEPSpace.Hints} defines settings for a {@code BEPSpace}.
	 *
	 * @author Waffles
	 * @since Aug 15, 2026
	 * @version 1.1
	 *
	 * 
	 * @see Coordinator
	 */
	public static interface Hints extends Coordinator
	{
		/**
		 * Returns a {@code BEPSpace} tile size.
		 * 
		 * @return  a tile size
		 */
		public abstract float TileSize();
	}
	

	private Arrow size;
	
	/**
	 * Creates a new {@code BEPSpace}.
	 * 
	 * @param h  space hints
	 * 
	 * 
	 * @see Hints
	 */
	public BEPSpace(Hints h)
	{
		super(h.Dimensions());
		
		int ord = h.Order();
		float s = h.TileSize();
		size = Arrow.create(s, ord);
	}
	
			
	@Override
	public Iterable<BEPSNode<E>> Nodes(int[] min, int[] max)
	{
		return super.Nodes(min, max);
	}
	
	@Override
	public Iterable<BEPSNode<E>> query(HyperCuboid c)
	{
		Bounds bnd = c.Bounds();
		
		int[] min = indexOf(bnd.Minimum());
		int[] max = indexOf(bnd.Maximum());
		
		return Nodes(min, max);
	}
	
	@Override
	public Iterable<BEPSNode<E>> query(Point p)
	{
		int[] crds = indexOf(p);
		if(defines(crds))
		{
			BEPSNode<E> n = nodeAt(crds);
			if(n != null)
			{
				return Iterables.singleton(n);
			}
		}

		return Iterables.empty();
	}
	
	@Override
	public BEPSNode<E> nodeAt(int... crds)
	{
		return (BEPSNode<E>) super.nodeAt(crds);
	}
	
	@Override
	public BEPSNode<E> Root()
	{
		return (BEPSNode<E>) super.Root();
	}
	
	
	@Override
	public Query<E> Query()
	{
		return () -> this;
	}
	
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
		
	@Override
	public Arrow TileSize()
	{
		return size;
	}

	@Override
	public int Dimension()
	{
		return Order();
	}
}