package waffles.utils.geom.collidable.fixed;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.fixed.BNDVoid;
import waffles.utils.geom.bounds.fixed.BNDVoid2D;
import waffles.utils.geom.bounds.fixed.BNDVoid3D;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collision.CLSVoid;
import waffles.utils.geom.maps.GlobalMap;

/**
 * A {@code Void} defines a geometry that contains nothing at all.
 *
 * @author Waffles
 * @since 12 May 2021
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Affine
 */
public class Void implements Geometry, Affine
{
	private int dim;
	
	/**
	 * Creates a new {@code Void}.
	 * 
	 * @param dim  a space dimension
	 */
	public Void(int dim)
	{
		this.dim = dim;
	}
	
	
	@Override
	public int Dimension()
	{
		return dim;
	}
	
	@Override
	public CLSVoid Collisions()
	{
		return new CLSVoid(this);
	}
	
	@Override
	public Bounds Bounds(GlobalMap map)
	{
		return Bounds();
	}

	@Override
	public Bounds Bounds()
	{
		if(dim == 2)
			return new BNDVoid2D(this);
		if(dim == 3)
			return new BNDVoid3D(this);

		return new BNDVoid(this);
	}
		
	
	@Override
	public Factory Factory()
	{
		return m ->
		{
			int rows = m.Rows();
			return new Void(rows-1);
		};
	}
	
	@Override
	public Matrix Span()
	{
		int dim = Dimension();
		return Matrices.create(dim+1, 0);
	}
}