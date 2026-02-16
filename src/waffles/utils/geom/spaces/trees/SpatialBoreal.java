package waffles.utils.geom.spaces.trees;

import java.util.Iterator;

import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.rooted.iterators.BreadthFirst;

/**
 * A {@code SpatialBoreal} defines an abstract {@code Arboreal Space}.
 *
 * @author Waffles
 * @since 16 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @see SpatialNodal
 * @see Arboreal
 * @see Space
 */
public interface SpatialBoreal<N extends SpatialNodal> extends Arboreal, Bounded, Space<N>
{
	/**
	 * A {@code SpatialBoreal.Query} defines queries for a {@code SpatialBoreal} tree.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a node type
	 * @see SpatialNodal
	 * @see Space
	 */
	@FunctionalInterface
	public static interface Query<N extends SpatialNodal> extends Space.Query<N>
	{
		/**
		 * Returns the tree of the {@code Query}.
		 * 
		 * @return  a spatial tree
		 * 
		 * 
		 * @see SpatialBoreal
		 */
		public abstract SpatialBoreal<N> Tree();

		@Override
		public default Iterator<N> All()
		{
			return new BreadthFirst<>(Tree().Root());
		}
	}
	
	
	@Override
	public default Bounds Bounds()
	{
		return Root().Bounds();
	}
	
	@Override
	public default Query<N> Query()
	{
		return () -> this;
	}
		
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
	
	@Override
	public abstract N Root();
}