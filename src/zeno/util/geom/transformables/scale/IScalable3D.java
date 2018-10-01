package zeno.util.geom.transformables.scale;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformable;
import zeno.util.geom.transformations.ITransformation3D;

/**
 * The {@code IScalable3D} interface defines an object
 * capable of scaling in 3D space.
 *
 * @author Zeno
 * @since Apr 21, 2016
 * @version 1.0
 * 
 * 
 * @see ITransformable
 */
public interface IScalable3D extends ITransformable
{
	@Override
	public abstract ITransformation3D Transform();

	
	/**
	 * Scales the {@code IScalable3D} with a specified factor.
	 * 
	 * @param w  a width factor
	 * @param h  a height factor
	 * @param d  a depth factor
	 */
	public default void scaleFor(float w, float h, float d)
	{
		if(w != 1f || h != 1f || d != 1f)
		{
			Vector3 s = Transform().Scale();
			scaleTo(w * s.X(), h * s.Y(), d * s.Z());
		}
	}
			
	/**
	 * Scales the {@code IScalable3D} with a set factor.
	 * 
	 * @param vec  a factor to scale with
	 * 
	 * 
	 * @see Vector3
	 */
	public default void scaleFor(Vector3 vec)
	{
		scaleFor(vec.X(), vec.Y(), vec.Z());
	}

	
	/**
	 * Scales the {@code IScalable3D} to a new scale vector.
	 * 
	 * @param w  a new width
	 * @param h  a new height
	 * @param d  a new depth
	 */
	public default void scaleTo(float w, float h, float d)
	{
		scaleTo(new Vector3(w, h, d));
	}
	
	/**
	 * Scales the {@code IScalable3D} to a new scale vector.
	 * 
	 * @param vec  a new scale vector
	 * 
	 * 
	 * @see Vector3
	 */
	public default void scaleTo(Vector3 vec)
	{
		Transform().setScale(vec);
	}
}