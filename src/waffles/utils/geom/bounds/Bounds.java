package waffles.utils.geom.bounds;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.axial.spheroid.HyperSphere;
import waffles.utils.geom.utilities.Geometries;

/**
 * The {@code Bounds} interface defines the boundary of a {@code Bounded} object.
 * It contains all the data to generate a bounding box and bounding sphere.
 * Make sure to override either minimum/maximum or center/size,
 * and either radius or diameter.
 * 
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 */
public interface Bounds
{
	/**
	 * The {@code Type} enum defines all types of bounding volumes.
	 *
	 * @author Waffles
	 * @since 20 Apr 2024
	 * @version 1.1
	 */
	public static enum Type
	{
		/**
		 * A bounding ball.
		 */
		BALL,
		/**
		 * A bounding box.
		 */
		BOX;
	}
	
	
	/**
	 * Returns the minimum of the {@code Bounds}.
	 * 
	 * @return  a minimum vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Minimum()
	{
		return Center().plus(Size().times(-0.5f));
	}
	
	/**
	 * Returns the maximum of the {@code Bounds}.
	 * 
	 * @return  a maximum vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Maximum()
	{
		return Center().plus(Size().times(0.5f));
	}

	/**
	 * Returns the center of the {@code Bounds}.
	 * 
	 * @return  a center vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Center()
	{
		return Minimum().plus(Maximum()).times(0.5f);
	}
	
	/**
	 * Returns the size of the {@code Bounds}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public default Vector Size()
	{
		return Maximum().minus(Minimum());
	}
	

	/**
	 * Returns the dimension of the {@code Bounds}.
	 * 
	 * @return  a space dimension
	 */
	public default int Dimension()
	{
		return Center().Size();
	}
	
	/**
	 * Returns the diameter of the {@code Bounds}.
	 * 
	 * @return  a bounds diameter
	 */
	public default float Diameter()
	{
		return Radius() * 2;
	}
	
	/**
	 * Returns the radius of the {@code Bounds}.
	 * 
	 * @return  a bounds radius
	 */
	public default float Radius()
	{
		return Diameter() / 2;
	}
			
			
	/**
	 * Returns a {@code Bounds} sphere.
	 * 
	 * @return  a bounding sphere
	 * 
	 * 
	 * @see HyperSphere
	 */
	public default HyperSphere Ball()
	{
		return Geometries.Sphere(Center(), Radius());
	}
	
	/**
	 * Returns a {@code Bounds} box.
	 * 
	 * @return  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public default HyperCuboid Box()
	{
		return Geometries.Cuboid(Center(), Size());
	}
}