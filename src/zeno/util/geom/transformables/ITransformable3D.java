package zeno.util.geom.transformables;

import zeno.util.geom._attempt1.utilities.bounds.Bounded3D;
import zeno.util.geom.transformables.movement.IVantage3D;
import zeno.util.geom.transformables.scale.IScalable3D;

/**
 * The {@code ITransformable3D} interface defines an object
 * capable of being transformed in 3D space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IScalable3D
 * @see IVantage3D
 * @see Bounded3D
 */
public interface ITransformable3D extends Bounded3D, IScalable3D, IVantage3D
{
	
}