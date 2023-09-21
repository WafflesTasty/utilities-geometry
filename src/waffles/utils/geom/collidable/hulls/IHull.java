package waffles.utils.geom.collidable.hulls;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.hulls.BNDHull;
import waffles.utils.geom.collidable.ConvexSet;
import waffles.utils.geom.collision.hulls.CLSHull;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * The {@code IHull} interface is the base for convex hulls of finite point sets.
 *
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see ConvexSet
 * @see Affine
 */
public interface IHull extends Affine, ConvexSet
{		
	/**
	 * An {@code Extremum} computes boundary points on an {@code IHull}.
	 *
	 * @author Waffles
	 * @since 01 Sep 2021
	 * @version 1.0
	 * 
	 * 
	 * @see ConvexSet
	 */
	public static class Extremum implements ConvexSet.Extremum
	{
		private Vector c;
		private Matrix span;
		
		/**
		 * Creates a new {@code Extremum}.
		 * 
		 * @param hull  a target hull
		 * 
		 * 
		 * @see IHull
		 */
		public Extremum(IHull hull)
		{
			c = hull.Bounds().Center();
			span = hull.Generator();
		}
		
		
		@Override
		public Vector along(Vector v)
		{
			int idx = Integers.MIN_VALUE;
			float dot = Floats.NEG_INFINITY;

			for(int i = 0; i < span.Columns(); i++)
			{
				Vector w = span.Column(i);
				float val = v.dot(w.minus(c));
				if(dot < val)
				{
					dot = val;
					idx = i;
				}
			}

			return span.Column(idx);
		}
	}
	
	/**
	 * Returns a vector generator of the {@code IHull}.
	 * 
	 * @param i  a generator index
	 * @return   a vector generator
	 * 
	 * 
	 * @see Vector
	 */
	public default <V extends Vector> V Generator(int i)
	{
		return (V) Generator().Column(i);
	}
	
	/**
	 * Returns the matrix generator of the {@code IHull}.
	 * 
	 * @return  a generating matrix
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract <M extends Matrix> M Generator();
	
	/**
	 * Returns a generator count of the {@code IHull}.
	 * 
	 * @return  a generator count
	 */
	public default int Generators()
	{
		return Generator().Columns();
	}
	
	
	@Override
	public default Bounds Bounds()
	{
		return new BNDHull(this);
	}

	@Override
	public default Collision Collisions()
	{
		return new CLSHull(this);
	}
	
	@Override
	public default Bounds Bounds(GlobalMap map)
	{
		IHull hull = (IHull) map.map(this);
		return hull.Bounds();
	}

	
	@Override
	public default Extremum Extremum()
	{
		return new Extremum(this);
	}
		
	@Override
	public default Factory Factory()
	{
		return span ->
		{
			int cols = span.Columns();
			int rows = span.Rows();
			
			Matrix gen = Matrices.create(rows-1, cols);
			for(int c = 0; c < cols; c++)
			{
				float m = span.get(rows-1, c);
				for(int r = 0; r < rows-1; r++)
				{
					float val = span.get(r, c) / m;
					gen.set(val, r, c);
				}
			}
			
			return Geometries.Hull(gen);
		};
	}
	
	@Override
	public default Matrix Span()
	{
		Matrix span = Generator();
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows+1, cols);
		for(int c = 0; c < cols; c++)
		{
			span.set(1f, rows, c);
		}

		return span;
	}
}