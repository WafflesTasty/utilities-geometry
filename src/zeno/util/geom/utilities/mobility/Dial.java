package zeno.util.geom.utilities.mobility;

import zeno.util.algebra.algorithms.solvers.SLVCrout;
import zeno.util.algebra.linear.matrix.fixed.Matrix3x3;
import zeno.util.algebra.linear.vector.fixed.Vector2;

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
	 * Computes the dial of a triangle.
	 * 
	 * @param a  a first point
	 * @param b  a second point
	 * @param c  a third point
	 * @return  a three point dial
	 * 
	 * 
	 * @see Vector2
	 */
	public static Dial of(Vector2 a, Vector2 b, Vector2 c)
	{
		Matrix3x3 mat = new Matrix3x3();
		
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
		return CLOCKWISE;
	}
}
