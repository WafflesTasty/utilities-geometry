package waffles.utils.geom.spatial.owners;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.spatial.maps.data.unary.Scaled3D;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Scalable3D} object can be scaled in a three-dimensional vector space.
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
	 * Scales the {@code Scalable3D} with a given factor.
	 *
	 * @param w  a width scale
	 * @param h  a height scale
	 * @param d  a depth scale
	 */
	public default void scaleFor(float w, float h, float d)
	{
		if(ERROR < Floats.abs(w - 1f) || ERROR < Floats.abs(h - 1f) || ERROR < Floats.abs(d - 1f))
		{
			scaleFor(new Arrow(w, h, d));
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
		scaleTo(new Arrow(w, h, d));
	}


	@Override
	public default Arrow Scale()
	{
		return Scalable.super.Scale();
	}
}