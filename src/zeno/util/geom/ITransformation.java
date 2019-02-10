package zeno.util.geom;

import zeno.util.algebra.Linear;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.Affine;

/**
 * The {@code ITransformation} interface defines a transformation in geometric space.
 * Transformations are defined as mappings between homogeneous coördinate systems.
 * 
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see Affine
 * @see Linear
 */
public interface ITransformation extends Linear.Map<Affine, Affine>
{	
	/**
	 * Returns the inverse of a {@code ITransformation}.
	 * 
	 * @param func  a transformation to invert
	 * @return  an inverted transformation
	 */
	public static ITransformation inverse(ITransformation func)
	{
		return new ITransformation()
		{
			@Override
			public Matrix Matrix(int dim)
			{
				return func.Inverse(dim);
			}

			@Override
			public Matrix Inverse(int dim)
			{
				return func.Matrix(dim);
			}
			
			
			@Override
			public int DimOut()
			{
				return func.DimIn();
			}
			
			@Override
			public int DimIn()
			{
				return func.DimOut();
			}
		};
	}
	
	/**
	 * The {@code Composite} interface composes multiple {@code ITransformations}.
	 *
	 * @author Zeno
	 * @since Feb 10, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see ITransformation
	 * @see Affine
	 * @see Linear
	 */
	@FunctionalInterface
	public static interface Composite extends Linear.Composite<Affine, Affine>, ITransformation
	{
		@Override
		public abstract ITransformation[] Functions();
		

		@Override
		public default Matrix Inverse(int dim)
		{
			Matrix inv = Functions()[0].Inverse(dim);
			for(int i = 1; i < Functions().length; i++)
			{
				inv = inv.times(Functions()[i].Inverse(dim));
			}
			
			return inv;
		}
		
		@Override
		public default Matrix Matrix(int dim)
		{
			Matrix mat = Functions()[0].Matrix(dim);
			for(int i = 1; i < Functions().length; i++)
			{
				mat = Functions()[i].Matrix(dim).times(mat);
			}
			
			return mat;
		}
		
		
		@Override
		public default Affine unmap(Affine val)
		{
			return ITransformation.super.unmap(val);
		}

		@Override
		public default Affine map(Affine val)
		{
			return ITransformation.super.map(val);
		}

		
		@Override
		public default Matrix Inverse()
		{
			return ITransformation.super.Inverse();
		}
		
		@Override
		public default Matrix Matrix()
		{
			return ITransformation.super.Matrix();
		}

		
		@Override
		public default int DimOut()
		{
			return Functions()[Functions().length - 1].DimOut();
		}
		
		@Override
		public default int DimIn()
		{
			return Functions()[0].DimIn();
		}
	}
	
	
	/**
	 * Returns the domain dimension of the {@ode ITransformation}.
	 * 
	 * @return  a domain dimension
	 */
	public abstract int DimIn();
	
	/**
	 * Returns the codomain dimension of the {@code ITransformation}.
	 * 
	 * @return  a codomain dimenion
	 */
	public abstract int DimOut();
	
	
	/**
	 * Returns a matrix for the {@code ITransformation}.
	 * 
	 * @param dim  a square matrix dimension
	 * @return  a transformation matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Matrix(int dim);
	
	/**
	 * Returns an inverse for the {@code ITransformation}.
	 * 
	 * @param dim  a square matrix dimension
	 * @return  a transformation inverse
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Inverse(int dim);

	
	@Override
	public default Affine unmap(Affine val)
	{
		Matrix m = Inverse(DimOut()).times(val.basis(DimOut()));
		
		int rows = m.Rows();
		int cols = m.Columns();
		
		Vector s = m.Row(rows - 1);
		m = Matrices.resize(m, rows - 1, cols);
		return new Affine(m, s);
	}
	
	@Override
	public default Affine map(Affine val)
	{
		Matrix m = Matrix(DimIn()).times(val.basis(DimIn()));
		
		int rows = m.Rows();
		int cols = m.Columns();
		
		Vector s = m.Row(rows - 1);
		m = Matrices.resize(m, rows - 1, cols);
		return new Affine(m, s);
	}

	
	@Override
	public default Matrix Inverse()
	{
		return Inverse(DimOut());
	}
	
	@Override
	public default Matrix Matrix()
	{
		return Matrix(DimIn());
	}
}