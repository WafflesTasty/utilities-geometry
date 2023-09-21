package waffles.utils.geom.collidable.hulls;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;

/**
 * A {@code HullND} defines the convex hull of a finite set of points in n-dimensional space.
 *
 * @author Waffles
 * @since 23 Apr 2021
 * @version 1.0
 * 
 * 
 * @see IHull
 */
public class HullND implements IHull
{
	private Matrix gen;
	
	/**
	 * Creates a new {@code HullND}.
	 * 
	 * @param pts  a set of points
	 * 
	 * 
	 * @see Vector
	 */
	public HullND(Vector... pts)
	{
		this(Matrices.concat(pts));
	}
	
	/**
	 * Creates a new {@code Hull2D}.
	 * 
	 * @param g  a generating matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public HullND(Matrix g)
	{
		gen = g;
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof IHull)
		{
			IHull h = (IHull) o;
			Matrix g = h.Generator();
			return gen.equals(g, 3);
		}

		return false;
	}
	
	@Override
	public Matrix Generator()
	{
		return gen;
	}	
}