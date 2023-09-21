package waffles.utils.geom.maps.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.banded.Diagonal;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.Spatial;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code Dilation} defines a linear map which scales vectors
 * in every direction. Its corresponding matrices are diagonal.
 *
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see LinearMap
 */
public class Dilation implements LinearMap
{
	/**
	 * Returns a default dilation {@code Vector}.
	 * 
	 * @param dim  a space dimension
	 * @return  a default vector
	 * 
	 * 
	 * @see Vector
	 */
	public static Vector Default(int dim)
	{
		Vector v = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			v.set(1f, i);
		}
		
		return v;
	}
	
	
	private Point size;
		
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param dim  a default dimension
	 */
	public Dilation(int dim)
	{
		this(Default(dim));
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param s  a default size
	 * 
	 * 
	 * @see Vector
	 */
	public Dilation(Vector s)
	{
		this(new Point(s, 0f));
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * The size of the source is divided
	 * by two, since the map scales in
	 * both directions of the axes.
	 * 
	 * @param s  a spatial source
	 * 
	 * 
	 * @see Spatial
	 */
	public Dilation(Spatial s)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		this(s.Size().times(0.5f));
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * The point provided is preferably
	 * of zero mass, i.e. a vector.
	 * 
	 * @param s  a size point
	 * 
	 * 
	 * @see Point
	 */
	public Dilation(Point s)
	{
		size = s;
	}
		
	/**
	 * Returns the size {@code Vector}.
	 * 
	 * @return  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Size()
	{
		return size.Generator();
	}
	

	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim);		
		m.setOperator(Diagonal.Type());
		
		for(int d = 0; d < dim; d++)
		{
			float s = size.get(d);
			if(!Floats.isZero(s, 1))
			{
				m.set(1f / s, d, d);
			}
		}
		
		float mass = size.Mass();
		if(!Floats.isZero(mass, 1))
		{
			m.set(1f / mass, dim-1, dim-1);
		}
		
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Diagonal.Type());
		
		for(int d = 0; d < dim; d++)
		{
			float s = size.get(d);
			if(!Floats.isZero(s, 1))
			{
				m.set(s, d, d);
			}
		}
		
		float mass = size.Mass();
		if(!Floats.isZero(mass, 1))
		{
			m.set(mass, dim-1, dim-1);
		}		
		
		return m;
	}
}