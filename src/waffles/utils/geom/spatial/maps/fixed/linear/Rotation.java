package waffles.utils.geom.spatial.maps.fixed.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.geom.spatial.data.spin.Spin;
import waffles.utils.geom.spatial.data.spin.Spin2D;
import waffles.utils.geom.spatial.data.spin.Spin3D;
import waffles.utils.geom.spatial.data.spin.SpinND;
import waffles.utils.geom.spatial.data.unary.Rotated;

/**
 * A {@code Rotation} defines a linear map which rotates vectors
 * around the origin. Its corresponding matrices are orthogonal.
 *
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see LinearMap
 * @see Rotated
 */
public class Rotation implements LinearMap, Rotated
{
	/**
	 * Returns a default rotation {@code Spin}.
	 * 
	 * @param dim  a space dimension
	 * @return  a default spin
	 * 
	 * 
	 * @see Spin
	 */
	public static Spin Default(int dim)
	{
		if(dim == 2)
			return new Spin2D();
		if(dim == 3)
			return new Spin3D();
		
		return new SpinND();
	}
	
	
	private Rotated src;
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param dim  a default dimension
	 */
	public Rotation(int dim)
	{
		this(Default(dim));
	}
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param s  a rotated source
	 * 
	 * 
	 * @see Rotated
	 */
	public Rotation(Rotated s)
	{
		src = s;
	}
	
	/**
	 * Creates a new {@code Rotation}.
	 * 
	 * @param s  a default spin
	 * 
	 * 
	 * @see Spin
	 */
	public Rotation(Spin s)
	{
		src = () -> s;
	}


	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Spin.Matrix(src.Spin(), dim);
		return m.transpose();
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		return Spin.Matrix(src.Spin(), dim);
	}
	
	@Override
	public Spin Spin()
	{
		return src.Spin();
	}
}