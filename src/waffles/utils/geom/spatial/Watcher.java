package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code Watcher} object describes a viewpoint in space.
 * In addition to {@code Spatial} data it defines an oculus.
 *
 * @author Waffles
 * @since 11 Sep 2023
 * @version 1.0
 *
 * 
 * @see Spatial
 */
public interface Watcher extends Spatial
{
	/**
	 * Changes the oculus of the {@code Watcher}.
	 * 
	 * @param o  an oculus vector
	 */
	public abstract void setOculus(Vector o);
	
	/**
	 * Returns the oculus of the {@code Watcher}.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Oculus();
}