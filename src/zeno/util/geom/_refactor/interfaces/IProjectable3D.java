package zeno.util.geom._refactor.interfaces;

import zeno.util.geom.IProjectable;
import zeno.util.geom.ITransformation;

/**
 * The {@code IProjectable3D} interface defines an object
 * capable of projection from 3D to 2D space.
 *
 * @since Apr 27, 2016
 * @author Zeno
 * 
 * @see ITransformation
 * @see IProjectable
 */
public interface IProjectable3D extends IProjectable, ITransformation
{
	@Override
	public default IProjectable3D Projection()
	{
		return this;
	}
	
	
	/**
	 * Changes the projection ratio of the {@code IProjectable3D}.
	 * 
	 * @param ratio  a new projection ratio
	 */
	public default void projectRatio(float ratio)
	{
		Projection().projectRatio(ratio);
	}
	
	/**
	 * Changes the base project ratio of the {@code IProjectable3D}.
	 * 
	 * @param ratio  a base projection ratio
	 */
	public default void projectBaseRatio(float ratio)
	{
		Projection().projectBaseRatio(ratio);
	}
	
	/**
	 * Changes the projection source of the {@code IProjectable3D}.
	 * 
	 * @param h  a source height
	 * @param d  a source depth
	 */
	public default void projectFrom(float h, float d)
	{
		Projection().projectFrom(h, d);
	}
	
	/**
	 * Changes the projection target of the {@code IProjectable3D}.
	 * 
	 * @param zplane  a target z-plane
	 */
	public default void projectTo(float zplane)
	{
		Projection().projectTo(zplane);
	}

		
	/**
	 * Returns the projection plane of the {@code IProjectable3D}.
	 * 
	 * @return  the projection's target plane
	 */
	public default float getProjectPlane()
	{
		return Projection().getProjectPlane();
	}
	
	/**
	 * Returns the projection ratio of the {@code IProjectable3D}.
	 * 
	 * @return  the projection's aspect ratio
	 */
	public default float getProjectRatio()
	{
		return Projection().getProjectRatio();
	}

	/**
	 * Returns the projection height of the {@code IProjectable3D}.
	 * 
	 * @return  the projection's source height
	 */
	public default float getSourceHeight()
	{
		return Projection().getSourceHeight();
	}
	
	/**
	 * Returns the projection depth of the {@code IProjectable3D}.
	 * 
	 * @return  the projection's source depth
	 */
	public default float getSourceDepth()
	{
		return Projection().getSourceDepth();
	}
}