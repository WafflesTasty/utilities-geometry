package waffles.utils.geom.shapes.linear.affine;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.solvers.matrix.ranks.RankReveal;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.linear.affine.CLSASpace;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.linear.affine.lines.Line;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.tools.primitives.Doubles;

/**
 * An {@code ASpace} defines a real-valued affine subspace of R^n.
 *
 * @author Waffles
 * @since 07 Dec 2025
 * @version 1.1
 *
 * 
 * @see RankReveal
 * @see VSpace
 */
public class ASpace implements VSpace.Direct, RankReveal
{
	/**
	 * An {@code ASpace.Factory} generates {@code ASpace} geometry.
	 *
	 * @author Waffles
	 * @since 07 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see VSpace
	 * @see RRSVD
	 */
	public static class Factory extends VSpace.Factory implements RRSVD.Hints
	{		
		/**
		 * Creates new {@code Factory}.
		 * 
		 * @param s  a matrix span
		 * 
		 * 
		 * @see Matrix
		 */
		public Factory(Matrix s)
		{
			super(s);
		}
				
		/**
		 * Creates new {@code Factory}.
		 * 
		 * @param o  an origin point
		 * @param d  a direction matrix
		 * 
		 * 
		 * @see Matrix
		 * @see Point
		 */
		public Factory(Point o, Matrix d)
		{
			super(o, d);
		}
		
		/**
		 * Creates new {@code Factory}.
		 * 
		 * @param set  a point set
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point... set)
		{	
			super(set);
		}
		
		
		@Override
		public Affine create(Matrix... set)
		{
			if(set.length == 0)
				return null;
			if(set.length == 1)
			{
				Matrix s = set[0];
				return new ASpace(s);
			}
			
			Matrix s = Matrices.concat(set);
			return new ASpace(s);
		}
		
		@Override
		public double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	/**
	 * Creates an affine space from a {@code Point} and a {@code Matrix}.
	 * This method is designed to return a {@code Point} instead when
	 * the direction matrix has no columns, or a {@code Line}
	 * when the matrix has exactly one column.
	 * 
	 * @param p  an affine point
	 * @param v  a direction matrix
	 * @return  an affine object
	 * 
	 * 
	 * @see Collidable
	 * @see Matrix
	 * @see Point
	 */
	public static Collidable create(Point p, Matrix v)
	{
		switch(v.Columns())
		{
		case 0:
			return p;
		case 1:
			return new Line(p, (Vector) v);
		default:
			return new ASpace(p, v);
		}
	}
	
	
	private RRSVD svd;
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public ASpace(Matrix s)
	{
		this(new Factory(s));
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param set  a point set
	 * 
	 * 
	 * @see Point
	 */
	public ASpace(Point... set)
	{
		this(new Factory(set));
	}
		
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param p  an origin point
	 * @param d  a direction matrix
	 * 
	 * 
	 * @see Matrix
	 * @see Point
	 */
	public ASpace(Point p, Matrix d)
	{
		this(new Factory(p, d));
	}
	
	/**
	 * Creates a new {@code ASpace}.
	 * 
	 * @param f  a space factory
	 * 
	 * 
	 * @see Factory
	 */
	public ASpace(Factory f)
	{
		svd = new RRSVD(f);
	}
		
					
	/**
	 * Follows a direction in the {@code ASpace}.
	 * 
	 * @param v  a direction vector
	 * @return  {@code true} if a valid direction
	 * 
	 * 
	 * @see Vector
	 */
	public boolean follows(Vector v)
	{
		Vector w = approx(v).minus(v);
		double e = Hints().Error();
		double n = w.normSqr();
		return n < e;
	}
	
	/**
	 * Approximates a vector in the {@code ASpace}.
	 * 
	 * @param v  a direction vector
	 * @return  a closest vector
	 * 
	 * 
	 * @see Vector
	 */
	public Vector approx(Vector v)
	{
		Matrix m = Hints().Matrix();
		Vector x = SVD().approx(v);
		return m.times(x);
	}
	
	/**
	 * Approximates a point in the {@code ASpace}.
	 * 
	 * @param p  a target point
	 * @return   a closest point
	 * 
	 * 
	 * @see Point
	 */
	public Point approx(Point p)
	{
		Point q = p.minus(Origin());
		Vector v = approx(q.Vector());
		return Origin().plus(v);
	}
	
	/**
	 * Returns the svd of the {@code ASpace}.
	 * 
	 * @return  an svd solver
	 * 
	 * 
	 * @see RRSVD
	 */
	public RRSVD SVD()
	{
		return svd;
	}
	
		
	@Override
	public Collision Collision()
	{
		return new CLSASpace(this);
	}
		
	@Override
	public Factory Factory()
	{
		return (Factory) svd.Hints();
	}
	
	@Override
	public Factory Hints()
	{
		return Factory();
	}
	
	@Override
	public int rank()
	{
		return SVD().rank();
	}
}