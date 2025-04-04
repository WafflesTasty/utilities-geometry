package waffles.utils.geom.spatial.data.spin;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.measures.Normed;
import waffles.utils.geom.utilities.Dimensional;

/**
 * A {@code Spin} defines a data source to construct rotations.
 * 
 * @author Waffles
 * @since Dec 26, 2019
 * @version 1.1
 * 
 * 
 * @see Dimensional
 */
public interface Spin extends Dimensional, Normed
{
	/**
	 * A {@code Spin.Error} is thrown when two
	 * {@code Spin} objects are not compatible.
	 *
	 * @author Waffles
	 * @since Jan 22, 2020
	 * @version 1.0
	 * 
	 *
	 * @see RuntimeException
	 */
	public static class Error extends RuntimeException
	{
		private static final long serialVersionUID = 6899503424285607547L;

		private static String Description(Spin s1, Spin s2)
		{
			String dsc = "";
			
			dsc += "Trying to compose incompatible spins: ";
			dsc += s1.getClass().getSimpleName() + " and ";
			dsc += s2.getClass().getSimpleName() + ".";
			
			return dsc;
		}
		
		
		/**
		 * Creates a new {@code Spin.Error}.
		 * 
		 * @param s1  a spin object
		 * @param s2  a spin object
		 * 
		 * 
		 * @see Spin
		 */
		public Error(Spin s1, Spin s2)
		{
			super(Description(s1, s2));
		}
	}
	
	/**
	 * Creates a {@code Matrix} from a {@code Spin}.
	 * 
	 * @param s  a spin object
	 * @param dim  a matrix dimension
	 * @return  a rotation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public static Matrix Matrix(Spin s, int dim)
	{
		if(s instanceof Spin2D)
			return Spin2D.Matrix((Spin2D) s, dim);
		if(s instanceof Spin3D)
			return Spin3D.Matrix((Spin3D) s, dim);
		if(s instanceof SpinND)
			return SpinND.Matrix((SpinND) s, dim);
		
		return null;
	}
	
	/**
	 * Creates a {@code Spin} from a dimension.
	 * 
	 * @param dim  a vector dimension
	 * @return  a spin
	 */
	public static Spin create(int dim)
	{
		switch(dim)
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
	 * Inverts the direction of the {@code Spin}.
	 * 
	 * @return  an inverted spin
	 */
	public abstract Spin invert();
	
	/**
	 * Scales the {@code Spin} with a scalar value.
	 * 
	 * @param val  a scalar value
	 * @return  a scaled spin
	 */
	public abstract Spin times(float val);
	
	/**
	 * Composes the spin with another {@code Spin}.
	 * 
	 * @param spin  a spin to compose with
	 * @return  a composite spin
	 */
	public abstract Spin compose(Spin spin);
	
	/**
	 * Returns a basis vector in the {@code Spin}.
	 * 
	 * @param i  a vector index
	 * @return   a basis vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Basis(int i);
}