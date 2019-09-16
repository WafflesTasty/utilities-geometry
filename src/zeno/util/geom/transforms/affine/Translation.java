package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.transforms.types.Translator;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code Translation} class defines an affine translation.
 * It is defined as a homogeneous linear transformation
 * through the {@code ITransformation} interface.
 * 
 * @author Zeno
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see ITransformation
 */
public class Translation implements ITransformation
{
	private static Point DefaultOrigin(int dim)
	{
		return new Point(Vectors.create(dim), 1f);
	}
	
	
	private Point origin;
	
	/**
	 * Creates a new {@code Translation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Translation(int dim)
	{
		this(DefaultOrigin(dim));
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
	 * </br> NOTE: The point provided should be part of the affine
	 * subset i.e. it is supposed to have a non-zero mass.
	 * 
	 * @param p  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Translation(Point p)
	{
		origin = p;
	}
		

	/**
	 * Returns the origin point.
	 * 
	 * @return  an origin point
	 * 
	 * 
	 * @see Point
	 */
	public Point Origin()
	{
		return origin;
	}
	
	
	@Override
	public Matrix Inverse(int dim)
	{
		if(origin.Type() != Point.Type.AFFINE)
		{
			throw new Geometries.TypeError(origin, Point.Type.AFFINE);
		}
		
		
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Translator.Type());
		for(int d = 0; d <= dim; d++)
		{
			m.set(origin.Mass(), d, d);
			if(d < dim)
			{
				m.set(-origin.get(d), d, dim);
			}
		}
		
		return m;
	}

	@Override
	public Matrix Matrix(int dim)
	{
		if(origin.Type() != Point.Type.AFFINE)
		{
			throw new Geometries.TypeError(origin, Point.Type.AFFINE);
		}
		
		
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Translator.Type());
		for(int d = 0; d <= dim; d++)
		{
			m.set(origin.Mass(), d, d);
			if(d < dim)
			{
				m.set(origin.get(d), d, dim);
			}
		}
		
		return m;
	}
}