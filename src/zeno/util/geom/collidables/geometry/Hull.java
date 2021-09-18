package zeno.util.geom.collidables.geometry;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.geometry.generic.IHull;

/**
 * The {@code Hull} class defines the convex hull of a finite set of points.
 *
 * @author Waffles
 * @since 23 Apr 2021
 * @version 1.0
 * 
 * 
 * @see IHull
 */
public class Hull implements IHull
{
	private Matrix span;
	private Vector min, max;
	private Float radius;
	
	/**
	 * Creates a new {@code Hull}.
	 * 
	 * @param pts  a set of points
	 * 
	 * 
	 * @see Vector
	 */
	public Hull(Vector... pts)
	{
		this(Matrices.concat(pts));
	}
	
	/**
	 * Creates a new {@code Hull}.
	 * 
	 * @param span  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public Hull(Matrix span)
	{
		this.span = span;
	}
	
	
	@Override
	public Matrix Vertices()
	{
		return span;
	}	

	// Obligatory bounds overrides.
	
	@Override
	public Vector Minimum()
	{
		if(min == null)
		{
			min = IHull.super.Minimum();
		}
		
		return min;
	}
	
	@Override
	public Vector Maximum()
	{
		if(max == null)
		{
			max = IHull.super.Maximum();
		}
		
		return max;
	}
	
	@Override
	public float Radius()
	{
		if(radius == null)
		{
			radius = IHull.super.Radius();
		}
		
		return radius;
	}
}