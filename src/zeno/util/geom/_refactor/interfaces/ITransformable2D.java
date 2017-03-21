package zeno.util.geom._refactor.interfaces;

import zeno.util.geom._refactor.interfaces.movement.IVantage2D;
import zeno.util.geom._refactor.interfaces.scale.IScalable2D;
import zeno.util.geom.tools.IBounds2D;

/**
 * The {@code ITransformable2D} interface defines an object
 * capable of being transformed in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see IScalable2D
 * @see IVantage2D
 * @see IBounds2D
 */
public interface ITransformable2D extends IBounds2D, IScalable2D, IVantage2D
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