package zeno.util.geom.transformables;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.transformables.affine.IScalable2D;
import zeno.util.geom.transformables.affine.IVantage2D;
import zeno.util.geom.utilities.spin.Spin2D;

/**
 * The {@code IAdjustable2D} interface defines an object
 * capable of being fully adjusted in two-dimensional affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see IAdjustable
 * @see IScalable2D
 * @see IVantage2D
 */
public interface IAdjustable2D extends IAdjustable, IScalable2D, IVantage2D
{
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Transform().Spin();
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