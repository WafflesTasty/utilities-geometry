package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformation;

/**
 * The {@code IResize} interface defines an affine scaling.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface IResize extends ITransformation
{
	/**
	 * Returns the scale of the {IResize}.
	 * 
	 * @return  a scale vector
	 * 
	 * 
	 * @see Vector3
	 */
	public abstract Vector3 Scale();
	
	
	@Override
	public default Matrix4x4 Inverse()
	{
		Vector3 s = Scale();
		
		Matrix4x4 inv = Matrix4x4.identity();
		inv.setOperator(Diagonal.Type());
		for(int i = 0; i < 3; i++)
		{
			inv.set(1f / s.get(i), i, i);
		}

		return inv;
	}

	@Override
	public default Matrix4x4 Matrix()
	{
		Vector3 s = Scale();
		
		Matrix4x4 inv = Matrix4x4.identity();
		inv.setOperator(Diagonal.Type());
		for(int i = 0; i < 3; i++)
		{
			inv.set(s.get(i), i, i);
		}

		return inv;
	}
}