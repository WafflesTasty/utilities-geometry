package waffles.utils.geom.shapes.fixed;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.fixed.CLSVoid;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid2D;
import waffles.utils.geom.shapes.bounds.fixed.BNDVoid3D;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code Void} defines a {@code Geometry} that contains nothing at all.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Affine
 */
public class Void implements Affine, Geometry
{
	/**
	 * A {@code Void.Factory} handles affine maps of {@code Void} geometry.
	 *
	 * @author Waffles
	 * @since 21 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Affine
	 */
	public class Factory implements Affine.Factory
	{
		@Override
		public Affine create(Matrix... set)
		{
			if(set.length > 0)
			{
				int n = set[0].Rows();
				return new Void(n);
			}
			
			return null;
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
	 * @param n  a space dimension
	 */
	public Void(int n)
	{
		dim = n;
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
	public Bounds Bounds(LinearMap m)
	{
		return Bounds();
	}
	
	@Override
	public int Dimension()
	{
		return dim;
	}
	
	
	@Override
	public CLSVoid Collision()
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
}