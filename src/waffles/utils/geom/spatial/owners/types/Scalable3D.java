package waffles.utils.geom.spatial.owners.types;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.tools.primitives.Floats;

/**
 * An {@code Scalable3D} object can be scaled in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see Scalable
 */
public interface Scalable3D extends Scalable
{	
	@Override
	public abstract Vector3 Size();
	
	
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

		
	/**
	 * Returns the height of the {@code Scalable3D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the width of the {@code Scalable3D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the depth of the {@code Scalable3D}.
	 * 
	 * @return  a depth factor
	 */
	public default float Depth()
	{
		return Size().Z();
	}
}