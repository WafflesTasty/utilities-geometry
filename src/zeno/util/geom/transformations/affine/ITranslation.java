package zeno.util.geom.transformations.affine;

import zeno.util.algebra.linear.matrix.fixed.Matrix4x4;
import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformation;

/**
 * The {@code ITranslation} interface defines an affine translation.
 *
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface ITranslation extends ITransformation
{
	/**
	 * Returns the origin of the {ITranslation}.
	 * 
	 * @return  an origin vector
	 * 
	 * 
	 * @see Vector3
	 */
	public abstract Vector3 Origin();	
	
	
	@Override
	public default Matrix4x4 Inverse()
	{
		Vector3 o = Origin();
		
		Matrix4x4 inv = Matrix4x4.identity();
		for(int r = 0; r < 3; r++)
		{
			inv.set(-o.get(r), r, 3);
		}

		return inv;
	}

	@Override
	public default Matrix4x4 Matrix()
	{
		Vector3 o = Origin();
		
		Matrix4x4 mat = Matrix4x4.identity();
		for(int r = 0; r < 3; r++)
		{
			mat.set(o.get(r), r, 3);
		}

		return mat;
	}
}