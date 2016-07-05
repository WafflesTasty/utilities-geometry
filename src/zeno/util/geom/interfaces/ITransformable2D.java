package zeno.util.geom.interfaces;

import zeno.util.geom.interfaces.movement.IVantage2D;
import zeno.util.geom.interfaces.scale.IScalable2D;
import zeno.util.geom.tools.bounds.IBound2D;

/**
 * The {@code ITransformable2D} interface defines an object
 * capable of being transformed in 2D space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @see IScalable2D
 * @see IVantage2D
 * @see IBound2D
 */
public interface ITransformable2D extends IBound2D, IScalable2D, IVantage2D
{
	@Override
	public default float XMin()
	{
		return Transform().XMin();
	}
	
	@Override
	public default float XMax()
	{
		return Transform().XMax();
	}
	
	@Override
	public default float YMin()
	{
		return Transform().YMin();
	}
	
	@Override
	public default float YMax()
	{
		return Transform().YMax();
	}
}