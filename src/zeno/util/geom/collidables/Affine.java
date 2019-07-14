package zeno.util.geom.collidables;

import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ICollidable;
import zeno.util.geom.collidables.affine.APoint;
import zeno.util.geom.collidables.affine.ASpace;
import zeno.util.geom.collidables.affine.ASpaces;

/**
 * The {@code Affine} interface defines a set of affine singletons.
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
 * @see ICollidable
 */
public interface Affine extends ICollidable
{
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
	public static class HSet implements Affine
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
	public static class VSet implements Affine
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