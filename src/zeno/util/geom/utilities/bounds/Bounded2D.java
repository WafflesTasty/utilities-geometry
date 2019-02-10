package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom._deprecated.collideables.geometry.planar.shapes.Rectangle;

/**
 * The {@code Bounded2D} interface defines an object bound in two dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 * 
 * @see Bounded
 */
public interface Bounded2D extends Bounded
{		
	/**
	 * Returns the center x of the {@code Bounded2D}.
	 * 
	 * @return  the object's center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code Bounded2D}.
	 * 
	 * @return  the object's center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
		
	/**
	 * Returns the width of the {@code Bounded2D}.
	 * 
	 * @return  the object's width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounded2D}.
	 * 
	 * @return  the object's height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	
	/**
	 * Returns the minimum x-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's minimum x
	 */
	public default float XMin()
	{
		return X() - Width() / 2;
	}
	
	/**
	 * Returns the maximum x-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's maximum x
	 */
	public default float XMax()
	{
		return X() + Width() / 2;
	}
	
	/**
	 * Returns the minimum y-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's minimum y
	 */
	public default float YMin()
	{
		return Y() - Height() / 2;
	}
	
	/**
	 * Returns the maximum y-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's maximum y
	 */
	public default float YMax()
	{
		return Y() + Height() / 2;
	}

	
	@Override
	public abstract Rectangle Bounds();
	
	@Override
	public default Vector2 Center()
	{
		return Bounds().Center();
	}
	
	@Override
	public default Vector2 Size()
	{
		return Bounds().Size();
	}
}