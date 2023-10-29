package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.spatial.types.Movable2D;
import waffles.utils.geom.spatial.types.Scalable2D;

/**
 * An {@code Adjustable2D} object can be axis-aligned in a two-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned
 * @see Scalable2D
 * @see Movable2D
 */
public interface Aligned2D extends Aligned, Scalable2D, Movable2D
{
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Spatial().Origin();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Spatial().Size();
	}
	

	@Override
	public default float Height()
	{
		return Scalable2D.super.Height();
	}

	@Override
	public default float Width()
	{
		return Scalable2D.super.Width();
	}
	
	
	@Override
	public default float X()
	{
		return Movable2D.super.X();
	}

	@Override
	public default float Y()
	{
		return Movable2D.super.Y();
	}
}