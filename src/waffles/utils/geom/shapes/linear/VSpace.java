package waffles.utils.geom.shapes.linear;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.lin.solvers.matrix.MatrixSolver;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.Collidable;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;

/**
 * A {@code VSpace} provides the basic structure for various linear spaces.
 *
 * @author Waffles
 * @since 10 Dec 2025
 * @version 1.1
 *
 * 
 * @see Positioned
 * @see Collidable
 * @see Affine
 */
public interface VSpace extends Affine, Collidable, Positioned
{
	/**
	 * A {@code VSpace.Factory} generates {@code VSpace} geometry.
	 *
	 * @author Waffles
	 * @since 10 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see MatrixSolver
	 * @see Affine
	 */
 	public static class Factory implements Affine.Factory, MatrixSolver.Hints
	{
		private Point org;
		private Matrix dir;
		private Matrix span;
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param s  a matrix span
		 * 
		 * 
		 * @see Matrix
		 */
		public Factory(Matrix s)
		{
			Vector o = s.Column(0);
			org = Point.create(o);
			span = s;
		}
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param set  a point set
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point... set)
		{			
			org = set[0];

			int col = set.length;			
			int row = org.Dimension();
			
			span = Matrices.create(row + 1, col);
			for(int c = 0; c < col; c++)
			{
				Point p = set[c];
				
				span.set(p.Mass(), row, c);
				for(int r = 0; r < row; r++)
				{
					float v = p.hom(r);
					span.set(v, r, c);
				}
			}
		}
		
		/**
		 * Creates a new {@code Factory}.
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
			org = o;
			dir = d;
		}
				
		/**
		 * Returns a {@code Point}.
		 * 
		 * @param idx  a span index
		 * @return     a span point
		 * 
		 * 
		 * @see Point
		 */
		public Point Point(int idx)
		{
			if(idx > 0)
			{
				Matrix s = Span();
				Vector o = s.Column(idx);
				return Point.create(o);
			}
			
			return org;
		}
		
				
		@Override
		public Matrix Matrix()
		{
			if(dir == null)
			{
				Point o = Point(0);
				
				int row = Span().Rows() - 1;
				int col = Span().Columns() - 1;
				
				dir = Matrices.create(row, col);
				for(int c = 0; c < col; c++)
				{
					Point p = Point(c+1);
					Point q = p.minus(o);
					
					for(int r = 0; r < row; r++)
					{
						float v = p.aff(r);
						dir.set(v, r, c);
					}
				}				
			}
			
			return dir;
		}
								
		@Override
		public Matrix Span()
		{
			if(span == null)
			{
				Point o = Point(0);
				
				int row = Matrix().Rows() + 1;
				int col = Matrix().Columns() + 1;
				
				span = Matrices.create(row, col);
				for(int c = 0; c < col; c++)
				{
					Vector dir = Vectors.create(row - 1);
					if(c > 0)
					{
						dir = Matrix().Column(c - 1);
					}
					
					span.set(1f, row - 1, c);
					for(int r = 0; r < row - 1; r++)
					{
						float v1 = org.aff(r);
						float v2 = dir.get(r);
						
						span.set(v1 + v2, r, c);
					}
				}
			}

			return span;
		}
	}

	
	/**
	 * A {@code Direct VSpace} is defined by a direct matrix.
	 *
	 * @author Waffles
	 * @since 10 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see VSpace
	 */
	public static interface Direct extends VSpace
	{
		/**
		 * Returns the direction of the {@code VSpace}.
		 * 
		 * @return  a direction matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public default Matrix Direction()
		{
			return Factory().Matrix();
		}
	}
	
	/**
	 * An {@code Ortho VSpace} is defined by a normal matrix.
	 *
	 * @author Waffles
	 * @since 10 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see VSpace
	 */
	public static interface Ortho extends VSpace
	{
		/**
		 * Returns the normal of the {@code VSpace}.
		 * 
		 * @return  a normal matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public default Matrix Normal()
		{
			return Factory().Matrix();
		}
	}


	@Override
	public default int Dimension()
	{
		return Factory().Matrix().Rows();
	}

	@Override
	public abstract Factory Factory();
		
	@Override
	public default Point Origin()
	{
		return Factory().Point(0);
	}
}
