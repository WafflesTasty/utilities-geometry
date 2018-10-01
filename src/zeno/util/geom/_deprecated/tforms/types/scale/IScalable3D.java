package zeno.util.geom._deprecated.tforms.types.scale;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ITransformable;
import zeno.util.geom._deprecated.tforms.types.ITransformation3D;

/**
 * The {@code IScalable3D} interface defines an object
 * capable of scaling in 3D space.
 *
 * @since Apr 21, 2016
 * @author Zeno
 * 
 * @see ITransformable
 */
public interface IScalable3D extends ITransformable
{
	@Override
	public abstract ITransformation3D Transform();
	
	public default Vector3 Scale()
	{
		return Transform().Scale();
	}
	
		
	/**
	 * Scales the {@code IScalable3D} with a specified factor.
	 * 
	 * @param w  a width factor
	 * @param h  a height factor
	 * @param d  a depth factor
	 */
	public default void scaleFor(float w, float h, float d)
	{
		scaleTo(w * Scale().X(), h * Scale().Y(), d * Scale().Z());
	}
			
	/**
	 * Scales the {@code IScalable3D} with a set factor.
	 * 
	 * @param vec  a factor to scale with
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
	 * @see Vector3
	 */
	public default void scaleTo(Vector3 vec)
	{
		Transform().scaleTo(vec);
	}
}