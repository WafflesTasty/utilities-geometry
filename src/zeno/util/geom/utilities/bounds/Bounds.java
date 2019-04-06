package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom._deprecated.collideables.geometry.ICuboid;
import zeno.util.geom._deprecated.collideables.geometry.ISphere;
import zeno.util.geom._deprecated.collideables.geometry.higher.shapes.NCuboid;
import zeno.util.geom._deprecated.collideables.geometry.higher.shapes.NSphere;

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
	 * Returns the radius of the {@code Bounds} object.
	 * 
	 * @return  the bounds radius
	 */
	public default float Radius()
	{
		return 0.5f * Size().norm();
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
	 * Returns the sphere of the {@code Bounds} object.
	 * 
	 * @return  the bounding sphere
	 * 
	 * 
	 * @see ISphere
	 */
	public default ISphere Sphere()
	{
		return new NSphere(Center(), Radius());
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
		return new NCuboid(Center(), Size());
	}
}