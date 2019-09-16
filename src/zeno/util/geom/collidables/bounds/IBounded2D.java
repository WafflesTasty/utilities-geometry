package zeno.util.geom.collidables.bounds;

/**
 * The {@code IBounded2D} interface defines an object bound in two dimensions.
 *
 * @author Zeno
 * @since Aug 25, 2015
 * @version 1.0
 * 
 * 
 * @see IBounded
 */
@FunctionalInterface
public interface IBounded2D extends IBounded
{		
	@Override
	public abstract Bounds2D Bounds();
}