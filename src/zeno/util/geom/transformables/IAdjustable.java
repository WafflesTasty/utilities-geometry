package zeno.util.geom.transformables;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.AffineMap;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformables.affine.IScalable;
import zeno.util.geom.transformables.affine.IVantage;
import zeno.util.geom.utilities.spin.Spin;

/**
 * The {@code IAdjustable} interface defines an object
 * capable of being fully adjusted in affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see IScalable
 * @see IVantage
 */
public interface IAdjustable extends ITransformable, IScalable, IVantage
{
	@Override
	public abstract AffineMap Transform();
	
	
	@Override
	public default void rotateTo(Spin s)
	{
		Transform().setSpin(s);
	}
		
	@Override
	public default void scaleTo(Vector s)
	{
		Transform().setSize(s);
	}
	
	@Override
	public default void moveTo(Vector o)
	{
		Transform().setOrigin(o);
	}
	
		
	@Override
	public default Vector Origin()
	{
		return Transform().Origin();
	}
		
	@Override
	public default Vector Size()
	{
		return Transform().Size();
	}
	
	@Override
	public default Spin Spin()
	{
		return Transform().Spin();
	}
}