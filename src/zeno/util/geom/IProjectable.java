package zeno.util.geom;

/**
 * The {@code IProjectable} interface defines an object capable
 * of being projected through an {@link ITransformation}.
 *
 * @author Zeno
 * @since Apr 22, 2016
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