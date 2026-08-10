package waffles.utils.geom.utilities.chiral;

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
	CCW(Sign.POSITIVE),
	/**
	 * Clockwise.
	 */
	 CW(Sign.NEGATIVE);


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
	 * Computes a {@code Dial} of a triangle of points.
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
		Point p = b.minus(a);
		Point q = c.minus(a);
		
		float r = p.X() * q.Y();
		float s = p.Y() * q.X();
		
		return of(Sign.of(r - s));
	}

	/**
	 * Creates a {@code Dial} based on a {@code Sign}.
	 * 
	 * @param s  a sign
	 * @return   a dial
	 * 
	 * 
	 * @see Sign
	 */
	public static Dial of(Sign s)
	{
		switch(s)
		{
		case POSITIVE:
			return CCW;
		case NEGATIVE:
			return CW;
		case ZERO:
		default:
			return null;
		}
	}
	
	
	private Sign sign;
	
	private Dial(Sign s)
	{
		sign = s;
	}
		
	@Override
	public Spin2D Spin()
	{
		return new Spin2D(sign.Value() * Floats.PI / 4);
	}

	@Override
	public Sign Sign()
	{
		return sign;
	}
}