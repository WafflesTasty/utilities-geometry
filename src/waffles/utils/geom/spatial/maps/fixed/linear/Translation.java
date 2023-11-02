package waffles.utils.geom.spatial.maps.fixed.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.unary.Positioned;
import waffles.utils.geom.spatial.maps.fixed.linear.matrix.Translator;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Translation} defines a linear map which
 * translates vectors in a single direction.
 * 
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see Positioned
 * @see LinearMap
 */
public class Translation implements LinearMap, Positioned
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


	private Positioned src;
	
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
	 * @param s  a positioned source
	 * 
	 * 
	 * @see Positioned
	 */
	public Translation(Positioned s)
	{
		src = s;
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
		this(() -> v);
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
		this(() -> o.Generator());
	}


	@Override
	public Matrix Inverse(int dim)
	{
		Vector o = Origin();
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			if(d < Integers.min(dim-1, o.Size()))
			{
				m.set(-o.get(d), d, dim-1);
			}
		}
		
		return m;
	}

	@Override
	public Matrix Matrix(int dim)
	{
		Vector o = Origin();
		Matrix m = Matrices.identity(dim);
		m.setOperator(Translator.Type());
		
		for(int d = 0; d < dim; d++)
		{
			if(d < Integers.min(dim-1, o.Size()))
			{
				m.set(o.get(d), d, dim-1);
			}
		}
		
		return m;
	}
	
	@Override
	public Vector Origin()
	{
		return src.Origin();
	}
}