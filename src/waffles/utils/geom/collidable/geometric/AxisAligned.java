package waffles.utils.geom.collidable.geometric;

import waffles.utils.geom.Collision;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.axial.AxialSet;
import waffles.utils.geom.collision.CLSAlignable;
import waffles.utils.geom.spatial.Aligned;
import waffles.utils.geom.spatial.maps.axial.AxialMap;

/**
 * An {@code AxisAligned} object defines an axis-aligned, n-dimensional geometric object.
 * It defines a shape, an axial transformation, and various utility methods.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see AxisAligned
 */
public interface AxisAligned extends Aligned, Geometrical
{	
	@Override
	public default Collision Collisions()
	{
		return new CLSAlignable(this);
	}
	
	
	@Override
	public abstract AxialMap Transform();
	
	@Override
	public abstract AxialSet Shape();
}