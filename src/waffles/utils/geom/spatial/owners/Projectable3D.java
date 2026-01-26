package waffles.utils.geom.spatial.owners;

import waffles.utils.alg.lin.measure.vector.fixed.Vector3;
import waffles.utils.geom.spatial.maps.data.unary.Projected3D;

/**
 * A {@code Projectable3D} object can be projected into a three-dimensional vector space.
 * 
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 * 
 * 
 * @see Projectable
 * @see Projected3D
 */
public interface Projectable3D extends Projectable, Projected3D
{		
	/**
	 * Moves the {@code Projectable3D} for a given distance.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 */
	public default void projectFor(float x, float y, float z)
	{
		projectFor(new Vector3(x, y, z));
	}
	
	/**
	 * Projects the {@code Projectable3D} to a new oculus.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 */
	public default void projectTo(float x, float y, float z)
	{
		projectTo(new Vector3(x, y, z));
	}
	
	
	@Override
	public default Vector3 Oculus()
	{
		return (Vector3) Projectable.super.Oculus();
	}
}