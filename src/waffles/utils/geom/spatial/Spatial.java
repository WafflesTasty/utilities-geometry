package waffles.utils.geom.spatial;

import waffles.utils.geom.spatial.spin.Spin;

/**
 * A {@code Spatial} object describes a position in space.
 * In addition to {@code Axial} data it defines a spin.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Axial
 */
public interface Spatial extends Axial
{
	/**
	 * Changes the spin of the {@code Spatial}.
	 * 
	 * @param s  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract void setSpin(Spin s);
	
	/**
	 * Returns the spin of the {@code Spatial}.
	 * 
	 * @return  a rotation spin
	 * 
	 * 
	 * @see Spin
	 */
	public abstract Spin Spin();
}