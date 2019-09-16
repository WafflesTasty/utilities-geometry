package zeno.util.geom.utilities.cardinal;

import zeno.util.algebra.linear.vector.fixed.Vector2;
import zeno.util.geom.collidables.bounds.IBounded2D;

/**
 * The {@code Cardinal2D} class defines all cardinal directions in two dimensions.
 * <br> All nine directions are available through static access.
 *
 * @author Zeno
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
	 * Returns the cardinal direction of a point relative to a rectangle.
	 * 
	 * @param rect  a rectangle to check
	 * @param point  a point to check
	 * @return  a relative cardinal
	 * @see IBounded2D
	 * @see Vector2
	 */
	public static Cardinal2D relativeTo(IBounded2D rect, Vector2 point)
	{	
		float xmin = rect.Bounds().XMin();
		float xmax = rect.Bounds().XMax();
		float ymin = rect.Bounds().YMin();
		float ymax = rect.Bounds().YMax();
		
		if(xmax < point.X())
		{
			if(ymax < point.Y())
			{
				return NORTHEAST;
			}
			
			if(ymin > point.Y())
			{
				return SOUTHEAST;
			}
			
			return EAST;
		}
		
		if(xmin > point.X())
		{
			if(ymax < point.Y())
			{
				return NORTHWEST;
			}
			
			if(ymin > point.Y())
			{
				return SOUTHWEST;
			}
			
			return WEST;
		}
		
		if(ymax < point.Y())
		{
			return NORTH;
		}
		
		if(ymin > point.Y())
		{
			return SOUTH;
		}
		
		return CENTER;
	}
		
	/**
	 * Returns an array that contains every perpendicular {@code Cardinal2D}.
	 * 
	 * @return an array of perpendicular directions
	 */
	public static Cardinal2D[] Perpendiculars()
	{
		return new Cardinal2D[]{EAST, NORTH, SOUTH, WEST};
	}
	
	/**
	 * Returns an array that contains every diagonal {@code Cardinal2D}.
	 * 
	 * @return an array of diagonal directions
	 */
	public static Cardinal2D[] Diagonals()
	{
		return new Cardinal2D[]{NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST};
	}
		
	/**
	 * Returns an array that contains every {@code Cardinal2D}.
	 * 
	 * @return  an array of cardinal directions
	 */
	public static Cardinal2D[] All()
	{
		return new Cardinal2D[]{CENTER, EAST, NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST};
	}
	
	
	private Cardinal2D(int x, int y)
	{
		super(x, y);
	}
}