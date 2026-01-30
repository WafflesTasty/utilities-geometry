package waffles.utils.geom.owners.spatial;

import waffles.utils._todo.geometric.CLSAlignable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.spatial.Aligned;
import waffles.utils.geom.spatial.maps.global.AxialMap;

/**
 * An {@code AxisAligned} defines an n-dimensional {@code Aligned Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Aligned
 */
public interface AxisAligned extends Aligned, Geometrical
{	
	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
	
	@Override
	public default Collision Collision()
	{
		return new CLSAlignable(this);
	}
	
	@Override
	public abstract AxialMap.Mutable Transform();
	
	@Override
	public abstract AxialSet Shape();
}