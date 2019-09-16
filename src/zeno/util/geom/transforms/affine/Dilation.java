package zeno.util.geom.transforms.affine;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.types.banded.Diagonal;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;

/**
 * The {@code Dilation} interface defines an affine dilation.
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
public class Dilation implements ITransformation
{
	private static Point DefaultSize(int dim)
	{
		Vector v = Vectors.create(dim);
		for(int i = 0; i < dim; i++)
		{
			v.set(0.5f, i);
		}
		
		return new Point(v, 0f);
	}
	
	
	private Point size;
		
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param dim  a space dimension
	 */
	public Dilation(int dim)
	{
		size = DefaultSize(dim);
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * 
	 * @param s  a size vector
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
	 * </br> NOTE: The point provided should be part of the vector
	 * subset i.e. it is supposed to have a zero mass.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Point
	 */
	public Dilation(Point s)
	{
		size = s;
	}
		
	/**
	 * Returns the size point.
	 * 
	 * @return  a size point
	 * 
	 * 
	 * @see Point
	 */
	public Point Size()
	{
		return size;
	}
	

	@Override
	public Matrix Inverse(int dim)
	{
		if(size.Type() != Point.Type.VECTOR)
		{
			throw new Geometries.TypeError(size, Point.Type.VECTOR);
		}
		
		
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Diagonal.Type());
		for(int d = 0; d < dim; d++)
		{
			float s = size.get(d);
			if(!Floats.isZero(s, 1))
			{
				m.set(1f / s, d, d);
			}
		}
		
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		if(size.Type() != Point.Type.VECTOR)
		{
			throw new Geometries.TypeError(size, Point.Type.VECTOR);
		}
		
		
		Matrix m = Matrices.identity(dim + 1);
		m.setOperator(Diagonal.Type());
		for(int d = 0; d < dim; d++)
		{
			float s = size.get(d);
			if(!Floats.isZero(s, 1))
			{
				m.set(s, d, d);
			}
		}
		
		return m;
	}
}