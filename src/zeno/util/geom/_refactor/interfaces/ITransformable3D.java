package zeno.util.geom._refactor.interfaces;

import zeno.util.geom._refactor.interfaces.movement.IVantage3D;
import zeno.util.geom._refactor.interfaces.scale.IScalable3D;
import zeno.util.geom.tools.IBounds3D;

/**
 * The {@code ITransformable3D} interface defines an object
 * capable of being transformed in 3D space.
 *
 * @since Apr 22, 2016
 * @author Zeno
 * 
 * @see IScalable3D
 * @see IVantage3D
 * @see IBounds3D
 */
public interface ITransformable3D extends IBounds3D, IScalable3D, IVantage3D
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

	@Override
	public default float ZMin()
	{
		return Transform().ZMin();
	}
	
	@Override
	public default float ZMax()
	{
		return Transform().ZMax();
	}
}