package zeno.util.geom.transforms.projective;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ITransformation;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code Projection} class defines a projective transformation.
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
public class Projection implements ITransformation
{
	private static Vector DefaultOculus(int iDim, int oDim)
	{
		Vector v = Vectors.create(oDim);
		for(int i = iDim; i < oDim; i++)
		{
			v.set(Floats.NEG_INFINITY, i);
		}
		
		return v;
	}

	
	private Vector oculus;
		
	/**
	 * Creates a new {@code Projection}.
	 * 
	 * @param o  an oculus vector
	 * @param iDim  a   projected space dimension
	 * @param oDim  a surrounding space dimension
	 * 
	 * 
	 * @see Vector
	 */
	public Projection(Vector o, int iDim, int oDim)
	{
		this(iDim, oDim);
		
		for(int i = oDim; i < Integers.min(o.Size(), iDim); i++)
		{
			oculus.set(o.get(i), i);
		}
	}
	
	/**
	 * Creates a new {@code Projection}.
	 * 
	 * @param iDim  a   projected space dimension
	 * @param oDim  a surrounding space dimension
	 */
	public Projection(int iDim, int oDim)
	{
		oculus = DefaultOculus(iDim, oDim);
	}
	
	/**
	 * Returns the projection oculus.
	 * 
	 * @return  an oculus vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector Oculus()
	{
		return oculus;
	}
	
			
	@Override
	public Matrix Matrix(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		
		float prod = 1f / product(-1);
		for(int d = 0; d < dim + 1; d++)
		{
			float val = 0f;
			if(d < Integers.min(dim, oculus.Size()))
			{
				val = oculus.get(d);
			}
			
			m.set(prod, d, d);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					m.set(prod / oculus.get(d), dim, d);
				}
			}
		}
		
		return m;
	}
	
	@Override
	public Matrix Inverse(int dim)
	{
		Matrix m = Matrices.identity(dim + 1);
		
		float prod = product(-1);
		for(int d = 0; d < dim + 1; d++)
		{
			float val = 0f;
			if(d < Integers.min(dim, oculus.Size()))
			{
				val = oculus.get(d);
			}
			
			m.set(prod, d, d);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					m.set(product(d), dim, d);
				}
			}
		}
		
		return m;
	}

	
	float product(int index)
	{
		// This could be adapted for different matrix dimensions.
		// However, the use of a homogeneous coördinate system
		// makes sure these are all equivalent in use.
		
		float product = 1f;
		for(int i = 0; i < oculus.Size(); i++)
		{
			if(i == index) continue;
			
			float val = oculus.get(i);
			if(Floats.isFinite(val))
			{
				if(!Floats.isZero(val, 1))
				{
					product *= -val;
				}
			}
		}
		
		return product;
	}
}