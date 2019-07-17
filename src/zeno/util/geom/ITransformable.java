package zeno.util.geom;

/**
 * The {@code ITransformable} interfaces defines an object
 * that maintains a local transformation in affine space.
 *
 * @author Zeno
 * @since Jul 17, 2019
 * @version 1.0
 */
public interface ITransformable
{
	/**
	 * Returns the transform of the {@code ITransformable}.
	 * 
	 * @return  an affine transform
	 * 
	 * 
	 * @see ITransformation
	 */
	public abstract ITransformation Transform();
}