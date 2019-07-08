package zeno.util.geom.transforms.types;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.types.banded.upper.UpperTriangular;
import zeno.util.algebra.linear.tensor.Tensor;
import zeno.util.geom.transforms.functions.TranslatorAddition;
import zeno.util.geom.transforms.functions.TranslatorDotProduct;
import zeno.util.geom.transforms.functions.TranslatorLProduct;
import zeno.util.geom.transforms.functions.TranslatorRProduct;
import zeno.util.tools.Floats;
import zeno.util.tools.patterns.properties.operator.Operation;
import zeno.util.tools.patterns.properties.operator.Operator;

/**
 * The {@code Banded} interface defines an operator for translation matrices.
 * Matrices tagged with this operator are generated from affine translations.
 * 
 * @author Zeno
 * @since Jul 8, 2019
 * @version 1.0
 * 
 *
 * @see UpperTriangular
 */
public interface Translator extends UpperTriangular
{
	/**
	 * Returns the abstract type of the {@code Translator}.
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
	public default Operation<Float> dotproduct(Tensor t)
	{
		return new TranslatorDotProduct(Operable(), (Matrix) t);
	}
			
	@Override
	public default Operation<Matrix> LMultiplier(Matrix m)
	{
		return new TranslatorRProduct(m, Operable());
	}
	
	@Override
	public default Operation<Matrix> RMultiplier(Matrix m)
	{
		return new TranslatorLProduct(Operable(), m);
	}

	@Override
	public default Operation<Tensor> addition(Tensor t)
	{
		return new TranslatorAddition((Matrix) t, Operable());
	}
	
	
	@Override
	public default Matrix multiply(float val)
	{
		Matrix m = Operable();
		
		int cols = m.Columns();
		int rows = m.Rows();
	
		
		Matrix result = Matrices.create(rows, cols);
		for(int r = 0; r < rows; r++)
		{
			result.set(val * m.get(r, cols - 1), r, cols - 1);
			result.set(val, r, r);
		}
		
		return result;
	}

	@Override
	public default boolean matches(Operator<Tensor> op)
	{
		return op instanceof Translator;
	}
	
	@Override
	public default boolean matches(Tensor m, int ulps)
	{		
		if(!m.is(UpperTriangular.Type(), ulps))
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
	public default Translator instance(Tensor t)
	{
		return () ->
		{
			return (Matrix) t;
		};
	}
}