package waffles.utils.geom.spatial.maps.data.spin;

import waffles.utils.alg.lin.measure.Normed;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.groups.Multiplication;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geom.utilities.groups.Composition;
import waffles.utils.geom.utilities.groups.Inversion;

/**
 * A {@code Spin} defines a data element that resembles a rotation.
 *
 * @author Waffles
 * @since Dec 26, 2019
 * @version 1.1
 *
 *
 * @see Composition
 * @see Multiplication
 * @see Dimensional
 * @see Normed
 */
public interface Spin extends Dimensional, Normed, Multiplication<Float>, Composition<Spin>, Inversion<Spin>
{
	/**
	 * Creates a {@code Matrix} from a {@code Spin}.
	 *
	 * @param s  a spin object
	 * @param d  a spin dimension
	 * @return   a rotation matrix
	 *
	 *
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin s, int d)
	{
		if(s instanceof Spin2D)
		{
			return Spin2D.Matrix((Spin2D) s, d);
		}
		if(s instanceof Spin3D)
		{
			return Spin3D.Matrix((Spin3D) s, d);
		}
		if(s instanceof SpinND)
		{
			return SpinND.Matrix((SpinND) s, d);
		}

		return null;
	}

	/**
	 * Creates a new {@code Spin}.
	 *
	 * @param d  a spin dimension
	 * @return   a spin
	 */
	public static Spin create(int d)
	{
		switch(d)
		{
		case 2:
			return new Spin2D();
		case 3:
			return new Spin3D();
		default:
			return new SpinND();
		}
	}


	/**
	 * Returns a basis vector in the {@code Spin}.
	 *
	 * @param k  a vector index
	 * @return   a basis vector
	 *
	 *
	 * @see Vector
	 */
	public abstract Vector Basis(int k);

	/**
	 * Composes the spin with another {@code Spin}.
	 *
	 * @param s  a second spin
	 * @return   a composite spin
	 */
	@Override
	public abstract Spin compose(Spin s);

	/**
	 * Multiplies a scalar with the {@code Spin}.
	 *
	 * @param v  a scalar value
	 * @return   a scaled spin
	 */
	@Override
	public abstract Spin times(Float v);
}