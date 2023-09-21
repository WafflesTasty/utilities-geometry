package waffles.utils.geom.collidable.hulls;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.bounds.hulls.BNDHull2D;
import waffles.utils.geom.collidable.Geometry2D;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Hull2D} defines the convex hull of a finite set of points in two-dimensional space.
 *
 * @author Waffles
 * @since 23 Apr 2021
 * @version 1.0
 * 
 * 
 * @see Geometry2D
 * @see HullND
 */
public class Hull2D extends HullND implements Geometry2D
{
	/**
	 * Creates a new {@code Hull2D}.
	 * 
	 * @param pts  a set of points
	 * 
	 * 
	 * @see Vector2
	 */
	public Hull2D(Vector2... pts)
	{
		super(Matrices.concat(pts));
	}
	
	/**
	 * Creates a new {@code Hull2D}.
	 * 
	 * @param g  a generating matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Hull2D(Matrix g)
	{
		super(g);
	}
	
	
	@Override
	public Vector2 Generator(int i)
	{
		return (Vector2) super.Generator(i);
	}
	
	@Override
	public Bounds2D Bounds(GlobalMap map)
	{
		return (Bounds2D) super.Bounds(map);
	}
	
	@Override
	public Bounds2D Bounds()
	{
		return new BNDHull2D(this);
	}
}