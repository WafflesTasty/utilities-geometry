package waffles.utils.geom.utilities;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.Geometry;

/**
 * A {@code AffineOriented} object defines its own {@code Geometry}.
 *
 * @author Waffles
 * @since 18 Nov 2023
 * @version 1.1
 *
 * 
 * @see Bounded
 */
public interface Shapeable extends Bounded
{
	/**
	 * Returns the shape of the {@code AffineOriented}.
	 * 
	 * @return  an object shape
	 * 
	 * 
	 * @see Geometry
	 */
	public abstract Geometry Shape();
	
	
	@Override
	public default Bounds Bounds()
	{
		return Shape().Bounds();
	}
}