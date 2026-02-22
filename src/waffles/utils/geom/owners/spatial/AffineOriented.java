package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical;
import waffles.utils.geom.owners.collision.CLSAffineOriented;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.Oriented;
import waffles.utils.geom.spatial.maps.global.RadialMap;

/**
 * An {@code AffineOriented} defines an n-dimensional {@code Oriented Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Oriented
 */
public interface AffineOriented extends Oriented, Geometrical
{
	@Override
	public default Point Origin()
	{
		return Oriented.super.Origin();
	}
	
	@Override
	public default Collision Collision()
	{
		return new CLSAffineOriented(this);
	}
	
	@Override
	public abstract RadialMap.Mutable Transform();

	@Override
	public default int Dimension()
	{
		return Geometrical.super.Dimension();
	}
}