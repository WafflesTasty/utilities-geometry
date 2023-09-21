package waffles.utils.geom.maps.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.maps.linear.types.Translator;
import waffles.utils.geom.spatial.Spatial;

/**
 * A {@code Translation} defines a linear map which
 * translates vectors in a single direction.
 * 
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see LinearMap
 */
public class Translation implements LinearMap
{
	/**
	 * Returns a default translation {@code Vector}.
	 * 
	 * @param dim  a space dimension
	 * @return  a default vector
	 * 
	 * 
	 * @see Vector
	 */
	public static Vector Default(int dim)
	{
		return Vectors.create(dim);
	}


	private Point origin;
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param dim  a default dimension
	 */
	public Translation(int dim)
	{
		this(Default(dim));
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param s  a spatial source
	 * 
	 * 
	 * @see Spatial
	 */
	public Translation(Spatial s)
	{
		this(s.Origin());
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param v  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Translation(Vector v)
	{
		this(new Point(v, 1f));
	}
	
	/**
	 * Creates a new {@code Translation}.
	 * The point provided is preferably of
	 * non-zero mass, i.e. affine.
	 * 
	 * @param o  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Translation(Point o)
	{
		origin = o;
	}
		
	/**
	 * Returns the origin {@code Vector}.
	 * 
	 * @return  an origin vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Origin()
	{
		return origin.Generator();
	}
	
	
	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			m.set(origin.Mass(), d, d);
			if(d < dim-1)
			{
				m.set(-origin.get(d), d, dim-1);
			}
		}
		
		return m;
	}

	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			m.set(origin.Mass(), d, d);
			if(d < dim-1)
			{
				m.set(origin.get(d), d, dim-1);
			}
		}
		
		return m;
	}
}