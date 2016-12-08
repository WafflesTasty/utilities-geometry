package zeno.util.geom.tools.cardinal;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.tools.bounds.IBound3D;

/**
 * The {@code Cardinal3D} class defines all cardinal directions in three dimensions.
 * <br> All 27 directions are available through static access.
 *
 * @since Apr 30, 2016
 * @author Zeno
 * 
 * @see Vector3
 */
public final class Cardinal3D extends Vector3
{
	/**
	 * The up {@code Cardinal3D} points along the positive z-axis.
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
	 * The down {@code Cardinal3D} points along the negative z-axis.
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
	 * Returns the cardinal direction of a point relative to a cuboid.
	 * 
	 * @param cube  a cuboid to check
	 * @param point  a point to check
	 * @return  a relative cardinal
	 * @see IBound3D
	 * @see Vector3
	 */
	public static Cardinal3D relativeTo(IBound3D cube, Vector3 point)
	{	
		float xmin = cube.XMin();
		float xmax = cube.XMax();
		float ymin = cube.YMin();
		float ymax = cube.YMax();
		float zmin = cube.ZMin();
		float zmax = cube.ZMax();
		
		if(xmax < point.X())
		{
			if(ymax < point.Y())
			{
				if(zmax < point.Z())
				{
					return UPPER_NORTHEAST;
				}
				
				if(zmin > point.Z())
				{
					return LOWER_NORTHEAST;
				}
				
				return NORTHEAST;
			}
			
			if(ymin > point.Y())
			{
				if(zmax < point.Z())
				{
					return UPPER_SOUTHEAST;
				}
				
				if(zmin > point.Z())
				{
					return LOWER_SOUTHEAST;
				}

				return SOUTHEAST;
			}
			
			if(zmax < point.Z())
			{
				return UPPER_EAST;
			}
			
			if(zmin > point.Z())
			{
				return LOWER_EAST;
			}
			
			return EAST;
		}
		
		if(xmin > point.X())
		{
			if(ymax < point.Y())
			{
				if(zmax < point.Z())
				{
					return UPPER_NORTHWEST;
				}
				
				if(zmin > point.Z())
				{
					return LOWER_NORTHWEST;
				}

				return NORTHWEST;
			}
			
			if(ymin > point.Y())
			{
				if(zmax < point.Z())
				{
					return UPPER_SOUTHWEST;
				}
				
				if(zmin > point.Z())
				{
					return LOWER_SOUTHWEST;
				}

				return SOUTHWEST;
			}
			
			if(zmax < point.Z())
			{
				return UPPER_WEST;
			}
			
			if(zmin > point.Z())
			{
				return LOWER_WEST;
			}

			return WEST;
		}
		
		if(ymax < point.Y())
		{
			if(zmax < point.Z())
			{
				return UPPER_NORTH;
			}
			
			if(zmin > point.Z())
			{
				return LOWER_NORTH;
			}

			return NORTH;
		}
		
		if(ymin > point.Y())
		{
			if(zmax < point.Z())
			{
				return UPPER_SOUTH;
			}
			
			if(zmin > point.Z())
			{
				return LOWER_SOUTH;
			}

			return SOUTH;
		}
		
		if(zmax < point.Z())
		{
			return UP;
		}
		
		if(zmin > point.Z())
		{
			return DOWN;
		}

		return CENTER;
	}
		
	/**
	 * Returns an array that contains every perpendicular {@code Cardinal3D}.
	 * 
	 * @return an array of perpendicular directions
	 */
	public static Cardinal3D[] getPerpendiculars()
	{
		return new Cardinal3D[]{EAST, NORTH, SOUTH, WEST};
	}
	
	/**
	 * Returns an array that contains every diagonal {@code Cardinal3D}.
	 * 
	 * @return an array of diagonal directions
	 */
	public static Cardinal3D[] getDiagonals()
	{
		return new Cardinal3D[]{NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST};
	}
		
	/**
	 * Returns an array that contains every {@code Cardinal3D}.
	 * 
	 * @return  an array of cardinal directions
	 */
	public static Cardinal3D[] getAll()
	{
		return new Cardinal3D[]
		{
			UP, UPPER_EAST, UPPER_NORTHEAST, UPPER_NORTH, UPPER_NORTHWEST, UPPER_WEST, UPPER_SOUTHWEST, UPPER_SOUTH, UPPER_SOUTHEAST,
			DOWN, LOWER_EAST, LOWER_NORTHEAST, LOWER_NORTH, LOWER_NORTHWEST, LOWER_WEST, LOWER_SOUTHWEST, LOWER_SOUTH, LOWER_SOUTHEAST,
			CENTER, EAST, NORTHEAST, NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST
		};
	}
	
	
	private Cardinal3D(int x, int y, int z)
	{
		super(x, y, z);
	}
}