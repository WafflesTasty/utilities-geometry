package waffles.utils.geom.shapes.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.fixed.CLSVoid;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid2D;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.utilities.Transformator;

/**
 * A {@code Void} defines a {@code Geometry} that contains nothing at all.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Transformator
 * @see Geometry
 */
public class Void implements Geometry, Transformator
{
	/**
	 * A {@code Void.Factory} generates {@code Void} geometry.
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
			return new Void(m.Rows());
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
	 * Creates a new {@code Void}.
	 * 
	 * @param d  a space dimension
	 */
	public Void(int d)
	{
		dim = d;
	}
	
	
	@Override
	public Collision Collision()
	{
		return new CLSVoid(this);
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
	
	
	@Override
	public Bounds Bounds()
	{
		if(Dimension() == 2)
			return new BNDVoid2D(this);
		if(Dimension() == 3)
			return new BNDVoid3D(this);

		return new BNDVoid(this);
	}

	@Override
	public int Dimension()
	{
		return dim;
	}
}