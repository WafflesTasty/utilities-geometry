package zeno.util.geom.transformables;

import zeno.util.geom._attempt1.utilities.bounds.Bounded2D;
import zeno.util.geom.transformables.movement.IVantage2D;
import zeno.util.geom.transformables.scale.IScalable2D;

/**
 * The {@code ITransformable2D} interface defines an object
 * capable of being transformed in 2D space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IScalable2D
 * @see IVantage2D
 * @see Bounded2D
 */
public interface ITransformable2D extends Bounded2D, IScalable2D, IVantage2D
{
	
}