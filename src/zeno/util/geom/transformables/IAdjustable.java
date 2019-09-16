package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.transformables.affine.IScalable;
import zeno.util.geom.transformables.affine.IVantage;
import zeno.util.geom.transforms.AffineMap;

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
public interface IAdjustable extends IScalable, IVantage
{
	/**
	 * Returns the transform of the {@code IAdjustable}.
	 * 
	 * @return  an affine transform
	 * 
	 * 
	 * @see AffineMap
	 */
	public abstract AffineMap Transform();
	
	
	@Override
	public default void rotateTo(Matrix b)
	{
		Transform().setBasis(b);
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
	public default Matrix Basis()
	{
		return Transform().Basis();
	}
	
	@Override
	public default Vector Size()
	{
		return Transform().Size();
	}
}