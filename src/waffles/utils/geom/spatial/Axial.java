package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code Axial} object describes an axis-aligned position in space.
 * This requires defining an origin and size vector.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 */
public interface Axial
{
	/**
	 * Changes the origin of the {@code Spatial}.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void setOrigin(Vector o);
		
	/**
	 * Changes the scale of the {@code Spatial}.
	 * 
	 * @param s  a size point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract void setSize(Vector s);

	
	/**
	 * Returns the origin of the {@code Spatial}.
	 * 
	 * @return  an affine origin
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Origin();
		
	/**
	 * Returns the scale of the {@code Spatial}.
	 * 
	 * @return  an affine size
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Size();
}