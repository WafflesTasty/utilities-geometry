package zeno.util.geom.transformables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformables.affine.IScalable;
import zeno.util.geom.transformables.affine.IVantage;
import zeno.util.geom.transformables.projective.IProjectable;
import zeno.util.geom.transforms.Camera;

/**
 * The {@code IProjector} interface defines an object
 * operating like a generalized pinhole camera.
 *
 * @author Zeno
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see ITransformable
 * @see IProjectable
 * @see IScalable
 * @see IVantage
 */
public interface IProjector extends IProjectable, IScalable, ITransformable, IVantage
{
	/**
	 * Returns the camera of the {@code IProjector}.
	 * 
	 * @return  a projection camera
	 * 
	 * 
	 * @see Camera
	 */
	public abstract Camera Camera();
	
	
	@Override
	public default void projectTo(Vector o)
	{
		Camera().setOculus(o);
	}
	
	@Override
	public default void rotateTo(Matrix b)
	{
		Camera().setBasis(b);
	}
	
	@Override
	public default void scaleTo(Vector s)
	{
		Camera().setSize(s);
	}
	
	@Override
	public default void moveTo(Vector o)
	{
		Camera().setOrigin(o);
	}
	
			
	@Override
	public default Vector Origin()
	{
		return Camera().Origin();
	}

	@Override
	public default Vector Oculus()
	{
		return Camera().Oculus();
	}
	
	@Override
	public default Matrix Basis()
	{
		return Camera().Basis();
	}
	
	@Override
	public default Vector Size()
	{
		return Camera().Size();
	}
}