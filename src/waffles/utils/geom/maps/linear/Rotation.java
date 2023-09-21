package waffles.utils.geom.maps.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.geom.spatial.spin.Spin;
import waffles.utils.geom.spatial.spin.Spin2D;
import waffles.utils.geom.spatial.spin.Spin3D;
import waffles.utils.geom.spatial.spin.SpinND;

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
 */
public class Rotation implements LinearMap
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
	
	
	private Spin spin;
	
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
	 * @param s  a spatial source
	 * 
	 * 
	 * @see Spatial
	 */
	public Rotation(Spatial s)
	{
		this(s.Spin());
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
		spin = s;
	}
			
	/**
	 * Returns the rotation {@code Spin}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public Spin Spin()
	{
		return spin;
	}


	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Spin.Matrix(spin, dim);
		return m.transpose();
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		return Spin.Matrix(spin, dim);
	}
}