package zeno.util.geom.transformables;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.transformables.affine.IScalable2D;
import zeno.util.geom.transformables.affine.IVantage2D;
import zeno.util.geom.transformables.projective.IProjectable2D;
import zeno.util.geom.utilities.spin.Spin2D;

/**
 * The {@code IProjector2D} interface defines an object
 * operating like a two-dimensional pinhole camera.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see IProjectable2D
 * @see IScalable2D
 * @see IVantage2D
 * @see IProjector
 */
public interface IProjector2D extends IProjector, IProjectable2D, IVantage2D, IScalable2D
{
	@Override
	public default Spin2D Spin()
	{
		return (Spin2D) Camera().Spin();
	}
	
	@Override
	public default Vector2 Oculus()
	{
		return (Vector2) Camera().Oculus();
	}
	
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Camera().Origin();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Camera().Size();
	}
}