package waffles.utils.geom.collision.hulls;

import waffles.utils.geom.collidable.hulls.triangles.ITriangle;

/**
 * The {@code CLSTriangle} class defines collision responses for {@code ITriangle} objects.
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
	 * @see ITriangle
	 */
	public CLSTriangle(ITriangle s)
	{
		super(s);
	}
	
		
	@Override
	public ITriangle Source()
	{
		return (ITriangle) super.Source();
	}
}