package zeno.util.geom;

import zeno.util.algebra.tensors.vectors.Vector;
import zeno.util.geom.shapes.lines.Line;

/**
 * The {@code ICollideable} interface defines an object that can be collided with.
 * It allows spatial queries to be performed through points and lines.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 */
public interface ICollideable
{
	/**
	 * Indicates if the {@code ICollideable} contains a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is contained
	 * @see Line
	 */
	public abstract boolean contains(Line l);
	
	/**
	 * Indicates if the {@code ICollideable} contains a point.
	 * 
	 * @param v  a point to check
	 * @return {@code true} if the point is contained
	 * @see Vector
	 */
	public abstract boolean contains(Vector v);
	
	/**
	 * Indicates if the {@code ICollideable} intersects a line.
	 * 
	 * @param l  a line to check
	 * @return {@code true} if the line is intersected
	 * @see Line
	 */
	public abstract boolean intersects(Line l);
}