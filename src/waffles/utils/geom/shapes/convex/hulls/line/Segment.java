package waffles.utils.geom.shapes.convex.hulls.line;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.solvers.Solver;
import waffles.utils.alg.lin.solvers.matrix.ranks.types.RRSVD;
import waffles.utils.geom.collide.collision.convex.hulls.CLSSegment;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.linear.LSpace;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.points.Point;

/**
 * A {@code Segment} defines a bounded line segment in n-dimensional space.
 *
 * @author Waffles
 * @since 15 Jan 2026
 * @version 1.1
 *
 * 
 * @see LSpace
 * @see Hull
 */
public interface Segment extends Hull, LSpace
{
	/**
	 * A {@code Segment.Factory} generates {@code Segment} geometry.
	 *
	 * @author Waffles
	 * @since 07 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see VSpace
	 * @see RRSVD
	 * @see Hull
	 */
	public static class Factory extends VSpace.Factory implements Hull.Factory, RRSVD.Hints
	{		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param s  a matrix span
		 * 
		 * 
		 * @see Matrix
		 */
		public Factory(Matrix s)
		{
			super(s);
		}
				
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param o  an origin point
		 * @param d  a direction matrix
		 * 
		 * 
		 * @see Matrix
		 * @see Point
		 */
		public Factory(Point o, Matrix d)
		{
			super(o, d);
		}
		
		/**
		 * Creates a new {@code Factory}.
		 * 
		 * @param set  a point set
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point... set)
		{	
			super(set);
		}
		
		
		@Override
		public Segment create(Matrix... set)
		{
			if(set.length == 0)
				return null;
			if(set.length == 1)
			{
				Matrix s = set[0];
				return Segment.create(s);
			}
			
			Matrix s = Matrices.concat(set);
			return Segment.create(s);
		}
		
		@Override
		public double Error()
		{
			return Solver.DEF_ERROR;
		}
	}
	
	
	/**
	 * Creates a {@code Segment} from two points.
	 * 
	 * @param x  a segment point
	 * @param y  a segment point
	 * @return   a line segment
	 * 
	 * 
	 * @see Point
	 */
	public static Segment create(Point x, Point y)
	{
		switch(x.Dimension())
		{
		case 2:
			return new Segment2D(x, y);
		case 3:
			return new Segment3D(x, y);
		default:
			return new SegmentND(x, y);
		}
	}
	
	/**
	 * Creates a {@code Segment} from a matrix span.
	 * 
	 * @param s  a matrix span
	 * @return   a line segment
	 * 
	 * 
	 * @see Matrix
	 */
	public static Segment create(Matrix s)
	{
		switch(s.Rows())
		{
		case 3:
			return new Segment2D(s);
		case 4:
			return new Segment3D(s);
		default:
			return new SegmentND(s);
		}
	}
	
	
	/**
	 * Returns the first point of the {@code Segment}.
	 *
	 * @return  a segment point
	 *
	 *
	 * @see Point
	 */
	public default Point P1()
	{
		return Factory().Point(0);
	}

	/**
	 * Returns the second point of the {@code Segment}.
	 *
	 * @return  a segment point
	 *
	 *
	 * @see Point
	 */
	public default Point P2()
	{
		return Factory().Point(1);
	}
	
	
	@Override
	public default int Dimension()
	{
		return LSpace.super.Dimension();
	}
	
	@Override
	public abstract Factory Factory();
		
	@Override
	public default CLSSegment Collision()
	{
		return new CLSSegment(this);
	}
	
	@Override
	public default Point Origin()
	{
		return LSpace.super.Origin();
	}
}