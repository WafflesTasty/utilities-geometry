package waffles.utils.geom.shapes;

import waffles.utils.geom.Collideable3D;
import waffles.utils.geom.spatial.bounds.Bounds3D;
import waffles.utils.geom.spatial.bounds.owners.Bounded3D;

/**
 * A {@code Geometrical3D} object defines a three-dimensional {@code Geometrical}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Collideable3D
 * @see Bounded3D
 */
public interface Geometrical3D extends Bounded3D, Collideable3D, Geometrical
{
	@Override
	public default Bounds3D Bounds()
	{
		return Shape().Bounds(Transform());
	}
	
	@Override
	public abstract Geometry3D Shape();
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}