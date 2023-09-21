package waffles.utils.geom.spatial.owners;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.owners.types.Projectable2D;
import waffles.utils.geom.spatial.owners.types.Scalable2D;
import waffles.utils.geom.spatial.owners.types.Vantage2D;
import waffles.utils.geom.spatial.spin.Spin2D;

/**
 * An {@code Projector2D} object observes a projection of a two-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable2D
 * @see Scalable2D
 * @see Vantage2D
 * @see Projector
 */
public interface Projector2D extends Projector, Projectable2D, Vantage2D, Scalable2D
{
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Camera().Spin();
	}
	
	@Override
	public default Vector2 Oculus()
	{
		return (Vector2) Camera().Oculus();
	}
	
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Camera().Origin();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Camera().Size();
	}
}