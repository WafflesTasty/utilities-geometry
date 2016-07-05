package zeno.util.geom;

/**
 * The {@code ITransformable} interface defines an object capable
 * of being transformed through an {@link ITransformation}.
 *
 * @author Zeno
 * @since Apr 22, 2016
 */
public interface ITransformable
{
	/**
	 * Returns the transform of the {@code ITransformable}.
	 * 
	 * @return  the object's transformation
	 * @see ITransformation
	 */
	public abstract ITransformation Transform();
}