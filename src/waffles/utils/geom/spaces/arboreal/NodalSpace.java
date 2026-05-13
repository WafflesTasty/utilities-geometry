package waffles.utils.geom.spaces.arboreal;

import waffles.utils.geom.spaces.arboreal.nodal.SpatialNodal;
import waffles.utils.sets.arboreal.arborus.Arborus;

/**
 * A {@code NodalSpace} defines an {@code ArborealSpace} which
 * treats its node structure as its {@code Space} objects.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <N>  a nodal type
 * @see ArborealSpace
 * @see SpatialNodal
 */
@FunctionalInterface
public interface NodalSpace<N extends SpatialNodal> extends ArborealSpace<N>, Arborus<N>
{
	/**
	 * A {@code NodalSpace.Query} defines queries for a {@code NodalSpace}.
	 *
	 * @author Waffles
	 * @since May 10, 2026
	 * @version 1.1
	 *
	 *
	 * @param <N>  a nodal type
	 * @see ArborealSpace
	 * @see SpatialNodal
	 */
	public static interface Query<N extends SpatialNodal> extends ArborealSpace.Query<N>, Arborus.Query<N>
	{
		@Override
		public abstract NodalSpace<N> Tree();
	}
	
	
	@Override
	public default Query<N> Query()
	{
		return () -> this;
	}
	
	@Override
	public abstract N Root();
}