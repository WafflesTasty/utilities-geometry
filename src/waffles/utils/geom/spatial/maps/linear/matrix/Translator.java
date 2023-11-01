package waffles.utils.geom.spatial.maps.linear.matrix;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.banded.upper.UpperTriangular;
import waffles.utils.algebra.elements.linear.tensor.Tensor;
import waffles.utils.geom.spatial.maps.linear.matrix.ops.TranslatorAddition;
import waffles.utils.geom.spatial.maps.linear.matrix.ops.TranslatorDotProduct;
import waffles.utils.geom.spatial.maps.linear.matrix.ops.TranslatorLProduct;
import waffles.utils.geom.spatial.maps.linear.matrix.ops.TranslatorRProduct;
import waffles.utils.geom.spatial.maps.linear.matrix.ops.TranslatorScalar;
import waffles.utils.tools.patterns.operator.Operation;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Translator} matrix performs a linear operation
 * which translates vectors with a constant. Matrices tagged
 * with this operator are generated from affine translations.
 * 
 * @author Waffles
 * @since Jul 8, 2019
 * @version 1.0
 * 
 *
 * @see UpperTriangular
 */
public interface Translator extends UpperTriangular
{
	/**
	 * Returns the abstract type of the {@code Translator} operator.
	 * 
	 * @return  a type operator
	 */
	public static Translator Type()
	{
		return () ->
		{
			return null;
		};
	}

	
	@Override
	public default Operation<Tensor> Addition(Tensor t)
	{
		return new TranslatorAddition(Operable(), (Matrix) t);
	}
	
	@Override
	public default Operation<Float> DotProduct(Tensor t)
	{
		return new TranslatorDotProduct(Operable(), (Matrix) t);
	}
			
	@Override
	public default Operation<Matrix> LMultiplier(Matrix m)
	{
		return new TranslatorRProduct(Operable(), m);
	}
	
	@Override
	public default Operation<Matrix> RMultiplier(Matrix m)
	{
		return new TranslatorLProduct(Operable(), m);
	}
		
	@Override
	public default Operation<Tensor> Multiply(float val)
	{
		return new TranslatorScalar(Operable(), val);
	}
	
	
	@Override
	public default Translator instance(Tensor t)
	{
		return () ->
		{
			return (Matrix) t;
		};
	}
	
	@Override
	public default boolean allows(Tensor m, int ulps)
	{		
		if(!UpperTriangular.Type().allows(m, ulps))
		{
			return false;
		}
		
		Matrix mat = (Matrix) m;
		int cols = mat.Columns();
		int rows = mat.Rows();
		
		for(int r = 0; r < rows - 1; r++)
		{			
			for(int c = r + 1; c < cols - 1; c++)
			{
				if(!Floats.isZero(mat.get(r, c), ulps))
				{
					return false;
				}
			}
			
			if(!Floats.isEqual(mat.get(r, r), 1f, ulps))
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public default boolean matches(Tensor t)
	{
		return t.Operator() instanceof Translator;
	}
}