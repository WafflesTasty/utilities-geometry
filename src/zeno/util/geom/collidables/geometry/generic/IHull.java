package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.Affine;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.collisions.convex.CLSHull;
import zeno.util.geom.collidables.geometry.Hull;
import zeno.util.geom.utilities.Geometries;
import zeno.util.tools.Floats;
import zeno.util.tools.Integers;

/**
 * The {@code IHull} interface is the base for convex hulls of finite point sets.
 * </br> It performs collision detection through linear programming.
 *
 * @author Zeno
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see IGeometry
 * @see Affine
 */
public interface IHull extends IConvex, Affine
{		
	/**
	 * The {@code Extremum} class handles extrema for an {@code IHull}.
	 *
	 * @author Waffles
	 * @since 01 Sep 2021
	 * @version 1.0
	 * 
	 * 
	 * @see IConvex
	 */
	public static class Extremum implements IConvex.Extremum
	{
		private Vector c;
		private Matrix span;
//		private List<Integer> pts;
		
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
			c = hull.Center();
			span = hull.Vertices();

//			pts = new ArrayList<>();
//			for(int i = 0; i < span.Columns(); i++)
//			{
//				pts.add(i);
//			}
		}
		
		
		@Override
		public Vector along(Vector v)
		{
//			if(pts.isEmpty()) return null;
			
			int idx = Integers.MIN_VALUE;
			float dot = Floats.NEG_INFINITY;
//			for(int i = 0; i < pts.size(); i++)
			for(int i = 0; i < span.Columns(); i++)
			{
//				Vector w = span.Column(pts.get(i));
				Vector w = span.Column(i);
				float val = v.dot(w.minus(c));
				if(dot < val)
				{
					dot = val;
					idx = i;
				}
			}

//			idx = pts.remove(idx);
			return span.Column(idx);
		}
	}

	
	/**
	 * Returns the vertices of the {@code IHull}.
	 * 
	 * @return  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public abstract Matrix Vertices();
	
	
	@Override
	public default CLSHull Collisions()
	{
		return new CLSHull(this);
	}
	
	@Override
	public default IConvex minus(IConvex c)
	{
		if(!(c instanceof IHull))
		{
			return IConvex.super.minus(c);
		}
		
		Matrix aSpan = Vertices();
		Matrix bSpan = ((IHull) c).Vertices();
		
		int aRows = aSpan.Rows();
		int bRows = bSpan.Rows();
		int aCols = aSpan.Columns();
		int bCols = bSpan.Columns();
		
		Matrix span = Matrices.create(aRows, aCols * bCols);
		for(int a = 0; a < aCols; a++)
		{
			Vector v = aSpan.Column(a);
			for(int b = 0; b < bCols; b++)
			{
				Vector w = bSpan.Column(b);
				for(int r = 0; r < bRows; r++)
				{
					int s = a * aCols + b;
					float val = v.get(r) - w.get(r);
					span.set(val, r, s);
				}
			}
		}
		
		return new Hull(span);
	}
		
	@Override
	public default Bounds Bounds(ITransformation map)
	{
		return new Hull(map.map(Vertices())).Bounds();
	}

	@Override
	public default Affine.Factory Factory()
	{
		return (m) ->
		{
			return Geometries.hull(m);
		};
	}
	
	@Override
	public default Extremum Extremum()
	{
		return new Extremum(this);
	}
		
	@Override
	public default Matrix Span()
	{
		Matrix span = Vertices();
		int cols = span.Columns();
		int rows = span.Rows();
		
		span = Matrices.resize(span, rows + 1, cols);
		for(int c = 0; c < cols; c++)
		{
			span.set(1f, rows, c);
		}

		return span;
	}
	
	
	// Obligatory bounds overrides.
	
	@Override
	public default Vector Minimum()
	{
		int rows = Vertices().Rows();
		int cols = Vertices().Columns();
		
		Vector min = Vectors.create(Floats.MAX_VALUE, rows);
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				float val = Vertices().get(r, c);
				if(val < min.get(r))
				{
					min.set(val, r);
				}
			}
		}
		
		return min;
	}
	
	@Override
	public default Vector Maximum()
	{
		int rows = Vertices().Rows();
		int cols = Vertices().Columns();
		
		Vector max = Vectors.create(Floats.MIN_VALUE, rows);
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				float val = Vertices().get(r, c);
				if(val > max.get(r))
				{
					max.set(val, r);
				}
			}
		}
		
		return max;
	}
	
	@Override
	public default float Radius()
	{
		Vector cen = Center();
		float radius = Floats.MIN_VALUE;
		for(int c = 0; c < Vertices().Columns(); c++)
		{
			Vector w = Vertices().Column(c);
			float dist = w.minus(cen).normSqr();
			if(radius < dist) radius = dist;
		}
		
		radius = Floats.sqrt(radius);
		return radius;
	}
}