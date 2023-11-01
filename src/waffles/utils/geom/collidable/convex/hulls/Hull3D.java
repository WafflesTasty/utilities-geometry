package waffles.utils.geom.collidable.convex.hulls;

import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.bounds.hulls.BNDHull3D;
import waffles.utils.geom.collidable.Geometry3D;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code Hull3D} defines the convex hull of a finite set of points in three-dimensional space.
 *
 * @author Waffles
 * @since 23 Apr 2021
 * @version 1.0
 * 
 * 
 * @see Geometry3D
 * @see HullND
 */
public class Hull3D extends HullND implements Geometry3D
{
	/**
	 * Creates a new {@code Hull3D}.
	 * 
	 * @param pts  a set of points
	 * 
	 * 
	 * @see Vector3
	 */
	public Hull3D(Vector3... pts)
	{
		super(pts);
	}
	
	/**
	 * Creates a new {@code Hull3D}.
	 * 
	 * @param g  a generating matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Hull3D(Matrix g)
	{
		super(g);
	}
	
	
	@Override
	public Vector3 Generator(int i)
	{
		return (Vector3) super.Generator(i);
	}
	
	@Override
	public Bounds3D Bounds(GlobalMap map)
	{
		return (Bounds3D) super.Bounds(map);
	}
	
	@Override
	public Bounds3D Bounds()
	{
		return new BNDHull3D(this);
	}
}