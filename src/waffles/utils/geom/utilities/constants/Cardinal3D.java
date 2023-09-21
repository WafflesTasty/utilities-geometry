package waffles.utils.geom.utilities.constants;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Cardinal3D} defines one of the cardinal directions in three-dimensional space.
 * All possible cardinal directions are available through static-access.
 *
 * @author Waffles
 * @since Apr 30, 2016
 * @version 1.0
 * 
 * 
 * @see Vector3
 */
public final class Cardinal3D extends Vector3
{
	/**
	 * The up {@code Cardinal3D} points along the positive y-axis.
	 */
	public static Cardinal3D UP			 		= new Cardinal3D( 0, 1, 0);
	/**
	 * The upper east {@code Cardinal3D} points up along the positive x-axis.
	 */
	public static Cardinal3D UPPER_EAST 		= new Cardinal3D( 1, 1, 0);
	/**
	 * The upper southeast {@code Cardinal3D} points up along the first bisector.
	 */
	public static Cardinal3D UPPER_NORTHEAST 	= new Cardinal3D( 1, 1, 1);
	/**
	 * The upper south {@code Cardinal3D} points up along the positive z-axis.
	 */
	public static Cardinal3D UPPER_NORTH 		= new Cardinal3D( 0, 1, 1);
	/**
	 * The upper southwest {@code Cardinal3D} points up along the second bisector.
	 */
	public static Cardinal3D UPPER_NORTHWEST 	= new Cardinal3D(-1, 1, 1);
	/**
	 * The upper west {@code Cardinal3D} points up along the negative x-axis.
	 */
	public static Cardinal3D UPPER_WEST 		= new Cardinal3D(-1, 1, 0);
	/**
	 * The upper northwest {@code Cardinal3D} points up along the third bisector.
	 */
	public static Cardinal3D UPPER_SOUTHWEST 	= new Cardinal3D(-1, 1,-1);
	/**
	 * The upper north {@code Cardinal3D} points up along the negative z-axis.
	 */
	public static Cardinal3D UPPER_SOUTH 		= new Cardinal3D( 0, 1,-1);
	/**
	 * The upper northeast {@code Cardinal3D} points up along the fourth bisector.
	 */
	public static Cardinal3D UPPER_SOUTHEAST 	= new Cardinal3D( 1, 1,-1);
	
	
	/**
	 * The down {@code Cardinal3D} points along the negative y-axis.
	 */
	public static Cardinal3D DOWN		 		= new Cardinal3D( 0,-1, 0);
	/**
	 * The lower east {@code Cardinal3D} points down along the positive x-axis.
	 */
	public static Cardinal3D LOWER_EAST 		= new Cardinal3D( 1,-1, 0);
	/**
	 * The lower southeast {@code Cardinal3D} points down along the first bisector.
	 */
	public static Cardinal3D LOWER_NORTHEAST 	= new Cardinal3D( 1,-1, 1);
	/**
	 * The lower south {@code Cardinal3D} points down along the positive z-axis.
	 */
	public static Cardinal3D LOWER_NORTH 		= new Cardinal3D( 0,-1, 1);
	/**
	 * The lower southwest {@code Cardinal3D} points down along the second bisector.
	 */
	public static Cardinal3D LOWER_NORTHWEST 	= new Cardinal3D(-1,-1, 1);
	/**
	 * The lower west {@code Cardinal3D} points down along the negative x-axis.
	 */
	public static Cardinal3D LOWER_WEST 		= new Cardinal3D(-1,-1, 0);
	/**
	 * The lower northwest {@code Cardinal3D} points down along the third bisector.
	 */
	public static Cardinal3D LOWER_SOUTHWEST 	= new Cardinal3D(-1,-1,-1);
	/**
	 * The lower north {@code Cardinal3D} points down along the negative z-axis.
	 */
	public static Cardinal3D LOWER_SOUTH 		= new Cardinal3D( 0,-1,-1);
	/**
	 * The lower northeast {@code Cardinal3D} points down along the fourth bisector.
	 */
	public static Cardinal3D LOWER_SOUTHEAST 	= new Cardinal3D( 1,-1,-1);
	
	
	/**
	 * The center {@code Cardinal3D} equals the zero vector.
	 */
	public static Cardinal3D CENTER 			= new Cardinal3D( 0, 0, 0);
	/**
	 * The east {@code Cardinal3D} points straight along the positive x-axis.
	 */
	public static Cardinal3D EAST 				= new Cardinal3D( 1, 0, 0);
	/**
	 * The southeast {@code Cardinal3D} points straight along the first bisector.
	 */
	public static Cardinal3D NORTHEAST 			= new Cardinal3D( 1, 0, 1);
	/**
	 * The south {@code Cardinal3D} points straight along the positive z-axis.
	 */
	public static Cardinal3D NORTH 				= new Cardinal3D( 0, 0, 1);
	/**
	 * The southwest {@code Cardinal3D} points straight along the second bisector.
	 */
	public static Cardinal3D NORTHWEST 			= new Cardinal3D(-1, 0, 1);
	/**
	 * The west {@code Cardinal3D} points straight along the negative x-axis.
	 */
	public static Cardinal3D WEST 				= new Cardinal3D(-1, 0, 0);
	/**
	 * The northwest {@code Cardinal3D} points straight along the third bisector.
	 */
	public static Cardinal3D SOUTHWEST 			= new Cardinal3D(-1, 0,-1);
	/**
	 * The north {@code Cardinal3D} points straight along the negative z-axis.
	 */
	public static Cardinal3D SOUTH 				= new Cardinal3D( 0, 0,-1);
	/**
	 * The northeast {@code Cardinal3D} points straight along the fourth bisector.
	 */
	public static Cardinal3D SOUTHEAST 			= new Cardinal3D( 1, 0,-1);
		
	
	/**
	 * Creates a {@code Cardinal3D} in a given direction.
	 * 
	 * @param x  an x-coordinate
	 * @param y  an y-coordinate
	 * @param z  an z-coordinate
	 * @return  a cardinal
	 */
	public static Cardinal3D create(int x, int y, int z)
	{
		int sx = Integers.sign(x);
		int sy = Integers.sign(y);
		int sz = Integers.sign(z);
		
		for(Cardinal3D c : All())
		{
			int cx = Integers.sign((int) c.X());
			int cy = Integers.sign((int) c.Y());
			int cz = Integers.sign((int) c.Z());
			
			if(sx == cx && sy == cy && sz == cz)
			{
				return c;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns an array that contains every perpendicular {@code Cardinal3D}.
	 * 
	 * @return  a perpendicular cardinal array
	 */
	public static Cardinal3D[] Perpendiculars()
	{
		return PERPENDICULARS;
	}
	
	/**
	 * Returns an array that contains every diagonal {@code Cardinal3D}.
	 * 
	 * @return  a diagonal cardinal array
	 */
	public static Cardinal3D[] Diagonals()
	{
		return DIAGONALS;
	}
		
	/**
	 * Returns an array that contains every {@code Cardinal3D}.
	 * 
	 * @return  a full cardinal array
	 */
	public static Cardinal3D[] All()
	{
		return ALL;
	}
	
	
	
	private static Cardinal3D[] ALL = new Cardinal3D[]
	{
		UP, UPPER_EAST, UPPER_NORTHEAST, UPPER_NORTH, UPPER_NORTHWEST, UPPER_WEST, UPPER_SOUTHWEST, UPPER_SOUTH, UPPER_SOUTHEAST,
		DOWN, LOWER_EAST, LOWER_NORTHEAST, LOWER_NORTH, LOWER_NORTHWEST, LOWER_WEST, LOWER_SOUTHWEST, LOWER_SOUTH, LOWER_SOUTHEAST,
		CENTER, EAST, NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST
	};
	
	private static Cardinal3D[] DIAGONALS = new Cardinal3D[]
	{
		UPPER_NORTHEAST, UPPER_NORTHWEST, UPPER_SOUTHEAST, UPPER_SOUTHWEST,
		LOWER_NORTHEAST, LOWER_NORTHWEST, LOWER_SOUTHEAST, LOWER_SOUTHWEST,
	};
	
	private static Cardinal3D[] PERPENDICULARS = new Cardinal3D[]{UP, EAST, NORTH, SOUTH, WEST, DOWN};
	
	private Cardinal3D(int x, int y, int z)
	{
		super(x, y, z);
	}
}