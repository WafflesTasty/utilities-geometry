package waffles.utils.geom.collidable.axial.cuboid;

/**
 * An {ICube} defines axis-aligned cube geometry with a center and length.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see ICuboid
 */
public interface ICube extends ICuboid
{		
	/**
	 * Returns the length of the {@code ICube}.
	 * 
	 * @return  a cube length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}