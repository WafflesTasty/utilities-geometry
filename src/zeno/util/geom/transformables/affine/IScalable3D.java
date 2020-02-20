package zeno.util.geom.transformables.affine;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.tools.Floats;

/**
 * The {@code IScalable3D} interface defines an object
 * capable of being scaled in a 3D affine space.
 *
 * @author Zeno
 * @since Apr 22, 2016
 * @version 1.0
 * 
 * 
 * @see IScalable
 */
public interface IScalable3D extends IScalable
{	
	/**
	 * Scales the {@code IScalable3D} with a specified factor.
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
	 * Scales the {@code IScalable3D} to a new scale vector.
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
	public abstract Vector3 Size();
	
	/**
	 * Returns the height of the {@code IScalable3D}.
	 * 
	 * @return  a height factor
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the width of the {@code IScalable3D}.
	 * 
	 * @return  a width factor
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the depth of the {@code IScalable3D}.
	 * 
	 * @return  a depth factor
	 */
	public default float Depth()
	{
		return Size().Z();
	}
}