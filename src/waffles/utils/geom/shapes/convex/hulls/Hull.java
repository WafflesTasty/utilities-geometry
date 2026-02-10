package waffles.utils.geom.shapes.convex.hulls;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.bounds.convex.hulls.BNDHull;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.collision.convex.hulls.CLSHull;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.convex.hulls.line.Segment;
import waffles.utils.geom.shapes.convex.hulls.triangle.Triangle;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.geom.utilities.iterators.HullIterator;
import waffles.utils.tools.patterns.properties.counters.Countable;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code Hull} defines a convex hull in n-dimensional space.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Transformator
 * @see ConvexSet
 */
public interface Hull extends ConvexSet, Transformator
{
	/**
	 * A {@code Hull.Factory} generates {@code Hull} geometry.
	 *
	 * @author Waffles
	 * @since 23 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Transformator
	 * @see Countable
	 */
	public static interface Factory extends Transformator.Factory, Countable
	{
		/**
		 * Returns a {@code Point} on the {@code Hull}.
		 * 
		 * @param idx  a span index
		 * @return  a span point
		 * 
		 * 
		 * @see Point
		 */
		public default Point Point(int idx)
		{
			if(idx < Count())
			{
				Matrix s = Span();
				Vector p = s.Column(idx);
				return Point.create(p);
			}

			return null;
		}
				
		
		@Override
		public default Transformator create(Matrix m)
		{
			return Hull.create(m);
		}
		
		@Override
		public default int Count()
		{
			return Span().Columns();
		}
	}
	
	/**
	 * Creates a {@code Transformable} from a {@code Matrix} span.
	 * 
	 * @param s  a matrix span
	 * @return   a collidable
	 * 
	 * 
	 * @see Transformator
	 * @see Matrix
	 */
	public static Transformator create(Matrix s)
	{
		if(s.Columns() == 0)
		{
			return new Void(s.Rows());
		}
		
		if(s.Columns() == 1)
		{
			return Point.create(s.Column(0));
		}
		
		if(s.Columns() == 2)
		{
			Point p = Point.create(s.Column(0));
			Point q = Point.create(s.Column(1));
			
			return Segment.create(p, q);
		}
		
		if(s.Columns() == 3)
		{
			Point p = Point.create(s.Column(0));
			Point q = Point.create(s.Column(1));
			Point r = Point.create(s.Column(2));
			
			return Triangle.create(p, q, r);
		}
		
		if(s.Rows() == 3)
			return new Hull2D(s);
		if(s.Rows() == 4)
			return new Hull3D(s);
		
		return new HullND(s);
	}
	
	/**
	 * Iterates over the points in the {@code Hull}.
	 * 
	 * @return  a point iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Point
	 */
	public default Iterable<Point> Points()
	{
		return () -> new HullIterator(this);
	}
	
	
	@Override
	public abstract Factory Factory();
		
	@Override
	public default Collision Collision()
	{
		return new CLSHull(this);
	}
	
	@Override
	public default Extremum Extremum()
	{
		return p ->
		{
			Point o = Origin();
			Factory fc = Factory();

			int idx = Integers.MIN_VALUE;
			float dot = Floats.NEG_INFINITY;

			for(int k = 0; k < fc.Count(); k++)
			{
				Point q = fc.Point(k);
				float d = p.dot(q.minus(o));
				if(dot < d)
				{
					dot = d;
					idx = k;
				}
			}

			return fc.Point(idx);
		};
	}
			
	@Override
	public default Bounds Bounds()
	{
		return new BNDHull(this);
	}
}
