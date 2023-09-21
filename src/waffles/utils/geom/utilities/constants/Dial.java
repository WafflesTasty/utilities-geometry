package waffles.utils.geom.utilities.constants;

import waffles.utils.algebra.algorithms.solvers.SLVCrout;
import waffles.utils.algebra.elements.linear.matrix.fixed.Matrix3x3;
import waffles.utils.algebra.elements.linear.matrix.types.Square;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;

/**
 * The {@code Dial} enum defines clockwise and counter-clockwise motion.
 *
 * @author Waffles
 * @since 10 Sep 2021
 * @version 1.0
 */
public enum Dial
{
	/**
	 * Counter clockwise.
	 */
	CNT_CLOCKWISE,
	/**
	 * Clockwise.
	 */
	CLOCKWISE;
	
	
	/**
	 * Checks if three points in the 2D plane are colinear.
	 * 
	 * @param a  a point vector
	 * @param b  a point vector
	 * @param c  a point vector
	 * @return  {@code true} if they are colinear
	 * 
	 * 
	 * @see Vector2
	 */
	public static boolean isColinear(Vector2 a, Vector2 b, Vector2 c)
	{
		return of(a, b, c) == null;
	}
	
	/**
	 * Computes the dial of a triangle of points.
	 * Depending on the order of the points, they will
	 * either turn clockwise or counterclockwise in
	 * the 2D plane. If the points are colinear,
	 * this method will return null.
	 * 
	 * @param a  a point vector
	 * @param b  a point vector
	 * @param c  a point vector
	 * @return   a dial order
	 * 
	 * 
	 * @see Vector2
	 */
	public static Dial of(Vector2 a, Vector2 b, Vector2 c)
	{
		Matrix3x3 mat = new Matrix3x3();
		mat.setOperator(Square.Type());
		
		mat.set(a.X(), 0, 0);
		mat.set(b.X(), 0, 1);
		mat.set(c.X(), 0, 2);
		
		mat.set(a.Y(), 1, 0);
		mat.set(b.Y(), 1, 1);
		mat.set(c.Y(), 1, 2);
		
		mat.set(1f, 2, 0);
		mat.set(1f, 2, 1);
		mat.set(1f, 2, 2);
		
		SLVCrout slv = new SLVCrout(mat);
		if(slv.determinant() > 0)
			return CNT_CLOCKWISE;
		if(slv.determinant() < 0)
			return CLOCKWISE;
		return null;
	}
}
