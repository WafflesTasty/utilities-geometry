package zeno.util.geom.collidables.geometry.generic;

/**
 * The {@code ICube} interface defines the collision operations for cube geometry.
 * 
 * @author Zeno
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
	 * @return  the cube's length
	 */
	public default float Length()
	{
		return Size().get(0);
	}
}