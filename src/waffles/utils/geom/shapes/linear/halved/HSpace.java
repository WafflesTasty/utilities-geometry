package waffles.utils.geom.shapes.linear.halved;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.collision.Collision;
import waffles.utils.geom.shapes.collision.linear.halved.CLSHSpace;
import waffles.utils.geom.shapes.fixed.Void;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.utilities.Transformator;
import waffles.utils.tools.primitives.Doubles;

/**
 * A {@code HSpace} defines a real-valued halfspace of R^n.
 *
 * @author Waffles
 * @since Apr 8, 2019
 * @version 1.0
 * 
 * 
 * @see VSpace
 */
public class HSpace implements VSpace.Ortho
{
	/**
	 * A {@code HSpace.Factory} generates {@code HSpace} geometry.
	 *
	 * @author Waffles
	 * @since 07 Dec 2025
	 * @version 1.1
	 *
	 * 
	 * @see VSpace
	 */
	public static class Factory extends VSpace.Factory
	{		
		/**
		 * Creates new {@code Factory}.
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
		 * Creates new {@code Factory}.
		 * 
		 * @param p  a source point
		 * @param q  a target point
		 * 
		 * 
		 * @see Point
		 */
		public Factory(Point p, Point q)
		{
			super(p, q.minus(p).arrow());
		}

		
		@Override
		public Transformator create(Matrix s)
		{
			if(s.Columns() == 0)
			{
				int n = s.Rows();
				return new Void(n);
			}
			
			Factory fct = new Factory(s);
			return new HSpace(fct);
		}
		
		@Override
		public double Error()
		{
			return Doubles.pow(2, -8);
		}
	}
	
	
	private Factory fct;

	/**
	 * Creates a new {@code HSpace}.
	 * 
	 * @param p  a source point
	 * @param q  a target point
	 * 
	 * 
	 * @see Point
	 */
	public HSpace(Point p, Point q)
	{
		this(new Factory(p, q));
	}
	
	/**
	 * Creates a new {@code HSpace}.
	 * 
	 * @param f  a space factory
	 * 
	 * 
	 * @see Factory
	 */
	public HSpace(Factory f)
	{
		fct = f;
	}

	
	@Override
	public Vector Normal()
	{
		return (Vector) Factory().Matrix();
	}
		
	@Override
	public Collision Collision()
	{
		return new CLSHSpace(this);
	}

	@Override
	public Factory Factory()
	{
		return fct;
	}
}