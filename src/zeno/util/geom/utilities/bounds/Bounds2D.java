package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom._deprecated.collideables.geometry.planar.shapes.Circle;
import zeno.util.geom._deprecated.collideables.geometry.planar.shapes.Rectangle;

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
	 * Returns the minimum x-coördinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds minimum x
	 */
	public abstract float XMin();
	
	/**
	 * Returns the maximum x-coördinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds maximum x
	 */
	public abstract float XMax();
	
	/**
	 * Returns the minimum y-coördinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds minimum y
	 */
	public abstract float YMin();
	
	/**
	 * Returns the maximum y-coördinate of the {@code Bounds2D}.
	 * 
	 * @return  the bounds maximum y
	 */
	public abstract float YMax();
	
	
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
	
		
	@Override
	public default Circle Sphere()
	{
		return new Circle(Center(), Radius());
	}
	
	@Override
	public default Rectangle Box()
	{
		return new Rectangle(Center(), Size());
	}
		
	@Override
	public default Vector2 Minimum()
	{
		return new Vector2(XMin(), YMin());
	}
	
	@Override
	public default Vector2 Maximum()
	{
		return new Vector2(XMax(), YMax());
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