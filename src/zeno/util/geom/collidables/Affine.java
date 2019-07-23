package zeno.util.geom.collidables;

import java.util.Iterator;
import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.affine.CLSASet;
import zeno.util.geom.collidables.collisions.affine.CLSASpace;
import zeno.util.tools.patterns.properties.Inaccurate;

/**
 * The {@code Affine} interface defines a collection of affine points.
 * This interface is implemented by both finite sets and infinite subspaces.
 *
 * @author Zeno
 * @since Jul 14, 2019
 * @version 1.0
 * 
 * 
 * @see ICollidable
 * @see Inaccurate
 */
public interface Affine extends ICollidable
{	
	/**
	 * The {@code Affine Set} interface defines a finite affine point set.
	 *
	 * @author Zeno
	 * @since Jul 14, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Iterable
	 * @see Affine
	 */
	public static interface Set extends Affine, Iterable<Point>
	{
		@Override
		public default Affine.Set Span()
		{
			return this;
		}
				
		@Override
		public default Iterator<Point> iterator()
		{
			return new Iterator<Point>()
			{
				private int i = 0;
				
				@Override
				public boolean hasNext()
				{
					return i < VMatrix().Columns();
				}

				@Override
				public Point next()
				{
					Vector v = VMatrix().Column(i);
					Point p = new Point(v);
					i++;
					
					return p;
				}
			};
		}
								
		@Override
		public default ICollision Collisions()
		{
			return new CLSASet(this);
		}
		
		
		@Override
		public default boolean isFinite()
		{
			return true;
		}
				
		@Override
		public default boolean isEmpty()
		{
			return iterator().hasNext();
		}
		
					
		/**
		 * Returns a vectorized matrix spanning the {@code Affine}.
		 * 
		 * @return  a vectorized matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public abstract Matrix VMatrix();
		
		/**
		 * Returns a homogenized matrix spanning the {@code Affine}.
		 * 
		 * @return  a homogenized matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public abstract Matrix HMatrix();
		
		/**
		 * Returns the amount of points in the {@code Affine}.
		 * 
		 * @return  the set size
		 */
		public abstract int Size();
	}
	
	/**
	 * The {@code Affine Space} interface defines an affine subspace.
	 *
	 * @author Zeno
	 * @since Jul 15, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Affine
	 */
	public static interface Space extends Affine
	{		
		@Override
		public default boolean isEmpty()
		{
			return Dimension() < 0;
		}
		
		@Override
		public default boolean isFinite()
		{
			return Dimension() < 1;
		}

		@Override
		public default ICollision Collisions()
		{
			return new CLSASpace(this);
		}
		
		@Override
		public default Affine.Set Span()
		{
			Matrix span = Direction().Span();
			Matrix o = Origin().VMatrix();
			
			int cols = span.Columns();
			int rows = span.Rows();
			
			span = Matrices.resize(span, rows, cols + 1);
			for(int c = 0; c <= cols; c++)
			{
				for(int r = 0; r < rows; r++)
				{
					float val = span.get(r, c) + o.get(r);
					span.set(val, r, c);
				}
			}
			
			return ASpaces.vset(span);
		}
	
		
		/**
		 * Returns the origin of the {@code Affine Space}.
		 * 
		 * @return  an origin point
		 * 
		 * 
		 * @see Point
		 */
		public abstract Point Origin();
		
		/**
		 * Returns the direction of the {@code Affine Space}.
		 * 
		 * @return  a vector space direction
		 * 
		 * 
		 * @see VSpace
		 */
		public abstract VSpace Direction();
		
		/**
		 * Returns the dimension of the {@code Affine Space}.
		 * 
		 * @return  a space dimension
		 */
		public default int Dimension()
		{
			return Direction().Dimension();
		}
	}
	

	/**
	 * Checks if the {@code Affine} is a finite set.
	 * 
	 * @return  {@code true} if the set is finite
	 */
	public abstract boolean isFinite();
	
	/**
	 * Returns an affine span for the {@code Affine}.
	 * 
	 * @return  an affine span
	 */
	public abstract Affine.Set Span();
}