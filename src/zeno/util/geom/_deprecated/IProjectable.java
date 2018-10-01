package zeno.util.geom._deprecated;

import zeno.util.geom.ITransformation;

/**
 * The {@code IProjectable} interface defines an object capable
 * of being projected through an {@link ITransformation}.
 *
 * @since Apr 22, 2016
 * @author Zeno
 */
public interface IProjectable
{
	/**
	 * Returns the projection of the {@code IProjectable}.
	 * 
	 * @return  the object's projection
	 * @see ITransformation
	 */
	public abstract ITransformation Projection();
}