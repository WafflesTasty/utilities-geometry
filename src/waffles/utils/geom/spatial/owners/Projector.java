package waffles.utils.geom.spatial.owners;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.maps.project.Camera;
import waffles.utils.geom.spatial.owners.types.Projectable;
import waffles.utils.geom.spatial.owners.types.Scalable;
import waffles.utils.geom.spatial.owners.types.Vantage;
import waffles.utils.geom.spatial.spin.Spin;

/**
 * An {@code Projector} object observes a projection of a vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Projectable
 * @see Scalable
 * @see Vantage
 */
public interface Projector extends Projectable, Scalable, Vantage
{
	/**
	 * Returns a {@code Camera} source.
	 * 
	 * @return  a camera source
	 * 
	 * 
	 * @see Camera
	 */
	public abstract Camera Camera();
	
	
	@Override
	public default void rotateTo(Spin s)
	{
		Camera().setSpin(s);
	}
	
	@Override
	public default void projectTo(Vector o)
	{
		Camera().setOculus(o);
	}
		
	@Override
	public default void scaleTo(Vector s)
	{
		Camera().setSize(s);
	}
	
	@Override
	public default void moveTo(Vector o)
	{
		Camera().setOrigin(o);
	}
	
	
	@Override
	public default Spin Spin()
	{
		return Camera().Spin();
	}
			
	@Override
	public default Vector Origin()
	{
		return Camera().Origin();
	}

	@Override
	public default Vector Oculus()
	{
		return Camera().Oculus();
	}
		
	@Override
	public default Vector Size()
	{
		return Camera().Size();
	}
}