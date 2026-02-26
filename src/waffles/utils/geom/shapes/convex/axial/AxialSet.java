package waffles.utils.geom.shapes.convex.axial;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.convex.ConvexSet;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;

/**
 * An {@code AxialSet} defines a {@code ConvexSet} by its origin and scale.
 * These types of convex sets have the additional capability of being transformed
 * by axial maps, allowing them to be used in axis-aligned computational problems.
 * 
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 * 
 * 
 * @see Transformator
 * @see ConvexSet
 */
public interface AxialSet extends ConvexSet, Transformator
{
	/**
	 * A {@code Factory} generates {@code AxialSet} geometry.
	 *
	 * @author Waffles
	 * @since 24 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Transformator
	 */
	public static interface Factory extends Transformator.Factory
	{
		/**
		 * Returns the source of the {@code Factory}.
		 * 
		 * @return  an axial set
		 * 
		 * 
		 * @see AxialSet
		 */
		public abstract AxialSet Source();
		
		/**
		 * Creates a new {@code Constructible}.
		 * 
		 * @param o  an origin point
		 * @param s  a scale arrow
		 * @return  an constructed object
		 * 
		 * 
		 * @see Transformator
		 * @see Arrow
		 * @see Point
		 */
		public abstract Transformator create(Point o, Arrow s);
		
		
		@Override
		public default Transformator create(Matrix m)
		{
			int n = m.Rows();
			
			if(n == 0)
				return new Void(n);
			if(n == 1)
			{
				Vector s = m.Column(0);
				return Point.create(s);
			}

			float m1 = m.get(n - 1, 0);
			float m2 = m.get(n - 1, 1);
			
			Vector c1 = m.Column(0);
			Vector c2 = m.Column(1);
			
			c1 = c1.resize(n - 1);
			c2 = c2.resize(n - 1);

			Point o = Point.create(m.Column(0));
			Arrow s = Arrow.create(m.Column(1));
			
			return create(o, s);
		}
		
		@Override
		public default Matrix Span()
		{
			Arrow s = Source().Scale();
			Point o = Source().Origin();
			
			Vector v = o.Factory().Span();
			Vector w = s.Factory().Span();
			
			return Matrices.concat(v, w);
		}
	}
	
		
	@Override
	public abstract Factory Factory();

	@Override
	public default int Dimension()
	{
		return ConvexSet.super.Dimension();
	}
}