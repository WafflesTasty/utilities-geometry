package waffles.utils.geom.shapes.convex.hulls;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.shapes.Geometry2D;
import waffles.utils.geom.shapes.bounds.convex.hulls.BNDHull2D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds2D;

/**
 * A {@code HullND} implements two-dimensional {@code Hull} geometry.
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
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public Hull2D(Matrix s)
	{
		super(s);
	}
	
	/**
	 * Creates a new {@code Hull2D}.
	 * 
	 * @param set  a point set
	 * 
	 * 
	 * @see Point
	 */
	public Hull2D(Point... set)
	{
		super(set);
	}


	@Override
	public Bounds2D Bounds()
	{
		return new BNDHull2D(this);
	}
	
	@Override
	public int Dimension()
	{
		return 2;
	}
}