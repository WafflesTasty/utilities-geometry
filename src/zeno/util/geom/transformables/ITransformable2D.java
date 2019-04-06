package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.fixed.Matrix2x2;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.transformables.affine.IScalable2D;
import zeno.util.geom.transformables.affine.IVantage2D;
import zeno.util.geom.utilities.bounds.Bounds2D;
import zeno.util.geom.utilities.bounds.IBounded2D;
import zeno.util.tools.Floats;

/**
 * The {@code ITransformable} interface defines an object
 * capable of being transformed in two-dimensional affine space.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see IScalable2D
 * @see IVantage2D
 * @see IBounded2D
 */
public interface ITransformable2D extends ITransformable, IVantage2D, IScalable2D, IBounded2D
{
	/**
	 * The {@code TBounds2D} class defines the bounds of a {@code ITransformable2D}.
	 *
	 * @author Zeno
	 * @since Apr 6, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see ITransformable2D
	 * @see Bounds2D
	 */
	public static class TBounds2D implements Bounds2D
	{
		private ITransformable2D source;
		
		/**
		 * Creates a new {@code ITransformable2D Bounds}.
		 * 
		 * @param source  a transformable source
		 * 
		 * 
		 * @see ITransformable2D
		 */
		public TBounds2D(ITransformable2D source)
		{
			this.source = source;
		}
		
		
		@Override
		public float XMin()
		{
			float ox = source.X();
			float rx = source.Right().X();
			float fx = source.Forward().X();
			float sx = source.Width();
			
			rx = rx * sx / 2;
			fx = fx * sx / 2;
			
			return ox + Floats.min(-rx, -fx, rx, fx);
		}

		@Override
		public float XMax()
		{
			float ox = source.X();
			float rx = source.Right().X();
			float fx = source.Forward().X();
			float sx = source.Width();
			
			rx = rx * sx / 2;
			fx = fx * sx / 2;
			
			return ox + Floats.max(-rx, -fx, rx, fx);
		}

		@Override
		public float YMin()
		{
			float oy = source.Y();
			float ry = source.Right().Y();
			float fy = source.Forward().Y();
			float sy = source.Height();
			
			ry = ry * sy / 2;
			fy = fy * sy / 2;
			
			return oy + Floats.min(-ry, -fy, ry, fy);
		}

		@Override
		public float YMax()
		{
			float oy = source.Y();
			float ry = source.Right().Y();
			float fy = source.Forward().Y();
			float sy = source.Height();
			
			ry = ry * sy / 2;
			fy = fy * sy / 2;
			
			return oy + Floats.max(-ry, -fy, ry, fy);
		}
	}
	
	
	@Override
	public default Bounds2D Bounds()
	{
		return new TBounds2D(this);
	}
	
	@Override
	public default Matrix2x2 Basis()
	{
		return (Matrix2x2) Transform().Basis();
	}
	
	@Override
	public default Vector2 Origin()
	{
		return (Vector2) Transform().Origin();
	}
	
	@Override
	public default Vector2 Size()
	{
		return (Vector2) Transform().Size();
	}
	

	@Override
	public default float Height()
	{
		return IScalable2D.super.Height();
	}

	@Override
	public default float Width()
	{
		return IScalable2D.super.Width();
	}
	
	@Override
	public default float X()
	{
		return IVantage2D.super.X();
	}

	@Override
	public default float Y()
	{
		return IVantage2D.super.Y();
	}
}