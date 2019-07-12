package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.generic.ICuboid;
import zeno.util.geom.collidables.geometry.generic.ISphere;
import zeno.util.geom.utilities.Generate;

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
	public default Vector Minimum()
	{
		return Center().minus(Size().times(0.5f));
	}
	
	/**
	 * Returns the maximum of the {@code Bounds} object.
	 * 
	 * @return  the bounds maximum
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Maximum()
	{
		return Center().plus(Size().times(0.5f));
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
	 * Returns the dimension of the {@code Bounds} object.
	 * 
	 * @return  the bounds dimension
	 */
	public default int Dimension()
	{
		return Center().Size();
	}
	
	/**
	 * Returns the diameter of the {@code Bounds} objects.
	 * 
	 * @return  the bounds diameter
	 */
	public default float Diameter()
	{
		return Radius() * 2;
	}
	
	/**
	 * Returns the radius of the {@code Bounds} object.
	 * 
	 * @return  the bounds radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
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
		return Generate.sphere(Center(), Radius());
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
		return Generate.cuboid(Center(), Size());
	}
}