package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.transformables.affine.IScalable3D;
import zeno.util.geom.transformables.affine.IVantage3D;
import zeno.util.geom.utilities.bounds.Bounded3D;
import zeno.util.tools.Floats;

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
 * @see Bounded3D
 */
public interface ITransformable3D extends ITransformable, IVantage3D, IScalable3D, Bounded3D
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

	
	@Override
	public default float XMin()
	{
		float ox = X();
		float ux = Up().X();
		float rx = Right().X();
		float fx = Forward().X();
		float sx = Width();
		
		ux = ux * sx / 2;
		rx = rx * sx / 2;
		fx = fx * sx / 2;
		
		return ox + Floats.min(-ux, -rx, -fx, ux, rx, fx);
	}
	
	@Override
	public default float XMax()
	{
		float ox = X();
		float ux = Up().X();
		float rx = Right().X();
		float fx = Forward().X();
		float sx = Width();
		
		ux = ux * sx / 2;
		rx = rx * sx / 2;
		fx = fx * sx / 2;
		
		return ox + Floats.max(-ux, -rx, -fx, ux, rx, fx);
	}
	
	@Override
	public default float YMin()
	{
		float oy = Y();
		float uy = Up().Y();
		float ry = Right().Y();
		float fy = Forward().Y();
		float sy = Height();
		
		uy = uy * sy / 2;
		ry = ry * sy / 2;
		fy = fy * sy / 2;
		
		return oy + Floats.min(-uy, -ry, -fy, uy, ry, fy);
	}
	
	@Override
	public default float YMax()
	{
		float oy = Y();
		float uy = Up().Y();
		float ry = Right().Y();
		float fy = Forward().Y();
		float sy = Height();
		
		uy = uy * sy / 2;
		ry = ry * sy / 2;
		fy = fy * sy / 2;
		
		return oy + Floats.max(-uy, -ry, -fy, uy, ry, fy);
	}
	
	@Override
	public default float ZMin()
	{
		float oz = Z();
		float uz = Up().Z();
		float rz = Right().Z();
		float fz = Forward().Z();
		float sz = Depth();
		
		uz = uz * sz / 2;
		rz = rz * sz / 2;
		fz = fz * sz / 2;
		
		return oz + Floats.min(-uz, -rz, -fz, uz, rz, fz);
	}
	
	@Override
	public default float ZMax()
	{
		float oz = Z();
		float uz = Up().Z();
		float rz = Right().Z();
		float fz = Forward().Z();
		float sz = Depth();
		
		uz = uz * sz / 2;
		rz = rz * sz / 2;
		fz = fz * sz / 2;
		
		return oz + Floats.max(-uz, -rz, -fz, uz, rz, fz);
	}
}