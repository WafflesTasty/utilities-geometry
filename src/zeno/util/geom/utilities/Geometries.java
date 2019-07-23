package zeno.util.geom.utilities;

import zeno.util.geom.ICollidable;

/**
 * The {@code Geometries} class defines static-access geometry operations.
 *
 * @author Zeno
 * @since Jul 23, 2019
 * @version 1.0
 */
public final class Geometries
{
	/**
	 * The {@code EqualityError} defines an error thrown when
	 * {@link ICollidable#equals(ICollidable, int)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class EqualityError extends RuntimeException
	{
		private static final long serialVersionUID = 8869287804826489223L;

		
		/**
		 * Creates a new {@code EqualityError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public EqualityError(ICollidable c1, ICollidable c2)
		{
			super("Equality not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code IntersectingError} defines an error thrown when
	 * {@link ICollidable#intersects(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class IntersectingError extends RuntimeException
	{
		private static final long serialVersionUID = -1595472890688569318L;

		
		/**
		 * Creates a new {@code IntersectingError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public IntersectingError(ICollidable c1, ICollidable c2)
		{
			super("Intersection not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code IntersectionError} defines an error thrown when
	 * {@link ICollidable#intersect(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class IntersectionError extends RuntimeException
	{
		private static final long serialVersionUID = 5708078836030861842L;

		
		/**
		 * Creates a new {@code IntersectionError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public IntersectionError(ICollidable c1, ICollidable c2)
		{
			super("Intersection not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	/**
	 * The {@code ContainError} defines an error thrown when
	 * {@link ICollidable#contains(ICollidable)} has not been implemented.
	 *
	 * @author Zeno
	 * @since Jul 23, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see RuntimeException
	 */
	public static class ContainError extends RuntimeException
	{		
		private static final long serialVersionUID = -4374565720733619149L;
		

		/**
		 * Creates a new {@code ContainError}.
		 * 
		 * @param c1  a collidable object
		 * @param c2  a collidable object
		 * 
		 * 
		 * @see ICollidable
		 */
		public ContainError(ICollidable c1, ICollidable c2)
		{
			super("Containment not implemented for (" + c1 + ", " + c2 + ").");
		}
	}
	
	
	private Geometries()
	{
		// NOT APPLICABLE
	}
}