package waffles.utils.geom.shapes;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.spatial.bounds.owners.Boundable;

/**
 * A {@code Geometry} is a well-defined, boundable collidable shape.
 * 
 * @author Waffles
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see Collidable
 * @see Boundable
 */
public interface Geometry extends Collidable, Boundable
{		
	@Override
	public default int Dimension()
	{
		return Bounds().Dimension();
	}
}