package waffles.utils.geom.utilities.chiral;

import waffles.utils.alg.lin.measure.matrix.fixed.Matrix3x3;
import waffles.utils.alg.lin.measure.matrix.types.Square;
import waffles.utils.alg.lin.solvers.matrix.exact.types.LUCrout;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.spin.Spin2D;
import waffles.utils.geom.spatial.maps.data.unary.Rotated2D;
import waffles.utils.lang.utilities.enums.Sign;
import waffles.utils.lang.utilities.patterns.Signed;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Dial} defines clockwise and counter-clockwise motion.
 *
 * @author Waffles
 * @since 10 Sep 2021
 * @version 1.0
 * 
 * 
 * @see Rotated2D
 * @see Signed
 */
public enum Dial implements Rotated2D, Signed
{
	/**
	 * Counter clockwise.
	 */
	CCW(+1),
	/**
	 * Clockwise.
	 */
	 CW(-1);


	/**
	 * Checks if three points in the 2D plane are colinear.
	 * 
	 * @param a  an affine point
	 * @param b  an affine point
	 * @param c  an affine point
	 * @return  {@code true} if they are colinear
	 * 
	 * 
	 * @see Point
	 */
	public static boolean isColinear(Point a, Point b, Point c)
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
	 * @param a  an affine point
	 * @param b  an affine point
	 * @param c  an affine point
	 * @return   a dial order
	 * 
	 * 
	 * @see Point
	 */
	public static Dial of(Point a, Point b, Point c)
	{
		Matrix3x3 mat = new Matrix3x3();
		mat.setOperator(Square.Type());
		
		mat.set(a.aff(0), 0, 0);
		mat.set(b.aff(0), 0, 1);
		mat.set(c.aff(0), 0, 2);
		
		mat.set(a.aff(1), 1, 0);
		mat.set(b.aff(1), 1, 1);
		mat.set(c.aff(1), 1, 2);
		
		mat.set(1f, 2, 0);
		mat.set(1f, 2, 1);
		mat.set(1f, 2, 2);
		
		LUCrout slv = new LUCrout(mat);
		if(slv.determinant() > 0)
			return CCW;
		if(slv.determinant() < 0)
			return CW;
		return null;
	}

	
	private int sign;
	
	private Dial(int s)
	{
		sign = s;
	}
		
	@Override
	public Spin2D Spin()
	{
		return new Spin2D(sign * Floats.PI / 4);
	}

	@Override
	public Sign Sign()
	{
		return Sign.of(sign);
	}
}