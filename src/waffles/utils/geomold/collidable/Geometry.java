package waffles.utils.geom.collidable;

import waffles.utils.geom.Collidable;
import waffles.utils.geom.bounds.Boundable;

/**
 * The {@code Geometry} interface is the base for bounded n-dimensional shapes.
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