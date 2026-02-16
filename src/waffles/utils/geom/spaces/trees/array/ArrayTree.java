package waffles.utils.geom.spaces.trees.array;

import java.util.Iterator;

import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.trees.SpatialBoreal;
import waffles.utils.geom.spaces.trees.SpatialTree;
import waffles.utils.geom.spaces.trees.queries.QRYManifold;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * An {@code ArrayTree} defines an {@code ArrayNodal SpatialTree} as a {@code Manifold}.
 *
 * @author Waffles
 * @since 16 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <N>  a node type
 * @see SpatialTree
 * @see ArrayNodal
 * @see Manifold
 */
public interface ArrayTree<O, N extends ArrayNodal<O>> extends SpatialTree<O, N>, Manifold<O>
{
	/**
	 * An {@code ArrayTree.Query} defines queries for an {@code ArrayTree}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <N>  a node type
	 * @see SpatialTree
	 * @see ArrayNodal
	 * @see Manifold
	 */
	@FunctionalInterface
	public interface Query<O, N extends ArrayNodal<O>> extends SpatialTree.Query<O, N>, Manifold.Query<O>
	{			
		@Override
		public default Iterator<Pair<O, O>> Pairs()
		{
			SpatialBoreal<N> t = Space().Tree();
			return new QRYManifold<>(t.BFSearch());
		}
		
		@Override
		public default Iterator<O> in(N n)
		{
			return n.iterator();
		}
	}
	
	
	@Override
	public default Bounds Bounds()
	{
		return Tree().Bounds();
	}
	
	@Override
	public default Query<O, N> Query()
	{
		return () -> this;
	}
	
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
	
	@Override
	public default N Root()
	{
		return Tree().Root();
	}
}