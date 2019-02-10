package zeno.util.geom.transformables.projective;

import zeno.util.algebra.linear.vector.fixed.Vector3;

/**
 * The {@code IProjectable3D} interface defines an object
 * capable of being projected through a 3D vector space.
 * 
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IProjectable
 */
public interface IProjectable3D extends IProjectable
{	
	/**
	 * Moves the oculus of the {@code IProjectable} for a specified distance.
	 * 
	 * @param x  an x-direction to move in 
	 * @param y  an y-direction to move in
	 * @param z  a  z-direction to move in
	 */
	public default void projectFor(float x, float y, float z)
	{
		projectFor(new Vector3(x, y, z));
	}
	
	/**
	 * Projects the {@code IProjectable} to a new oculus.
	 * 
	 * @param x  an oculus x-coördinate
	 * @param y  an oculus y-coördinate
	 * @param z  an oculus z-coördinate
	 */
	public default void projectTo(float x, float y, float z)
	{
		projectTo(new Vector3(x, y, z));
	}
	
	
	@Override
	public abstract Vector3 Oculus();
}