package waffles.utils.geom.collidable;

import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.collision.CLSAlignable;
import waffles.utils.geom.maps.AlignedMap;
import waffles.utils.geom.spatial.Aligned;

/**
 * An {@code Alignable} object defines an axis-aligned, n-dimensional geometric object.
 * It defines a shape, an affine transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Aligned
 */
public interface Alignable extends Aligned, Geometrical
{
	@Override
	public abstract AxialSet Shape();
	
	@Override
	public default Collision Collisions()
	{
		return new CLSAlignable(this);
	}
	
	@Override
	public abstract AlignedMap Transform();
	
	@Override
	public default AlignedMap Spatial()
	{
		return Transform();
	}
}