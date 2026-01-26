package waffles.utils.geom.shapes;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.owners.Boundable;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;

/**
 * A {@code Geometry} is a well-defined, boundable collidable shape.
 * 
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see Positioned
 * @see Collidable
 * @see Boundable
 */
public interface Geometry extends Collidable, Boundable, Positioned
{	
	@Override
	public default int Dimension()
	{
		return Bounds().Dimension();
	}
	
	@Override
	public default Point Origin()
	{
		return Bounds().Origin();
	}
}