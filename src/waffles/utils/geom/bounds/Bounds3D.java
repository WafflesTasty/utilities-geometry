package waffles.utils.geom.bounds;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.collidable.axial.cuboid.Cuboid;
import waffles.utils.geom.collidable.axial.spheroid.Sphere;

/**
 * The {@code Bounds2D} interface defines the boundary of a three-dimensional {@code Bounded} object.
 *
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public interface Bounds3D extends Bounds
{		
	/**
	 * Returns the x-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a center x-coordinate
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the y-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a center y-coordinate
	 */
	public default float Y()
	{
		return Center().Y();
	}
	
	/**
	 * Returns the z-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a center z-coordinate
	 */
	public default float Z()
	{
		return Center().Z();
	}
		
	/**
	 * Returns the width of the {@code Bounds3D}.
	 * 
	 * @return  a width value
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounds3D}.
	 * 
	 * @return  a height value
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the depth of the {@code Bounds3D}.
	 * 
	 * @return  a depth value
	 */
	public default float Depth()
	{
		return Size().Z();
	}
	
	
	/**
	 * Returns the minimum x-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a minimum x-coordinate
	 */
	public default float XMin()
	{
		return Minimum().X();
	}
	
	/**
	 * Returns the maximum x-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a maximum x-coordinate
	 */
	public default float XMax()
	{
		return Maximum().X();
	}
	
	/**
	 * Returns the minimum y-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a minimum y-coordinate
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}
	
	/**
	 * Returns the maximum y-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a maximum y-coordinate
	 */
	public default float YMax()
	{
		return Maximum().Y();
	}
	
	/**
	 * Returns the minimum z-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a minimum z-coordinate
	 */
	public default float ZMin()
	{
		return Minimum().Z();
	}
	
	/**
	 * Returns the maximum z-coordinate of the {@code Bounds3D}.
	 * 
	 * @return  a maximum z-coordinate
	 */
	public default float ZMax()
	{
		return Maximum().Z();
	}
	
	
	@Override
	public default Cuboid Box()
	{
		return (Cuboid) Bounds.super.Box();
	}
		
	@Override
	public default Sphere Ball()
	{
		return (Sphere) Bounds.super.Ball();
	}
		
	@Override
	public default Vector3 Minimum()
	{
		return (Vector3) Bounds.super.Minimum();
	}
	
	@Override
	public default Vector3 Maximum()
	{
		return (Vector3) Bounds.super.Maximum();
	}
	
	@Override
	public default Vector3 Center()
	{
		return (Vector3) Bounds.super.Center();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Bounds.super.Size();
	}
}