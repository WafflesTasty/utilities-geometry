package waffles.utils.geom.spatial.maps.linear;

import waffles.utils.algebra.elements.linear.LinearMap;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.unary.Projected;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Projection} defines a linear transformation which
 * projects vectors towards an oculus vector. An oculus is typically
 * defined as a set of zeroes denoting the dimensions that are being
 * kept, followed by negative oculi values which define the
 * vanishing points on every axis of projection.
 *
 * @author Waffles
 * @since Sep 26, 2018
 * @version 1.0
 * 
 * 
 * @see LinearMap
 * @see Projected
 */
public class Projection implements LinearMap, Projected
{
	/**
	 * Returns a default oculus {@code Vector}.
	 * 
	 * @param iDim  an input dimension
	 * @param oDim  an output dimension
	 * @return  a default vector
	 * 
	 * 
	 * @see Vector
	 */
	public static Vector Default(int iDim, int oDim)
	{
		Vector v = Vectors.create(iDim);
		for(int i = oDim; i < iDim; i++)
		{
			v.set(Floats.NEG_INFINITY, i);
		}
		
		return v;
	}

	
	private Projected src;
		
	/**
	 * Creates a new {@code Projection}.
	 * 
	 * @param v  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public Projection(Vector v)
	{
		this(() -> v);
	}
	
	/**
	 * Creates a new {@code Projection}.
	 * 
	 * @param s  a projected source
	 * 
	 * 
	 * @see Projected
	 */
	public Projection(Projected s)
	{
		src = s;
	}

	/**
	 * Creates a new {@code Projection}.
	 * 
	 * @param iDim  a   projected space dimension
	 * @param oDim  a surrounding space dimension
	 */
	public Projection(int iDim, int oDim)
	{
		this(Default(iDim, oDim));
	}
	
	/**
	 * Creates a new {@code Projection}.
	 * The point provided is preferably of
	 * zero mass, i.e. a vector.
	 * 
	 * @param o  an oculus point
	 * 
	 * 
	 * @see Point
	 */
	public Projection(Point o)
	{
		this((Vector) o.Generator());
	}

	
	float product(int index)
	{
		Vector o = src.Oculus();
		
		float product = 1f;
		for(int i = 0; i < o.Size(); i++)
		{
			if(i == index) continue;
			
			float val = -o.get(i);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					product *= val;
				}
			}
		}
		
		return product;
	}
				
	@Override
	public Matrix Inverse(int dim)
	{
		Vector o = src.Oculus();	
		Matrix m = Matrices.identity(dim);
		
		float prod = 1f / product(-1);
		for(int d = 0; d < dim; d++)
		{
			float val = 0f;
			if(d < Integers.min(dim-1, o.Size()))
			{
				val = -o.get(d);
			}
			
			m.set(prod, d, d);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					m.set(prod / val, dim-1, d);
				}
			}
		}
		
		return m;
	}
	
	@Override
	public Matrix Matrix(int dim)
	{
		Vector o = src.Oculus();
		Matrix m = Matrices.identity(dim);
		
		float prod = product(-1);
		for(int d = 0; d < dim; d++)
		{
			float val = 0f;
			if(d < Integers.min(dim-1, o.Size()))
			{
				val = -o.get(d);
			}
			
			m.set(prod, d, d);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					m.set(prod / val, dim-1, d);
				}
			}
		}
		
		return m;
	}

	@Override
	public Vector Oculus()
	{
		return src.Oculus();
	}
}