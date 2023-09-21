package waffles.utils.geom.utilities.constants;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Cardinal2D} defines one of the cardinal directions in two-dimensional space.
 * All possible cardinal directions are available through static-access.
 *
 * @author Waffles
 * @since Apr 30, 2016
 * @version 1.0
 * 
 * 
 * @see Vector2
 */
public final class Cardinal2D extends Vector2
{
	/**
	 * The center {@code Cardinal2D} equals the zero vector.
	 */
	public static Cardinal2D CENTER 	= new Cardinal2D( 0, 0);
	/**
	 * The east {@code Cardinal2D} points along the positive x-axis.
	 */
	public static Cardinal2D EAST 		= new Cardinal2D( 1, 0);
	/**
	 * The northeast {@code Cardinal2D} points along the first bisector.
	 */
	public static Cardinal2D NORTHEAST 	= new Cardinal2D( 1, 1);
	/**
	 * The north {@code Cardinal2D} points along the positive y-axis.
	 */
	public static Cardinal2D NORTH 		= new Cardinal2D( 0, 1);
	/**
	 * The northwest {@code Cardinal2D} points along the second bisector.
	 */
	public static Cardinal2D NORTHWEST 	= new Cardinal2D(-1, 1);
	/**
	 * The west {@code Cardinal2D} points along the negative x-axis.
	 */
	public static Cardinal2D WEST 		= new Cardinal2D(-1, 0);
	/**
	 * The southwest {@code Cardinal2D} points along the third bisector.
	 */
	public static Cardinal2D SOUTHWEST 	= new Cardinal2D(-1,-1);
	/**
	 * The south {@code Cardinal2D} points along the negative y-axis.
	 */
	public static Cardinal2D SOUTH 		= new Cardinal2D( 0,-1);
	/**
	 * The southeast {@code Cardinal2D} points along the fourth bisector.
	 */
	public static Cardinal2D SOUTHEAST 	= new Cardinal2D( 1,-1);
		

	/**
	 * Creates a {@code Cardinal2D} in a given direction.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @return  a cardinal
	 */
	public static Cardinal2D create(int x, int y)
	{
		int sx = Integers.sign(x);
		int sy = Integers.sign(y);
		
		for(Cardinal2D c : All())
		{
			int cx = Integers.sign((int) c.X());
			int cy = Integers.sign((int) c.Y());
			
			if(sx == cx && sy == cy)
			{
				return c;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns an array that contains every perpendicular {@code Cardinal2D}.
	 * 
	 * @return a perpendicular cardinal array
	 */
	public static Cardinal2D[] Perpendiculars()
	{
		return PERPENDICULARS;
	}
	
	/**
	 * Returns an array that contains every diagonal {@code Cardinal2D}.
	 * 
	 * @return a diagonal cardinal array
	 */
	public static Cardinal2D[] Diagonals()
	{
		return DIAGONALS;
	}
		
	/**
	 * Returns an array that contains every {@code Cardinal2D}.
	 * 
	 * @return  a full cardinal array
	 */
	public static Cardinal2D[] All()
	{
		return ALL;
	}
	
	
	
	private static Cardinal2D[] ALL = new Cardinal2D[]{CENTER, EAST, NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST};
	
	private static Cardinal2D[] DIAGONALS = new Cardinal2D[]{NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST};
	
	private static Cardinal2D[] PERPENDICULARS = new Cardinal2D[]{EAST, NORTH, SOUTH, WEST};
	
	private Cardinal2D(int x, int y)
	{
		super(x, y);
	}
}