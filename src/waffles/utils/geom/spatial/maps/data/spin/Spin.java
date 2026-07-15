package waffles.utils.geom.spatial.maps.data.spin;

import waffles.utils.alg.lin.DotProduct;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.Hadamard;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Dimensional;
import waffles.utils.geom.utilities.tform.Composition;
import waffles.utils.geom.utilities.tform.Inversion;

/**
 * A {@code Spin} defines a data element that resembles a rotation.
 *
 * @author Waffles
 * @since Dec 26, 2019
 * @version 1.1
 *
 *
 * @see Composition
 * @see Dimensional
 * @see DotProduct
 * @see Inversion
 * @see Hadamard
 */
public interface Spin extends DotProduct, Dimensional, Composition<Spin>, Hadamard<Point>, Inversion<Spin>
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
		switch(s.Dimension())
		{
		case 2:
			return Spin2D.Matrix((Spin2D) s, d);
		case 3:
			return Spin3D.Matrix((Spin3D) s, d);
		default:
			return SpinND.Matrix((SpinND) s, d);
		}
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
	 * Returns a euler for the {@code Spin}.
	 * 
	 * @return  a euler vector
	 * 
	 * 
	 * @see Point
	 */
	public abstract Point Euler();

	/**
	 * Returns a spin from an {@code Euler}.
	 * 
	 * @param e  an euler vector
	 * @return   a spin
	 * 
	 * 
	 * @see Point
	 */
	public abstract Spin from(Point e);
	
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
	 * Hadamard-compose a point with the {@code Spin}.
	 * 
	 * @param p  a point
	 * @return   a hadamard spin
	 * 
	 * 
	 * @see Point
	 */
	@Override
	public default Spin hadamard(Point p)
	{
		return from(Euler().hadamard(p));
	}
	
	/**
	 * Multiplies a scalar with the {@code Spin}.
	 *
	 * @param s  a scalar value
	 * @return   a scaled spin
	 */
	@Override
	public abstract Spin times(Float s);
	
	/**
	 * Divides a scalar from the {@code Spin}.
	 *
	 * @param s  a scalar value
	 * @return   a scaled spin
	 */
	@Override
	public default Spin over(Float s)
	{
		return times(1f / s);
	}
}