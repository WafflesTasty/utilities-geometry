package waffles.utils.geom.shapes;

import waffles.utils.geom.Collideable2D;
import waffles.utils.geom.spatial.bounds.Bounds2D;
import waffles.utils.geom.spatial.bounds.owners.Bounded2D;

/**
 * A {@code Geometrical2D} object defines a two-dimensional {@code Geometrical}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Collideable2D
 * @see Bounded2D
 */
public interface Geometrical2D extends Geometrical, Bounded2D, Collideable2D
{
	@Override
	public default Bounds2D Bounds()
	{
		return Shape().Bounds(Transform());
	}
	
	@Override
	public abstract Geometry2D Shape();
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}