package waffles.utils.geom.spatial.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.data.unary.Scaled3D;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Scalable3D} object can be scaled in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.1
 * 
 * 
 * @see Scalable
 * @see Scaled3D
 */
public interface Scalable3D extends Scalable, Scaled3D
{	
	/**
	 * Scales the {@code Scalable3D} with a specified factor.
	 * 
	 * @param w  a  width factor
	 * @param h  a height factor
	 * @param d  a  depth factor
	 */
	public default void scaleFor(float w, float h, float d)
	{
		if(!Floats.isEqual(w, 1f, 1) || !Floats.isEqual(h, 1f, 1) || !Floats.isEqual(d, 1f, 1))
		{
			scaleFor(new Vector3(w, h, d));
		}
	}

	/**
	 * Scales the {@code Scalable3D} to a new scale vector.
	 * 
	 * @param w  a new  width
	 * @param h  a new height
	 * @param d  a new  depth
	 */
	public default void scaleTo(float w, float h, float d)
	{
		scaleTo(new Vector3(w, h, d));
	}

		
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Scalable.super.Size();
	}
}