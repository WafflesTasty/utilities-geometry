package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;

/**
 * The {@code ILine} interface defines the base for line spaces.
 * 
 * @author Zeno
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see ICollidable
 */
public interface ILine extends ICollidable
{	
	/**
	 * Returns the first point of the {@code ILine}.
	 * 
	 * @return  the line's first point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P1();
	
	/**
	 * Returns the second point of the {@code ILine}.
	 * 
	 * @return  the line's second point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector P2();
}