package waffles.utils.geom.spaces.arboreal.nodal;

import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.spaces.arboreal.nodal.tform.OffsetTransform;
import waffles.utils.tools.collections.Iterables;

/**
 * A {@code TransformNodal} defines a {@code SpatialNodal} as a {@code Geometrical}.
 *
 * @author Waffles
 * @since May 17, 2026
 * @version 1.1
 *
 * 
 * @see SpatialNodal
 * @see Geometrical
 */
public interface TransformNodal extends Geometrical, SpatialNodal
{	
	/**
	 * Iterates the children of the {@code TransformNodal}.
	 * 
	 * @return  a child iterable
	 * 
	 * 
	 * @ee Iterable
	 */
	public default <N extends TransformNodal> Iterable<N> Children()
	{
		return Iterables.of(Arch().Children());
	}

	
	@Override
	public abstract OffsetTransform Transform();
		
	@Override
	public default TransformNodal Parent()
	{
		return (TransformNodal) Arch().Parent();
	}
}