package waffles.utils.geom.spatial.bounds;

import waffles.utils.alg.lin.measure.vector.fixed.Vector2;
import waffles.utils.geomold.collidable.axial.cuboid.Rectangle;
import waffles.utils.geomold.collidable.axial.spheroid.Circle;

/**
 * The {@code Bounds} interface defines {@code Bounds} in two-dimensional space.
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
	 * Returns the origin x of the {@code Bounds2D}.
	 * 
	 * @return  an origin x
	 */
	public default float X()
	{
		return Origin().X();
	}
	
	/**
	 * Returns the origin y of the {@code Bounds2D}.
	 * 
	 * @return  an origin y
	 */
	public default float Y()
	{
		return Origin().Y();
	}
		
	/**
	 * Returns the width of the {@code Bounds2D}.
	 * 
	 * @return  a width scale
	 */
	public default float Width()
	{
		return Scale().X();
	}
	
	/**
	 * Returns the height of the {@code Bounds2D}.
	 * 
	 * @return  a height scale
	 */
	public default float Height()
	{
		return Scale().Y();
	}
	
	
	/**
	 * Returns the minimum x of the {@code Bounds2D}.
	 * 
	 * @return  a minimum x
	 */
	public default float XMin()
	{
		return Minimum().X();
	}
	
	/**
	 * Returns the maximum x of the {@code Bounds2D}.
	 * 
	 * @return  a maximum x
	 */
	public default float XMax()
	{
		return Maximum().X();
	}
	
	/**
	 * Returns the minimum y of the {@code Bounds2D}.
	 * 
	 * @return  a minimum y
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}
	
	/**
	 * Returns the maximum y of the {@code Bounds2D}.
	 * 
	 * @return  a maximum y
	 */
	public default float YMax()
	{
		return Maximum().Y();
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
	public default Vector2 Origin()
	{
		return (Vector2) Bounds.super.Origin();
	}
	
	@Override
	public default Vector2 Scale()
	{
		return (Vector2) Bounds.super.Scale();
	}

	@Override
	public default Rectangle Box()
	{
		return (Rectangle) Bounds.super.Box();
	}

	@Override
	public default Circle Orb()
	{
		return (Circle) Bounds.super.Orb();
	}
}