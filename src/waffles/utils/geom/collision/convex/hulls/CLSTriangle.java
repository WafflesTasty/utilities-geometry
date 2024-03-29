package waffles.utils.geom.collision.convex.hulls;

import waffles.utils.geom.collidable.convex.hulls.triangles.Triangle;

/**
 * The {@code CLSTriangle} class defines collision responses for {@code Triangle} objects.
 *
 * @author Waffles
 * @since Jul 23, 2019
 * @version 1.0
 * 
 * 
 * @see CLSHull
 */
public class CLSTriangle extends CLSHull
{		
	/**
	 * Creates a new {@code CLSTriangle}.
	 * 
	 * @param s  a source triangle
	 * 
	 * 
	 * @see Triangle
	 */
	public CLSTriangle(Triangle s)
	{
		super(s);
	}
	
		
	@Override
	public Triangle Source()
	{
		return (Triangle) super.Source();
	}
}