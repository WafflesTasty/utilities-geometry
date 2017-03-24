package zeno.util.geom._deprecated.tforms.types.scale;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom._deprecated.ITransformable;
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
	
		
	/**
	 * Scales the {@code IScalable3D} with a specified factor.
	 * 
	 * @param w  a width factor
	 * @param h  a height factor
	 * @param d  a depth factor
	 */
	public default void scaleFor(float w, float h, float d)
	{
		scaleTo(w * getWidth(), h * getHeight(), d * getDepth());
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

		
	/**
	 * Returns the height of the {@code IScalable3D}.
	 * 
	 * @return  the scalable's height
	 */
	public default float getHeight()
	{
		return Transform().getHeight();
	}
	
	/**
	 * Returns the width of the {@code IScalable3D}.
	 * 
	 * @return  the scalable's width
	 */
	public default float getWidth()
	{
		return Transform().getWidth();
	}
	
	/**
	 * Returns the depth of the {@code IScalable3D}.
	 * 
	 * @return  the scalable's depth
	 */
	public default float getDepth()
	{
		return Transform().getDepth();
	}
}