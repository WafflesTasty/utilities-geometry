package waffles.utils.geom.collidable.spaces;

import waffles.utils.algebra.algorithms.LinearSpace;
import waffles.utils.algebra.algorithms.rankreveal.RRSVD;
import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.matrix.types.Tall;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.algebra.utilities.Generated;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collision.spaces.CLSVSpace;

/**
 * A {@code VSpace} defines a real-valued euclidian vector space.
 * Vector spaces are designed to produce various bases, and
 * implement {@code Collidable} for collision checks.
 *
 * @author Waffles
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see Generated
 * @see LinearSpace
 * @see Collidable
 * @see Affine
 */
public class VSpace implements Affine, Generated, LinearSpace, Collidable
{	
	/**
	 * Creates a trivial {@code VSpace}.
	 * 
	 * @param dim  a space dimension
	 * @return  a trivial space
	 */
	public static VSpace Trivial(int dim)
	{
		Vector o = Vectors.create(dim);
		return new VSpace(o);
	}
	
	
	private RRSVD svd;
	private Matrix gen;
	
	/**
	 * Creates a new {@code VSpace}.
	 * 
	 * @param g  a generating matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public VSpace(Matrix g)
	{
		gen = g;
		if(Tall.Type().allows(gen, 0))
		{
			gen.setOperator(Tall.Type());
			svd = new RRSVD(gen);
		}
		else
		{
			Matrix neg = gen.transpose();
			neg.setOperator(Tall.Type());
			svd = new RRSVD(neg);
		}
	}
	
	/**
	 * Creates a new {@code VSpace}.
	 * 
	 * @param set  a generating set
	 * 
	 * 
	 * @see Matrix
	 */
	public VSpace(Matrix... set)
	{
		this(Matrices.concat(set));

	}

	
	/**
	 * Returns a direct sum with the {@code VSpace}.
	 * 
	 * @param s  a vector space
	 * @return  a sum space
	 */
	public VSpace add(VSpace s)
	{
		return new VSpace(gen, s.gen);
	}
	
	/**
	 * Approximates a vector within the {@code VSpace}.
	 * The vector needs as many components as there
	 * are rows in the generating matrix.
	 * 
	 * @param v  a target vector
	 * @return   a space vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector approx(Vector v)
	{
		if(gen.is(Tall.Type()))
			return svd.approx(v);
		return svd.preApprox(v);
	}
	
	/**
	 * Evaluates a coefficient matrix in the {@code VSpace}.
	 * The matrix needs as many rows as there are
	 * columns in the generating matrix.
	 * 
	 * @param m  a coefficient matrix
	 * @return   a space matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public Matrix evaluate(Matrix m)
	{
		return Generator().times(m);
	}

	/**
	 * Returns the rank of the {@code VSpace}.
	 * This is its actual span dimension.
	 * 
	 * @return  a vector rank
	 */
	public int Rank()
	{
		return svd.rank();
	}

	
	@Override
	public int Dimension()
	{
		return Generator().Rows();
	}
	
	@Override
	public Factory Factory()
	{
		return m ->
		{
			int rows = m.Rows();
			int cols = m.Columns();
			
			Matrix g = Matrices.resize(m, rows-1, cols);
			return new VSpace(g);
		};
	}
	
	@Override
	public Collision Collisions()
	{
		return new CLSVSpace(this);
	}
	
	@Override
	public Matrix ColComplement()
	{
		if(gen.is(Tall.Type()))
			return svd.ColComplement();
		return svd.RowComplement();
	}
	
	@Override
	public Matrix RowComplement()
	{
		if(gen.is(Tall.Type()))
			return svd.RowComplement();
		return svd.ColComplement();
	}
	
	@Override
	public Matrix Generator()
	{
		return gen;
	}
	
	@Override
	public Matrix ColSpace()
	{
		if(gen.is(Tall.Type()))
			return svd.ColSpace();
		return svd.RowSpace();
	}
	
	@Override
	public Matrix RowSpace()
	{
		if(gen.is(Tall.Type()))
			return svd.RowSpace();
		return svd.ColSpace();
	}

	@Override
	public Matrix Span()
	{
		int rows = Generator().Rows();
		int cols = Generator().Columns();
		
		return Matrices.resize(gen, rows+1, cols);
	}
}