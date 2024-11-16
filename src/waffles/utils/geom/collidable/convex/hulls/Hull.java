package waffles.utils.geom.collidable.convex.hulls;

import waffles.utils.algebra.elements.linear.Affine;
import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.utilities.matrix.Generated;
import waffles.utils.geom.Collision;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.bounds.hulls.BNDHull;
import waffles.utils.geom.bounds.hulls.BNDHull2D;
import waffles.utils.geom.bounds.hulls.BNDHull3D;
import waffles.utils.geom.collidable.convex.ConvexSet;
import waffles.utils.geom.collision.convex.hulls.CLSHull;
import waffles.utils.geom.spatial.maps.GlobalMap;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * The {@code Hull} interface is the base for convex hulls of finite point sets.
 *
 * @author Waffles
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see Generated
 * @see ConvexSet
 * @see Affine
 */
public interface Hull extends Affine, ConvexSet, Generated
{		
	/**
	 * An {@code Extremum} computes boundary points on an {@code Hull}.
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
		 * @see Hull
		 */
		public Extremum(Hull hull)
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
	
		
	@Override
	public default Bounds Bounds()
	{
		switch(Dimension())
		{
		case 2:
			return new BNDHull2D(this);
		case 3:
			return new BNDHull3D(this);
		default:
			return new BNDHull(this);	
		}
	}

	@Override
	public default Collision Collisions()
	{
		return new CLSHull(this);
	}
	
	@Override
	public default Bounds Bounds(GlobalMap map)
	{
		Hull hull = (Hull) map.map(this);
		return hull.Bounds();
	}

	
	@Override
	public default int Dimension()
	{
		return Generated.super.Dimension();
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