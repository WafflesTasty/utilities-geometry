package waffles.utils.geom.utilities.tform.linear.trx;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.banded.upper.UpperTriangular;
import waffles.utils.alg.lin.measure.tensor.Tensor;
import waffles.utils.geom.utilities.tform.linear.trx.ops.TranslatorAddition;
import waffles.utils.geom.utilities.tform.linear.trx.ops.TranslatorDotProduct;
import waffles.utils.geom.utilities.tform.linear.trx.ops.TranslatorLProduct;
import waffles.utils.geom.utilities.tform.linear.trx.ops.TranslatorRProduct;
import waffles.utils.geom.utilities.tform.linear.trx.ops.TranslatorScalar;
import waffles.utils.tools.patterns.operator.Operation;

/**
 * A {@code Translator} operator assumes a matrix to perform
 * a linear translation of vectors. Per construction, every
 * {@code Translator} is {@code UpperTriangular}.
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
	 * Returns the abstract {@code Translator} type.
	 *
	 * @return  a type operator
	 */
	public static Translator Type()
	{
		return () -> null;
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
	public default Operation<Tensor> Multiply(float v)
	{
		return new TranslatorScalar(Operable(), v);
	}

	@Override
	public default Translator instance(Tensor t)
	{
		return () -> (Matrix) t;
	}

	@Override
	public default boolean matches(Tensor t)
	{
		return t.Operator() instanceof Translator;
	}
}