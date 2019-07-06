package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.transformables.affine.IScalable3D;
import zeno.util.geom.transformables.affine.IVantage3D;

/**
 * The {@code ITransformable3D} interface defines an object
 * capable of being transformed in three-dimensional affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see IScalable3D
 * @see IVantage3D
 */
public interface ITransformable3D extends ITransformable, IVantage3D, IScalable3D
{
	@Override
	public default Matrix3x3 Basis()
	{
		return (Matrix3x3) Transform().Basis();
	}
	
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Transform().Origin();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Transform().Size();
	}
	

	@Override
	public default float Depth()
	{
		return IScalable3D.super.Depth();
	}

	@Override
	public default float Height()
	{
		return IScalable3D.super.Height();
	}

	@Override
	public default float Width()
	{
		return IScalable3D.super.Width();
	}
	
	
	@Override
	public default float X()
	{
		return IVantage3D.super.X();
	}

	@Override
	public default float Y()
	{
		return IVantage3D.super.Y();
	}

	@Override
	public default float Z()
	{
		return IVantage3D.super.Z();
	}
}