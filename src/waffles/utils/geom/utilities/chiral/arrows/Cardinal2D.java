package waffles.utils.geom.utilities.chiral.arrows;

import waffles.utils.geom.utilities.chiral.Chirality;
import waffles.utils.geom.utilities.chiral.Dial;

/**
 * A {@code Cardinal2D} defines a two-dimensional cardinal arrow.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Cardinal
 */
public class Cardinal2D extends Cardinal
{
	/**
	 * The center {@code Cardinal2D} equals the zero vector.
	 */
	public static Cardinal2D CENTER 	= new Cardinal2D( 0, 0);
	/**
	 * The east {@code Cardinal2D} points along the positive x-axis.
	 */
	public static Cardinal2D EAST 		= new Cardinal2D(+1, 0);
	/**
	 * The northeast {@code Cardinal2D} points along the first bisector.
	 */
	public static Cardinal2D NORTHEAST 	= new Cardinal2D(+1,-1);
	/**
	 * The north {@code Cardinal2D} points along the positive y-axis.
	 */
	public static Cardinal2D NORTH 		= new Cardinal2D( 0,-1);
	/**
	 * The northwest {@code Cardinal2D} points along the second bisector.
	 */
	public static Cardinal2D NORTHWEST 	= new Cardinal2D(-1,-1);
	/**
	 * The west {@code Cardinal2D} points along the negative x-axis.
	 */
	public static Cardinal2D WEST 		= new Cardinal2D(-1, 0);
	/**
	 * The southwest {@code Cardinal2D} points along the third bisector.
	 */
	public static Cardinal2D SOUTHWEST 	= new Cardinal2D(-1,+1);
	/**
	 * The south {@code Cardinal2D} points along the negative y-axis.
	 */
	public static Cardinal2D SOUTH 		= new Cardinal2D( 0,+1);
	/**
	 * The southeast {@code Cardinal2D} points along the fourth bisector.
	 */
	public static Cardinal2D SOUTHEAST 	= new Cardinal2D(+1,+1);
	
	
	private static Cardinal2D[] ALL = new Cardinal2D[]
	{CENTER, EAST, NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST};
	private static Cardinal2D[] DIAGONALS = new Cardinal2D[]
	{NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST};
	private static Cardinal2D[] PERPENDICULARS = new Cardinal2D[]
	{EAST, NORTH, SOUTH, WEST};
	
	/**
	 * Creates a new {@code Cardinal2D} in a given direction.
	 * 
	 * @param x  an x coordinate
	 * @param y  an y coordinate
	 * @return  a cardinal arrow
	 */
	public static Cardinal2D create(float x, float y)
	{
		if(x < 0)
		{
			if(y < 0)
				return NORTHWEST;
			if(y > 0)
				return SOUTHWEST;
			
			return WEST;
		}

		if(x > 0)
		{
			if(y < 0)
				return NORTHEAST;
			if(y > 0)
				return SOUTHEAST;
			
			return EAST;
		}
		
		if(y < 0)
			return NORTH;
		if(y > 0)
			return SOUTH;
		
		return CENTER;
	}
	
	/**
	 * Returns a perpendicular {@code Cardinal2D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal2D[] Perpendiculars()
	{
		return PERPENDICULARS;
	}

	/**
	 * Returns a diagonal {@code Cardinal2D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal2D[] Diagonals()
	{
		return DIAGONALS;
	}

	/**
	 * Returns a {@code Cardinal2D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal2D[] All()
	{
		return ALL;
	}
	
	
	/**
	 * Creates a new {@code Cardinal2D}.
	 * 
	 * @param x  an x coordinate
	 * @param y  an y coordinate
	 */
	protected Cardinal2D(int x, int y)
	{
		super(x, y);
	}

	/**
	 * Rotates the {@code Cardinal2D}.
	 * 
	 * @param d  a dial
	 * @return   a cardinal
	 * 
	 * 
	 * @see Dial
	 */
	public Cardinal2D spin(Dial d)
	{
		return (Cardinal2D) spin(Chirality.create(d));
	}
}