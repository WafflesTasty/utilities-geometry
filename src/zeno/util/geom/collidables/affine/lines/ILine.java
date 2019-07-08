package zeno.util.geom.collidables.affine.lines;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.algebra.linear.vector.fixed.Vector3;
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
	 * Creates a new {@code ILine}.
	 * 
	 * @param p1  the line's first point
	 * @param p2  the line's second point
	 * @return  a new line
	 * 
	 * 
	 * @see Vector
	 */
	public static ILine create(Vector p1, Vector p2)
	{
		if(p1.Size() == 2)
			return new Line2D((Vector2) p1, (Vector2) p2);
		if(p1.Size() == 3)
			return new Line3D((Vector3) p1, (Vector3) p2);
		
		return new LineND(p1, p2);
	}
	
	
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