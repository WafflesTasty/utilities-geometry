package waffles.utils.geom.collidable;

import waffles.utils.geom.maps.AffineMap;
import waffles.utils.geom.spatial.owners.Adjustable;

/**
 * A {@code Shapeable} object defines a fully adjustable, n-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Adjustable
 */
public interface Shapeable extends Adjustable, Geometrical
{
	@Override
	public abstract AffineMap Transform();
	
	@Override
	public default AffineMap Spatial()
	{
		return Transform();
	}
}