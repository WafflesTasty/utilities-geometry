package zeno.util.geom;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.utilities.spin.Spin;

/**
 * The {@code AffineMap} interface defines an affine transformation.
 *
 * @author Zeno
 * @since Feb 03, 2020
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public interface AffineMap extends ITransformation
{
	/**
	 * Changes the origin of the {@code AffineMap}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void setOrigin(Vector o);
		
	/**
	 * Changes the scale of the {@code AffineMap}.
	 * 
	 * @param s  a size point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void setSize(Vector s);
	
	/**
	 * Changes the spin of the {@code AffineMap}.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract void setSpin(Spin s);
	
	
	/**
	 * Returns the origin of the {@code AffineMap}.
	 * 
	 * @return  an affine origin
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Origin();
		
	/**
	 * Returns the scale of the {@code AffineMap}.
	 * 
	 * @return  an affine size
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Size();

	/**
	 * Returns the spin of the {@code AffineMap}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
}