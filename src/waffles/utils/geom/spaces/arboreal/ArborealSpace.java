package waffles.utils.geom.spaces.arboreal;

import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.arboreal.nodal.SpatialNodal;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;

/**
 * An {@code ArborealSpace} defines an {@code Arboreal} as a {@code Bounded Space}.
 *
 * @author Waffles
 * @since May 10, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Arboreal
 * @see Bounded
 * @see Space
 */
public interface ArborealSpace<O> extends Bounded, Arboreal, Space<O>
{
	/**
	 * An {@code ArborealSpace.Query} defines queries for an {@code ArborealSpace}.
	 *
	 * @author Waffles
	 * @since May 10, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see Arboreal
	 * @see Space
	 */
	public static interface Query<O> extends Arboreal.Query<O>, Space.Query<O>
	{
		/**
		 * Returns the tree of the {@code ArborealSpace}.
		 * 
		 * @return  a parent tree
		 * 
		 * 
		 * @see ArborealSpace
		 */
		public abstract ArborealSpace<O> Tree();
	}
	
	
	@Override
	public abstract SpatialNodal Root();
	
	@Override
	public abstract Query<O> Query();
	
	@Override
	public default Bounds Bounds()
	{
		return Root().Bounds();
	}
		
	@Override
	public default int Dimension()
	{
		return Root().Dimension();
	}
}