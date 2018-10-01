package zeno.util.geom.transformations;

import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.algebra.linear.matrix.types.orthogonal.Identity;
import zeno.util.geom.ITransformation;

/**
 * The {@code ChainTransform} interface defines a chained transformation.
 * This allows multiple transformations to be linked sequentially in one class.
 * 
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface ChainTransform extends ITransformation
{
	/**
	 * Returns the chain of the {@code ChainTransform}.
	 * 
	 * @return  a transform chain
	 * 
	 * 
	 * @see ITransformation
	 */
	public abstract ITransformation[] Chain();
	
	
	@Override
	public default Matrix4x4 Inverse()
	{
		Matrix4x4 inv = Matrix4x4.identity();		
		inv.setOperator(Identity.Type());		
	
		for(ITransformation tform : Chain())
		{
			if(tform != null)
			{
				inv = inv.times(tform.Inverse());
			}
		}
		
		return inv;
	}

	@Override
	public default Matrix4x4 Matrix()
	{
		Matrix4x4 mat = Matrix4x4.identity();
		mat.setOperator(Identity.Type());
		
		for(ITransformation tform : Chain())
		{
			if(tform != null)
			{
				mat = tform.Matrix().times(mat);
			}
		}
		
		return mat;
	}
}