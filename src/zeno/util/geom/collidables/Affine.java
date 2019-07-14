package zeno.util.geom.collidables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.APoint;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;
import zeno.util.tools.helper.Array;

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
 */
public interface Affine extends ICollidable
{
	/**
	 * The {@code Affine} interface defines a finite affine set.
	 * Along with the {@link APoint} class, two more implementations are provided.
	 * <ul>
	 * 	<li> The {@code HSet} class creates an affine set from a homogenized matrix. </li>
	 *  <li> The {@code VSet} class creates an affine set from a vectorized matrix. </li>
	 * </ul>
	 *
	 * @author Zeno
	 * @since Jul 14, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Affine
	 */
	public static interface Set extends Affine
	{
		@Override
		public default boolean contains(ASpace s)
		{
			if(s.Dimension() == 1)
			{
				APoint p = s.Span().Points()[0];
				return contains(p);
			}

			return false;
		}
		
		@Override
		public default boolean contains(Vector v)
		{
			Matrix vmat = VMatrix();
			for(int i = 0; i < Size(); i++)
			{
				if(v.equals(vmat.Column(i), 1))
				{
					return true;
				}
			}

			return false;
		}
		
		@Override
		public default boolean intersects(ASpace s)
		{
			Matrix vmat = VMatrix();
			for(int i = 0; i < Size(); i++)
			{
				if(s.contains(vmat.Column(i)))
				{
					return true;
				}
			}

			return false;
		}
				
		@Override
		public default Affine intersect(Affine s)
		{
			Matrix vmat = VMatrix();
			Vector[] set = new Vector[0];			
			for(int i = 0; i < Size(); i++)
			{
				Vector v = vmat.Column(i);
				if(s.contains(v))
				{
					set = Array.add.to(set, v);
				}
			}

			return ASpaces.vset(set);
		}
		
		@Override
		public default Affine.Set Span()
		{
			return this;
		}
		
		
		/**
		 * Returns the list of points spanning the {@code Affine}.
		 * 
		 * @return  a list of affine points
		 * 
		 * 
		 * @see APoint
		 */
		public default APoint[] Points()
		{
			Matrix vmat = VMatrix();
			APoint[] pts = new APoint[Size()];
			for(int i = 0; i < Size(); i++)
			{
				pts[i] = new APoint(vmat.Column(i));
			}
			
			return pts;
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
	 * The {@code HSet} class defines an affine set from a homogeneous matrix.
	 *
	 * @author Zeno
	 * @since Jul 14, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Affine
	 */
	public static class HSet implements Set
	{
		private Matrix hmat;
		
		/**
		 * Creates a new {@code Affine HSet}.
		 * 
		 * @param m  an affine matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public HSet(Matrix m)
		{
			hmat = m;
		}
		
		
		@Override
		public Matrix VMatrix()
		{
			return ASpaces.vectorize(hmat);
		}

		@Override
		public Matrix HMatrix()
		{
			return hmat;
		}

		@Override
		public int Size()
		{
			return hmat.Columns();
		}
	}
	
	/**
	 * The {@code VSet} class defines an affine set from a vectorized matrix.
	 *
	 * @author Zeno
	 * @since Jul 14, 2019
	 * @version 1.0
	 * 
	 * 
	 * @see Affine
	 */
	public static class VSet implements Set
	{
		private Matrix vmat;
		
		/**
		 * Creates a new {@code Affine VSet}.
		 * 
		 * @param m  an affine matrix
		 * 
		 * 
		 * @see Matrix
		 */
		public VSet(Matrix m)
		{
			vmat = m;
		}
		
		
		@Override
		public Matrix VMatrix()
		{
			return vmat;
		}

		@Override
		public Matrix HMatrix()
		{
			return ASpaces.homogenize(vmat);
		}

		@Override
		public int Size()
		{
			return vmat.Columns();
		}
	}
	
	
	/**
	 * Checks if a subspace is contained in the {@code Affine}.
	 * 
	 * @param s  a subspace to check
	 * @return  {@code true} if the subspace is contained
	 * 
	 * 
	 * @see ASpace
	 */
	public abstract boolean contains(ASpace s);
	
	/**
	 * Intersects a subspace with the {@code Affine}.
	 * 
	 * @param s  a subspace to intersect
	 * @return  an affine intersection
	 * 
	 * 
	 * @see ASpace
	 */
	public abstract Affine intersect(Affine s);
	
	/**
	 * Returns a point span for the {@code Affine}.
	 * 
	 * @return  an affine span
	 */
	public abstract Affine.Set Span();
}