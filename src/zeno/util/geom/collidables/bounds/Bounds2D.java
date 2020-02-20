package zeno.util.geom.collidables.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.geometry.planar.Circle;
import zeno.util.geom.collidables.geometry.planar.Rectangle;

/**
 * The {@code Bounds2D} interface defines object bounds in two dimensions.
 *
 * @author Zeno
 * @since Apr 06, 2019
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public interface Bounds2D extends Bounds
{	
	/**
	 * Returns the center x of the {@code Bounds2D}.
	 * 
	 * @return  the bounds center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code Bounds2D}.
	 * 
	 * @return  the bounds center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
		
	/**
	 * Returns the width of the {@code Bounds2D}.
	 * 
	 * @return  the bounds width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounds2D}.
	 * 
	 * @return  the bounds height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	
	/**
	 * Returns the minimum x-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds minimum x
	 */
	public default float XMin()
	{
		return Minimum().X();
	}
	
	/**
	 * Returns the maximum x-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds maximum x
	 */
	public default float XMax()
	{
		return Maximum().X();
	}
	
	/**
	 * Returns the minimum y-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds minimum y
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}
	
	/**
	 * Returns the maximum y-coordinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds maximum y
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