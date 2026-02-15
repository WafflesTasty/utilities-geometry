package waffles.utils.geom.utilities.chiral.arrows;

import waffles.utils.geom.utilities.chiral.Chirality;
import waffles.utils.geom.utilities.chiral.Dial;

/**
 * A {@code Cardinal3D} defines a three-dimensional cardinal arrow.
 *
 * @author Waffles
 * @since 15 Feb 2026
 * @version 1.1
 *
 * 
 * @see Cardinal
 */
public class Cardinal3D extends Cardinal
{
	/**
	 * The up {@code Cardinal3D} points along the positive y-axis.
	 */
	public static Cardinal3D UP			 		= new Cardinal3D( 0, 0, 1);
	/**
	 * The upper east {@code Cardinal3D} points up along the positive x-axis.
	 */
	public static Cardinal3D UPPER_EAST 		= new Cardinal3D( 1, 0, 1);
	/**
	 * The upper southeast {@code Cardinal3D} points up along the first bisector.
	 */
	public static Cardinal3D UPPER_NORTHEAST 	= new Cardinal3D( 1,-1, 1);
	/**
	 * The upper south {@code Cardinal3D} points up along the positive z-axis.
	 */
	public static Cardinal3D UPPER_NORTH 		= new Cardinal3D( 0,-1, 1);
	/**
	 * The upper southwest {@code Cardinal3D} points up along the second bisector.
	 */
	public static Cardinal3D UPPER_NORTHWEST 	= new Cardinal3D(-1,-1, 1);
	/**
	 * The upper west {@code Cardinal3D} points up along the negative x-axis.
	 */
	public static Cardinal3D UPPER_WEST 		= new Cardinal3D(-1, 0, 1);
	/**
	 * The upper northwest {@code Cardinal3D} points up along the third bisector.
	 */
	public static Cardinal3D UPPER_SOUTHWEST 	= new Cardinal3D(-1, 1, 1);
	/**
	 * The upper north {@code Cardinal3D} points up along the negative z-axis.
	 */
	public static Cardinal3D UPPER_SOUTH 		= new Cardinal3D( 0, 1, 1);
	/**
	 * The upper northeast {@code Cardinal3D} points up along the fourth bisector.
	 */
	public static Cardinal3D UPPER_SOUTHEAST 	= new Cardinal3D( 1, 1, 1);


	/**
	 * The down {@code Cardinal3D} points along the negative y-axis.
	 */
	public static Cardinal3D DOWN		 		= new Cardinal3D( 0, 0,-1);
	/**
	 * The lower east {@code Cardinal3D} points down along the positive x-axis.
	 */
	public static Cardinal3D LOWER_EAST 		= new Cardinal3D( 1, 0,-1);
	/**
	 * The lower southeast {@code Cardinal3D} points down along the first bisector.
	 */
	public static Cardinal3D LOWER_NORTHEAST 	= new Cardinal3D( 1,-1,-1);
	/**
	 * The lower south {@code Cardinal3D} points down along the positive z-axis.
	 */
	public static Cardinal3D LOWER_NORTH 		= new Cardinal3D( 0,-1,-1);
	/**
	 * The lower southwest {@code Cardinal3D} points down along the second bisector.
	 */
	public static Cardinal3D LOWER_NORTHWEST 	= new Cardinal3D(-1,-1,-1);
	/**
	 * The lower west {@code Cardinal3D} points down along the negative x-axis.
	 */
	public static Cardinal3D LOWER_WEST 		= new Cardinal3D(-1, 0,-1);
	/**
	 * The lower northwest {@code Cardinal3D} points down along the third bisector.
	 */
	public static Cardinal3D LOWER_SOUTHWEST 	= new Cardinal3D(-1,+1,-1);
	/**
	 * The lower north {@code Cardinal3D} points down along the negative z-axis.
	 */
	public static Cardinal3D LOWER_SOUTH 		= new Cardinal3D( 0,+1,-1);
	/**
	 * The lower northeast {@code Cardinal3D} points down along the fourth bisector.
	 */
	public static Cardinal3D LOWER_SOUTHEAST 	= new Cardinal3D( 1,+1,-1);


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
	public static Cardinal3D NORTHEAST 			= new Cardinal3D( 1, 1, 0);
	/**
	 * The south {@code Cardinal3D} points straight along the positive z-axis.
	 */
	public static Cardinal3D NORTH 				= new Cardinal3D( 0, 1, 0);
	/**
	 * The southwest {@code Cardinal3D} points straight along the second bisector.
	 */
	public static Cardinal3D NORTHWEST 			= new Cardinal3D(-1, 1, 0);
	/**
	 * The west {@code Cardinal3D} points straight along the negative x-axis.
	 */
	public static Cardinal3D WEST 				= new Cardinal3D(-1, 0, 0);
	/**
	 * The northwest {@code Cardinal3D} points straight along the third bisector.
	 */
	public static Cardinal3D SOUTHWEST 			= new Cardinal3D(-1,-1, 0);
	/**
	 * The north {@code Cardinal3D} points straight along the negative z-axis.
	 */
	public static Cardinal3D SOUTH 				= new Cardinal3D( 0,-1, 0);
	/**
	 * The northeast {@code Cardinal3D} points straight along the fourth bisector.
	 */
	public static Cardinal3D SOUTHEAST 			= new Cardinal3D( 1,-1, 0);
	
	
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
	private static Cardinal3D[] PERPENDICULARS = new Cardinal3D[]
	{UP, EAST, NORTH, SOUTH, WEST, DOWN};
	
	/**
	 * Creates a new {@code Cardinal3D} in a given direction.
	 * 
	 * @param x  an x coordinate
	 * @param y  an y coordinate
	 * @param z  an z coordinate
	 * @return  a cardinal arrow
	 */
	public static Cardinal3D create(float x, float y, float z)
	{
		if(x < 0)
		{
			if(y < 0)
			{
				if(z < 0)
					return LOWER_NORTHWEST;
				if(z > 0)
					return UPPER_NORTHWEST;
				
				return NORTHWEST;
			}
			
			if(y > 0)
			{
				if(z < 0)
					return LOWER_SOUTHWEST;
				if(z > 0)
					return UPPER_SOUTHWEST;
				
				return SOUTHWEST;
			}
			
			if(z < 0)
				return LOWER_WEST;
			if(z > 0)
				return UPPER_WEST;
			
			return WEST;
		}
		
		if(x > 0)
		{
			if(y < 0)
			{
				if(z < 0)
					return LOWER_NORTHEAST;
				if(z > 0)
					return UPPER_NORTHEAST;
				
				return NORTHEAST;
			}
			
			if(y > 0)
			{
				if(z < 0)
					return LOWER_SOUTHEAST;
				if(z > 0)
					return UPPER_SOUTHEAST;
				
				return SOUTHEAST;
			}
			
			if(z < 0)
				return LOWER_EAST;
			if(z > 0)
				return UPPER_EAST;
			
			return EAST;
		}

		if(y < 0)
		{
			if(z < 0)
				return LOWER_NORTH;
			if(z > 0)
				return UPPER_NORTH;
			
			return NORTH;
		}
		
		if(y > 0)
		{
			if(z < 0)
				return LOWER_SOUTH;
			if(z > 0)
				return UPPER_SOUTH;
			
			return SOUTH;
		}
		
		if(z < 0)
			return DOWN;
		if(z > 0)
			return UP;

		return CENTER;
	}
	
	/**
	 * Returns a perpendicular {@code Cardinal3D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal3D[] Perpendiculars()
	{
		return PERPENDICULARS;
	}

	/**
	 * Returns a diagonal {@code Cardinal3D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal3D[] Diagonals()
	{
		return DIAGONALS;
	}

	/**
	 * Returns a {@code Cardinal3D} array.
	 *
	 * @return  a cardinal array
	 */
	public static Cardinal3D[] All()
	{
		return ALL;
	}
	
	
	/**
	 * Creates a new {@code Cardinal3D}.
	 * 
	 * @param x  an x coordinate
	 * @param y  an y coordinate
	 * @param z  an z coordinate
	 */
	protected Cardinal3D(int x, int y, int z)
	{
		super(x, y, z);
	}

	/**
	 * Rotates the {@code Cardinal3D}.
	 * 
	 * @param d1  a dial
	 * @param d2  a dial
	 * @return    a cardinal
	 * 
	 * 
	 * @see Dial
	 */
	public Cardinal3D spin(Dial d1, Dial d2)
	{
		return (Cardinal3D) spin(Chirality.create(d1, d2));
	}
}