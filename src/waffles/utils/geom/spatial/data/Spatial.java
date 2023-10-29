package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Rotated;

/**
 * A {@code Spatial} object describes an affine position in space.
 * It defines an origin and size vector, and a rotation spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Axial
 */
public interface Spatial extends Axial, Rotated
{
	/**
	 * A {@code Mutable Axial} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Axial
	 */
	public static interface Mutable extends Spatial, Axial.Mutable, Rotated.Mutable
	{
		@Override
		public default Spatial.Mutable Mutator()
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