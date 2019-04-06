package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.ICuboid;
import zeno.util.geom.collidables.geometry.ISphere;

/**
 * The {@code Bounds} interface defines object bounds in n dimensions.
 * 
 * @author Zeno
 * @since Apr 06, 2019
 * @version 1.0
 */
public interface Bounds
{
	/**
	 * Returns the minimum of the {@code Bounds} object.
	 * 
	 * @return  the bounds minimum
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Minimum();
	
	/**
	 * Returns the maximum of the {@code Bounds} object.
	 * 
	 * @return  the bounds maximum
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Maximum();
	
	
	/**
	 * Returns the size of the {@code Bounds} object.
	 * 
	 * @return  the bounds size
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Size()
	{
		return Maximum().minus(Minimum());
	}
	
	/**
	 * Returns the center of the {@code Bounds} object.
	 * 
	 * @return  the bounds center
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Center()
	{
		return Minimum().plus(Maximum()).times(0.5f);
	}
	
	/**
	 * Returns the diameter of the {@code Bounds} objects.
	 * 
	 * @return  the bounds diameter
	 */
	public default float Diameter()
	{
		return Size().norm();
	}
	
	/**
	 * Returns the radius of the {@code Bounds} object.
	 * 
	 * @return  the bounds radius
	 */
	public default float Radius()
	{
		return 0.5f * Diameter();
	}
			
		
	/**
	 * Returns the dim of the {@code Bounds} object.
	 * 
	 * @return  the bounds dimension
	 */
	public default int Dimension()
	{
		return Center().Size();
	}
	
	/**
	 * Returns the ball of the {@code Bounds} object.
	 * 
	 * @return  the bounding ball
	 * 
	 * 
	 * @see ISphere
	 */
	public default ISphere Ball()
	{
		return ISphere.create(Center(), Radius());
	}
	
	/**
	 * Returns the box of the {@code Bounds} object.
	 * 
	 * @return  the bounding box
	 * 
	 * 
	 * @see ICuboid
	 */
	public default ICuboid Box()
	{
		return ICuboid.create(Center(), Size());
	}
}