package waffles.utils.geom.collidable.axial.cuboid;

/**
 * An {HyperCube} defines axis-aligned cube geometry with a center and length.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see HyperCuboid
 */
public interface HyperCube extends HyperCuboid
{		
	/**
	 * Returns the length of the {@code HyperCube}.
	 * 
	 * @return  a cube length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}