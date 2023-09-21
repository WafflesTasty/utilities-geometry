package waffles.utils.geom.bounds;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.collidable.axial.cuboid.Rectangle;
import waffles.utils.geom.collidable.axial.spheroid.Circle;

/**
 * The {@code Bounds2D} interface defines the boundary of a two-dimensional {@code Bounded} object.
 *
 * @author Waffles
 * @since Apr 06, 2019
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public interface Bounds2D extends Bounds
{	
	/**
	 * Returns the x-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a center x-coordinate
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the y-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a center y-coordinate
	 */
	public default float Y()
	{
		return Center().Y();
	}
		
	/**
	 * Returns the width of the {@code Bounds2D}.
	 * 
	 * @return  a width value
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounds2D}.
	 * 
	 * @return  a height value
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	
	/**
	 * Returns the minimum x-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a minimum x-coordinate
	 */
	public default float XMin()
	{
		return Minimum().X();
	}
	
	/**
	 * Returns the maximum x-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a maximum x-coordinate
	 */
	public default float XMax()
	{
		return Maximum().X();
	}
	
	/**
	 * Returns the minimum y-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a minimum y-coordinate
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}
	
	/**
	 * Returns the maximum y-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  a maximum y-coordinate
	 */
	public default float YMax()
	{
		return Maximum().Y();
	}
	
		
	@Override
	public default Circle Ball()
	{
		return (Circle) Bounds.super.Ball();
	}
	
	@Override
	public default Rectangle Box()
	{
		return (Rectangle) Bounds.super.Box();
	}
		
	@Override
	public default Vector2 Minimum()
	{
		return (Vector2) Bounds.super.Minimum();
	}
	
	@Override
	public default Vector2 Maximum()
	{
		return (Vector2) Bounds.super.Maximum();
	}
	
	@Override
	public default Vector2 Center()
	{
		return (Vector2) Bounds.super.Center();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Bounds.super.Size();
	}
}