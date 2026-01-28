package waffles.utils.geom.shapes.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.fixed.CLSUniverse;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse2D;
import waffles.utils.geom.shapes.bounds.fixed.BNDUniverse3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.utilities.Transformator;

/**
 * A {@code Universe} defines a {@code Geometry} that contains everything.
 *
 * @author Waffles
 * @since 02 Jan 2022
 * @version 1.0
 *
 *
 * @see Transformator
 * @see Geometry
 */
public class Universe implements Geometry, Transformator
{
	/**
	 * A {@code Void.Factory} generates {@code Universe} geometry.
	 *
	 * @author Waffles
	 * @since 21 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Transformator
	 */
	public class Factory implements Transformator.Factory
	{
		@Override
		public Transformator create(Matrix m)
		{
			return new Universe(m.Rows());
		}

		@Override
		public Matrix Span()
		{
			int n = Dimension() + 1;
			return Matrices.create(n, 0);
		}
	}
	
	
	private int dim;

	/**
	 * Creates a new {@code Universe}.
	 *
	 * @param d  a space dimension
	 */
	public Universe(int d)
	{
		dim = d;
	}


	@Override
	public Bounds Bounds()
	{
		if(Dimension() == 2)
			return new BNDUniverse2D(this);
		if(Dimension() == 3)
			return new BNDUniverse3D(this);

		return new BNDUniverse(this);
	}

	@Override
	public Bounds Bounds(LinearMap map)
	{
		return Bounds();
	}

	@Override
	public int Dimension()
	{
		return dim;
	}
	

	@Override
	public CLSUniverse Collision()
	{
		return new CLSUniverse(this);
	}
	
	@Override
	public Factory Factory()
	{
		return new Factory();
	}
	
	@Override
	public Point Origin()
	{
		return new Point(dim);
	}
}