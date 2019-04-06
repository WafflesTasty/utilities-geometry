package zeno.util.geom.utilities.bounds;

/**
 * The {@code IBounded3D} interface defines an object bound in three dimensions.
 *
 * @author Zeno
 * @since Aug 25, 2015
 * @version 1.0
 * 
 * 
 * @see IBounded
 */
@FunctionalInterface
public interface IBounded3D extends IBounded
{		
	@Override
	public abstract Bounds3D Bounds();
}