package zeno.util.geom.transformables.projective;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.transformables.affine.IScalable3D;
import zeno.util.geom.transformables.affine.IVantage3D;

/**
 * The {@code IProjector3D} interface defines an object
 * operating like a three-dimensional pinhole camera.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see IProjectable3D
 * @see IScalable3D
 * @see IVantage3D
 * @see IProjector
 */
public interface IProjector3D extends IProjector, IProjectable3D, IVantage3D, IScalable3D
{
	@Override
	public default Matrix3x3 Basis()
	{
		return (Matrix3x3) Camera().Basis();
	}
	
	@Override
	public default Vector3 Oculus()
	{
		return (Vector3) Camera().Oculus();
	}
	
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Camera().Origin();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Camera().Size();
	}
}