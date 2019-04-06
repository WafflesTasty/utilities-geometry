package zeno.util.geom.utilities.bounds;

/**
 * The {@code IBounded} interface defines an object bound in n dimensions.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 */
@FunctionalInterface
public interface IBounded
{	
	/**
	 * Returns the bounds of the {@code IBounded} object.
	 * 
	 * @return  the object bounds
	 * 
	 * 
	 * @see Bounds
	 */
	public abstract Bounds Bounds();
}