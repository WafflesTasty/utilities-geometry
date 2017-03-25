package zeno.util.geom.tforms.types;

import zeno.util.geom.tforms.types.movement.IVantage2D;
import zeno.util.geom.tforms.types.scale.IScalable2D;
import zeno.util.geom.tools.bounds.Bounded2D;

/**
 * The {@code ITransformable2D} interface defines an object
 * capable of being transformed in 2D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see IScalable2D
 * @see IVantage2D
 * @see Bounded2D
 */
public interface ITransformable2D extends Bounded2D, IScalable2D, IVantage2D
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