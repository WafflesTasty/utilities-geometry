package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.transformables.affine.IScalable3D;
import zeno.util.geom.transformables.affine.IVantage3D;
import zeno.util.geom.utilities.bounds.Bounds3D;
import zeno.util.geom.utilities.bounds.IBounded3D;
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
 * @see IBounded3D
 */
public interface ITransformable3D extends ITransformable, IVantage3D, IScalable3D, IBounded3D
{
	/**
	 * The {@code TBounds3D} class defines the bounds of a {@code ITransformable3D}.
	 *
	 * @author Zeno
	 * @since Apr 6, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see ITransformable3D
	 * @see Bounds3D
	 */
	public static class TBounds3D implements Bounds3D
	{
		private ITransformable3D source;
		
		/**
		 * Creates a new {@code ITransformable3D Bounds}.
		 * 
		 * @param source  a transformable source
		 * 
		 * 
		 * @see ITransformable3D
		 */
		public TBounds3D(ITransformable3D source)
		{
			this.source = source;
		}
		
		
		@Override
		public float XMin()
		{
			float ox = source.X();
			float ux = source.Up().X();
			float rx = source.Right().X();
			float fx = source.Forward().X();
			float sx = source.Width();
			
			ux = ux * sx / 2;
			rx = rx * sx / 2;
			fx = fx * sx / 2;
			
			return ox + Floats.min(-ux, -rx, -fx, ux, rx, fx);
		}

		@Override
		public float XMax()
		{
			float ox = source.X();
			float ux = source.Up().X();
			float rx = source.Right().X();
			float fx = source.Forward().X();
			float sx = source.Width();
			
			ux = ux * sx / 2;
			rx = rx * sx / 2;
			fx = fx * sx / 2;
			
			return ox + Floats.max(-ux, -rx, -fx, ux, rx, fx);
		}

		@Override
		public float YMin()
		{
			float oy = source.Y();
			float uy = source.Up().Y();
			float ry = source.Right().Y();
			float fy = source.Forward().Y();
			float sy = source.Height();
			
			uy = uy * sy / 2;
			ry = ry * sy / 2;
			fy = fy * sy / 2;
			
			return oy + Floats.min(-uy, -ry, -fy, uy, ry, fy);
		}

		@Override
		public float YMax()
		{
			float oy = source.Y();
			float uy = source.Up().Y();
			float ry = source.Right().Y();
			float fy = source.Forward().Y();
			float sy = source.Height();
			
			uy = uy * sy / 2;
			ry = ry * sy / 2;
			fy = fy * sy / 2;
			
			return oy + Floats.max(-uy, -ry, -fy, uy, ry, fy);
		}
		
		@Override
		public float ZMin()
		{
			float oz = source.Z();
			float uz = source.Up().Z();
			float rz = source.Right().Z();
			float fz = source.Forward().Z();
			float sz = source.Depth();
			
			uz = uz * sz / 2;
			rz = rz * sz / 2;
			fz = fz * sz / 2;
			
			return oz + Floats.min(-uz, -rz, -fz, uz, rz, fz);
		}
		
		@Override
		public float ZMax()
		{
			float oz = source.Z();
			float uz = source.Up().Z();
			float rz = source.Right().Z();
			float fz = source.Forward().Z();
			float sz = source.Depth();
			
			uz = uz * sz / 2;
			rz = rz * sz / 2;
			fz = fz * sz / 2;
			
			return oz + Floats.max(-uz, -rz, -fz, uz, rz, fz);
		}
	}

	
	@Override
	public default Bounds3D Bounds()
	{
		return new TBounds3D(this);
	}
	
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