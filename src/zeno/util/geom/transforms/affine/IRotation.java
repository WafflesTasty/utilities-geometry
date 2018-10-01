package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.algebra.linear.matrix.types.orthogonal.Orthogonal;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformation;

/**
 * The {@code IRotation} interface defines an affine rotation.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface IRotation extends ITransformation
{
	/**
	 * Returns the up vector of the {@code IRotation}.
	 * 
	 * @return  an up vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Up()
	{
		return Basis().Column(1);
	}
	
	/**
	 * Returns the right vector of the {@code IRotation}.
	 * 
	 * @return  a right vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Right()
	{
		return Basis().Column(0);
	}
	
	/**
	 * Returns the forward vector of the {@code IRotation}.
	 * 
	 * @return  a forward vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default Vector3 Forward()
	{
		return Basis().Column(2);
	}

	/**
	 * Returns the basis of the {@code IRotation}.
	 * 
	 * @return  a rotation basis
	 * @see Matrix3x3
	 */
	public abstract Matrix3x3 Basis();
	

	@Override
	public default Matrix4x4 Inverse()
	{
		Matrix4x4 inv = Matrices.resize(Basis().transpose(), 4, 4);
		inv.setOperator(Orthogonal.Type());
		inv.set(1f, 3, 3);
		return inv;
	}

	@Override
	public default Matrix4x4 Matrix()
	{
		Matrix4x4 mat = Matrices.resize(Basis(), 4, 4);
		mat.setOperator(Orthogonal.Type());
		mat.set(1f, 3, 3);
		return mat;
	}
}