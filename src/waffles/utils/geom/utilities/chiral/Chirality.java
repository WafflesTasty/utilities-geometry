package waffles.utils.geom.utilities.chiral;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.spatial.maps.data.unary.Rotated;
import waffles.utils.geom.spatial.maps.linear.Rotation;

/**
 * A {@code Chirality} defines an orientation of a reference frame.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see LinearMap
 * @see Rotated
 */
public abstract class Chirality implements Rotated, LinearMap
{
	/**
	 * Creates a new {@code Chirality}.
	 * 
	 * @param d  a dial set
	 * @return   a chirality
	 * 
	 * 
	 * @see Dial
	 */
	public static Chirality create(Dial... d)
	{
		if(d.length == 1)
			return new Chirality2D(d[0]);
		if(d.length == 2)
			return new Chirality3D(d[0], d[1]);
		
		return new ChiralityND(d);
	}
	
	
	private Dial[] dials;
	private Rotation rot;
	
	/**
	 * Creates a new {@code Chirality}.
	 * 
	 * @param d  a dial set
	 * 
	 * 
	 * @see Dial
	 */
	public Chirality(Dial... d)
	{
		rot = new Rotation(this);
		dials = d;
	}

	/**
	 * Returns the {@code Chirality} dials.
	 * 
	 * @return  a dial set
	 * 
	 * 
	 * @see Dial
	 */
	public Dial[] Dials()
	{
		return dials;
	}

	
	@Override
	public Matrix Matrix(int dim)
	{
		return rot.Matrix(dim);
	}

	@Override
	public Matrix Inverse(int dim)
	{
		return rot.Inverse(dim);
	}
}