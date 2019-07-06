package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.fixed.Matrix2x2;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.transformables.affine.IScalable2D;
import zeno.util.geom.transformables.affine.IVantage2D;

/**
 * The {@code ITransformable} interface defines an object
 * capable of being transformed in two-dimensional affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see IScalable2D
 * @see IVantage2D
 */
public interface ITransformable2D extends ITransformable, IVantage2D, IScalable2D
{
	@Override
	public default Matrix2x2 Basis()
	{
		return (Matrix2x2) Transform().Basis();
	}
	
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Transform().Origin();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Transform().Size();
	}
	

	@Override
	public default float Height()
	{
		return IScalable2D.super.Height();
	}

	@Override
	public default float Width()
	{
		return IScalable2D.super.Width();
	}
	
	
	@Override
	public default float X()
	{
		return IVantage2D.super.X();
	}

	@Override
	public default float Y()
	{
		return IVantage2D.super.Y();
	}
}