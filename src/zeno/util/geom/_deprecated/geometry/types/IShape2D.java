package zeno.util.geom._deprecated.geometry.types;

import zeno.util.algebra.tensors.vectors.fixed.Vector2;
import zeno.util.geom._deprecated.geometry.IShape;

/**
 * The {@code IShape2D} interface defines a two-dimensional convex shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry2D
 * @see IShape
 */
public interface IShape2D extends IShape, IGeometry2D
{
	/**
	 * Returns the center x of the {@code IShape2D}.
	 * 
	 * @return  the shape's center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code IShape2D}.
	 * 
	 * @return  the shape's center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
		
	/**
	 * Returns the width of the {@code IShape2D}.
	 * 
	 * @return  the shape's width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code IShape2D}.
	 * 
	 * @return  the shape's height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
		
	@Override
	public default float XMin()
	{
		return X() - Width() / 2;
	}

	@Override
	public default float XMax()
	{
		return X() + Width() / 2;
	}

	@Override
	public default float YMin()
	{
		return Y() - Height() / 2;
	}

	@Override
	public default float YMax()
	{
		return Y() + Height() / 2;
	}

	
	@Override
	public abstract Vector2 Center();

	@Override
	public abstract Vector2 Size();
}