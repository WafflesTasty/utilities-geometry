package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.transformables.affine.IMovable;
import zeno.util.geom.transformables.affine.IRotatable;
import zeno.util.geom.transformables.affine.IScalable;
import zeno.util.geom.transforms.AffineMap;

/**
 * The {@code ITransformable} interface defines an object
 * capable of being transformed in affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see IRotatable
 * @see IScalable
 * @see IMovable
 */
public interface ITransformable extends IMovable, IRotatable, IScalable
{
	/**
	 * Returns the transform of the {@code ITransformable}.
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