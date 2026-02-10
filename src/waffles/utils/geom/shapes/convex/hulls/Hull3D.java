package waffles.utils.geom.shapes.convex.hulls;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.Geometry3D;
import waffles.utils.geom.shapes.bounds.convex.hulls.BNDHull3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds3D;

/**
 * A {@code Hull3D} implements three-dimensional {@code Hull} geometry.
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
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public Hull3D(Matrix s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Hull3D}.
	 * 
	 * @param set  a point set
	 * 
	 * 
	 * @see Point
	 */
	public Hull3D(Point... set)
	{
		super(set);
	}


	@Override
	public Bounds3D Bounds()
	{
		return new BNDHull3D(this);
	}
	
	@Override
	public int Dimension()
	{
		return 3;
	}
}