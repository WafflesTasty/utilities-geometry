package waffles.utils.geom.spatial.maps.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.banded.Diagonal;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.unary.Scaled;
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
 * @see Scaled
 */
public class Dilation implements LinearMap, Scaled
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
	
	
	private Scaled src;
		
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
		this(() -> s.times(2f));
	}
	
	/**
	 * Creates a new {@code Dilation}.
	 * The size of the source is divided
	 * by two, since the map scales in
	 * both directions of the axes.
	 * 
	 * @param s  a scaled source
	 * 
	 * 
	 * @see Scaled
	 */
	public Dilation(Scaled s)
	{
		src = s;
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
		this((Vector) s.Generator());
	}
		

	@Override
	public Matrix Inverse(int dim)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		Vector size = Size().times(0.5f);
		Matrix m = Matrices.identity(dim);		
		m.setOperator(Diagonal.Type());
		int sDim = Size().Size();
		
		for(int d = 0; d < dim; d++)
		{
			if(d < sDim)
			{
				float s = size.get(d);
				if(!Floats.isZero(s, 1))
				{
					m.set(1f / s, d, d);
				}	
			}
		}

		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		// Divided by two because it scales in both
		// the positive and negative direction of axes.
		Vector size = Size().times(0.5f);
		Matrix m = Matrices.identity(dim);
		m.setOperator(Diagonal.Type());
		int sDim = Size().Size();
		
		for(int d = 0; d < dim; d++)
		{
			if(d < sDim)
			{
				float s = size.get(d);
				if(!Floats.isZero(s, 1))
				{
					m.set(s, d, d);
				}	
			}
		}
		
		return m;
	}
	
	@Override
	public Vector Size()
	{
		return src.Size();
	}
}