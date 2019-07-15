package zeno.util.geom.collidables;

import java.util.Iterator;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.VSpace;
import zeno.util.algebra.linear.vector.VSpaces;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.geom.collidables.affine.points.Point;
import zeno.util.tools.helper.Array;
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
public interface Affine extends ICollidable, Inaccurate<Affine>
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
		public default boolean equals(Affine a, int ulps)
		{
			if(!a.isFinite())
			{
				return false;
			}
			
			Affine.Set s = (Affine.Set) a;
			return Size() == s.Size()
				&& contains(s);
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
		public default Affine.Set Span()
		{
			return this;
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
		
		@Override
		public default Affine intersect(Affine a)
		{
			if(a.isFinite())
			{
				return Affine.super.intersect(a);
			}
			
			Affine.Space s = (Affine.Space) a;			
			VSpace dir = Direction().add(s.Direction());
			Vector pq = Origin().minus(s.Origin());
			int size = Origin().Size();			
			
			// If p-q not in V+W...
			Vector x = dir.coordinates(pq);
			if(x == null)
			{
				// The intersection is empty.
				return ASpaces.trivial(size);
			}
			
			// Otherwise, a common point is found.
			x = Vectors.resize(x, size);
			x = Direction().Span().times(x);
			x = Origin().VMatrix().plus(x);
			
			// Calculate the direction intersection.
			Matrix m = dir.RowComplement();
			m = Matrices.resize(m, Dimension(), m.Columns());
			m = dir.Span().times(m);
			
			// Create the new affine subspace.
			Point o = new Point(x);
			VSpace v = VSpaces.create(m);
			return ASpaces.span(o, v);
		}
		
		@Override
		public default boolean intersects(Affine a)
		{
			if(a.isFinite())
			{
				return Affine.super.intersects(a);
			}
			
			Affine.Space s = (Affine.Space) a;
			VSpace dir = Direction().add(s.Direction());		
			Vector pq = Origin().minus(s.Origin());
			return dir.contains(pq); 
		}
		
		@Override
		public default boolean equals(Affine a, int ulps)
		{
			if(a instanceof Affine.Space)
			{
				Affine.Space s = (Affine.Space) a;
				
				VSpace dir1 = Direction();
				VSpace dir2 = s.Direction();
				return dir1.equals(dir2, ulps)
					&& intersects(s);
			}
			
			return false;
		}
				
		@Override
		public default boolean contains(Affine a)
		{
			if(a.isFinite())
			{
				return Affine.super.contains(a);
			}
			
			Affine.Space s = (Affine.Space) a;
			Vector pq = Origin().minus(s.Origin());
			VSpace dir = Direction().add(s.Direction());		
			return dir.Dimension() == s.Dimension()
				&& dir.contains(pq);
		}

		@Override
		public default boolean contains(Point p)
		{
			VSpace dir = Direction();
			Vector ov = p.minus(Origin());
			return dir.contains(ov);
		}

		
		@Override
		public default boolean isFinite()
		{
			return Dimension() < 1;
		}

		@Override
		public default boolean isEmpty()
		{
			return Dimension() < 0;
		}
	}
	

	/**
	 * Checks if the {@code Affine} is a finite set.
	 * 
	 * @return  {@code true} if the set is finite
	 */
	public abstract boolean isFinite();
	
	/**
	 * Checks if the {@code Affine} is an empty set.
	 * 
	 * @return  {@code true} if the set is empty
	 */
	public abstract boolean isEmpty();	

	
	/**
	 * Returns an affine span for the {@code Affine}.
	 * 
	 * @return  an affine span
	 */
	public abstract Affine.Set Span();
	
	/**
	 * Intersects an affine set with the {@code Affine}.
	 * 
	 * @param a  an affine set to intersect
	 * @return  an affine intersection
	 */
	public default Affine intersect(Affine a)
	{
		Point[] pts = new Point[0];
		for(Point p : Span())
		{
			if(a.contains(p))
			{
				pts = Array.add.to(pts, p);
			}
		}
		
		return ASpaces.set(pts);
	}
	
	
	@Override
	public default boolean intersects(Affine a)
	{
		for(Point p : a.Span())
		{
			if(contains(p))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public default boolean contains(Point p)
	{
		for(Point q : Span())
		{
			if(p.equals(q, 1))
			{
				return true;
			}
		}
		
		return false;
	}
}