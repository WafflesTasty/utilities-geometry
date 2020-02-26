package zeno.util.geom;

/**
 * The {@code ITransformable} interface defines an object being transformed in geometric space.
 *
 * @author Zeno
 * @since 26 Feb 2020
 * @version 1.1
 */
public interface ITransformable
{
	/**
	 * Returns the transform of the {@code ITransformable}.
	 * 
	 * @return  a homogeneous transform
	 * 
	 * 
	 * @see ITransformation
	 */
	public abstract ITransformation Transform();
}