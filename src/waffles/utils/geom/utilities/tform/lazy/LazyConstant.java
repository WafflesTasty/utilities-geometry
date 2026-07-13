package waffles.utils.geom.utilities.tform.lazy;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.matrix.LazyMatrix;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code LazyConstant} defines a {@code LazyMatrix} that spawn a constant matrix.
 *
 * @author Waffles
 * @since 01 Oct 2025
 * @version 1.1
 *
 *
 * @see LazyMatrix
 */
public class LazyConstant extends LazyMatrix
{
	private Point cns;
	
	/**
	 * Creates a new {@code LazyConstant}.
	 * 
	 * @param c  a point constant
	 * 
	 * 
	 * @see Point
	 */
	public LazyConstant(Point c)
	{
		cns = c;
	}
	
	
	@Override
	public Matrix compute(Integer d)
	{
		Matrix m = Matrices.create(d, d);
		m.set(cns.Mass(), d - 1, d - 1);
		for(int k = 0; k < d - 1; k++)
		{
			float v = cns.hom(k);
			m.set(v, k, d - 1);
		}
		
		return m;
	}
}