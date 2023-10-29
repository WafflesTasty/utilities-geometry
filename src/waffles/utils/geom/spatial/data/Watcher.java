package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Projected;

/**
 * A {@code Watcher} object describes an affine viewpoint in space.
 * It defines an origin and size vector, rotation spin, and projection oculus.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 *
 * 
 * @see Projected
 * @see Spatial
 */
public interface Watcher extends Spatial, Projected
{
	/**
	 * A {@code Mutable Watcher} can change its own origin and size.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Projected
	 * @see Spatial
	 * @see Watcher
	 */
	public static interface Mutable extends Watcher, Spatial.Mutable, Projected.Mutable
	{
		@Override
		public default Watcher.Mutable Mutator()
		{
			return this;
		}
	}
	
	
	@Override
	public default Mutable Mutator()
	{
		if(this instanceof Mutable)
		{
			return (Mutable) this;
		}
		
		return null;
	}
}