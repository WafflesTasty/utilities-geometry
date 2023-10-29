package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.spin.Spin3D;
import waffles.utils.geom.spatial.types.Projectable3D;
import waffles.utils.geom.spatial.types.Scalable3D;
import waffles.utils.geom.spatial.types.Vantage3D;

/**
 * An {@code Projector3D} object observes a projection of a three-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable3D
 * @see Scalable3D
 * @see Vantage3D
 * @see Projector
 */
public interface Projector3D extends Projector, Projectable3D, Vantage3D, Scalable3D
{
	@Override
	public default Spin3D Spin()
	{
		return (Spin3D) Camera().Spin();
	}
	
	@Override
	public default Vector3 Oculus()
	{
		return (Vector3) Camera().Oculus();
	}
	
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Camera().Origin();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Camera().Size();
	}
}