package waffles.utils.geom.collidable.spaces;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.matrix.types.shaped.Tall;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.Collision;
import waffles.utils.geom.collision.spaces.CLSVSpace;
import waffles.utils.geom.utilities.Generated;
import waffles.utils.geom.utilities.LinearSpace;

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
		if(gen.allows(Tall.Type(), 0f))
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
//		if(gen.is(Tall.Type()))
			return svd.approx(v);
//		return svd.preApprox(v);
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
	 * Returns the condition of the {@code VSpace}.
	 * This is the condition number of its span.
	 * 
	 * @return  a condition number
	 */
	public float Condition()
	{
		return svd.condition();
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
			
			Matrix g = m.resize(rows-1, cols);
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
		int r1 = gen.Rows();
		int c1 = gen.Columns();
		int rank = svd.rank();
		
		
		Matrix u = svd.U();
		// Create the column complement matrix...
		Matrix b = Matrices.create(r1, c1 - rank);
		for(int c = rank; c < c1; c++)
		{
			// ...which is the last r1-r columns of U.
			for(int r = 0; r < r1; r++)
			{
				float v = u.get(r, c);
				b.set(v, r, c - rank);
			}
		}
		
		return b;
	}
	
	@Override
	public Matrix RowComplement()
	{
		int r1 = gen.Rows();
		int c1 = gen.Columns();
		int rank = svd.rank();
		
		
		Matrix v = svd.V();
		// Create the row complement matrix...
		Matrix b = Matrices.create(r1, c1 - rank);
		for(int c = rank; c < c1; c++)
		{
			// ...which is the last r1-r columns of V.
			for(int r = 0; r < r1; r++)
			{
				float u = v.get(r, c);
				b.set(u, r, c - rank);
			}
		}
		
		return b;
	}
	
	@Override
	public Matrix Generator()
	{
		return gen;
	}
	
	@Override
	public Matrix ColSpace()
	{
		int r1 = gen.Rows();
		int c1 = gen.Columns();
		int rank = svd.rank();
		
		
		Matrix u = svd.U();
		// Create the column space matrix...
		Matrix b = Matrices.create(r1, rank);
		for(int c = 0; c < rank; c++)
		{
			// ...which is the first r columns of U.
			for(int r = 0; r < r1; r++)
			{
				float v = u.get(r, c);
				b.set(v, r, c);
			}
		}
		
		return b;
	}
	
	@Override
	public Matrix RowSpace()
	{
		int r1 = gen.Rows();
		int c1 = gen.Columns();
		int rank = svd.rank();
		
		
		Matrix v = svd.V();
		// Create the row space matrix...
		Matrix b = Matrices.create(r1, rank);
		for(int c = 0; c < rank; c++)
		{
			// ...which is the first r columns of V.
			for(int r = 0; r < r1; r++)
			{
				float u = v.get(r, c);
				b.set(u, r, c);
			}
		}
		
		return b;
	}

	@Override
	public Matrix Span()
	{
		int rows = Generator().Rows();
		int cols = Generator().Columns();
		return gen.resize(rows+1, cols);
	}
}