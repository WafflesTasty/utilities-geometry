package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.bounds.Bounds;
import zeno.util.geom.collidables.collisions.convex.CLSConvex;
import zeno.util.geom.utilities.Geometries;

/**
 * The {@code IConvex} interface is the base for convex n-dimensional shapes.
 * </br> It performs collision detection through an iterative algorithm.
 *
 * @author Zeno
 * @since 11 Jan 2021
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IConvex extends IGeometry
{
	/**
	 * The {@code Extremum} interface generates extremal points on an {@code IConvex}.
	 *
	 * @author Waffles
	 * @since 01 Sep 2021
	 * @version 1.0
	 */
	@FunctionalInterface
	public static interface Extremum
	{
		/**
		 * Returns an extremum along a {@code Vector}.
		 * 
		 * @param v  a target vector
		 * @return   a convex extremum
		 * 
		 * 
		 * @see Vector
		 */
		public abstract Vector along(Vector v);
	}
	
	public static class Difference implements IConvex
	{
		private IConvex a, b;
		
		public Difference(IConvex a, IConvex b)
		{
			this.a = a;
			this.b = b;
		}
		
		@Override
		public Bounds Bounds(ITransformation map)
		{
			Vector min = Minimum();
			Vector max = Maximum();
			
			return Geometries.cuboid
			(
				min.plus(max).times(0.5f),
				max.minus(min)
			);
		}
		
		@Override
		public Extremum Extremum()
		{			
			return v ->
			{
				Extremum aExt = a.Extremum();
				Extremum bExt = b.Extremum();
				
				Vector v1 = aExt.along(v.times(+1f));
				if(v1 == null) return null;
				Vector v2 = bExt.along(v.times(-1f));
				if(v2 == null) return null;
				return v1.minus(v2);
			};
		}
		
		@Override
		public Vector Minimum()
		{
			return a.Minimum().minus(b.Maximum());
		}
		
		@Override
		public Vector Maximum()
		{
			return a.Maximum().minus(b.Minimum());
		}
	}
	
	/**
	 * Returns the extremum of an {@code IConvex}.
	 * 
	 * @return  an extremum
	 * 
	 * 
	 * @see Extremum
	 */
	public abstract Extremum Extremum();
	
	/**
	 * Returns the Minkowski difference with an {@code IConvex}.
	 * 
	 * @param c  a convex shape
	 * @return   a minkowski difference
	 */
	public default IConvex minus(IConvex c)
	{
		return new Difference(this, c);
	}
	
	
	@Override
	public default CLSConvex Collisions()
	{
		return new CLSConvex(this);
	}
}