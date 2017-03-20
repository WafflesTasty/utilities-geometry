package zeno.util.geom;

import zeno.util.geom.shapes.NCuboid;

/**
 * The {@code IBounds} interface defines an object bound in n dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 */
public interface IBounds
{	
	/**
	 * Returns the dimension of the {@code IBounds}.
	 * 
	 * @return  the object's dimension
	 */
	public abstract int Dimension();
	
	/**
	 * Returns the {@code IBounds}'s bounding cuboid.
	 * 
	 * @return  the object's bounds
	 * @see NCuboid
	 */
	public abstract NCuboid Bounds();
}