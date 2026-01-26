package waffles.utils.geom.shapes.linear.halved;

import waffles.utils.alg.lin.measure.matrix.Matrices;
import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.linear.halved.CLSHSpace;
import waffles.utils.geom.shapes.linear.VSpace;
import waffles.utils.geom.shapes.points.Point;
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
		 * @param o  an origin point
		 * @param d  a direction vector
		 * 
		 * 
		 * @see Vector
		 * @see Point
		 */
		public Factory(Point o, Vector d)
		{
			super(o, d);
		}

		
		@Override
		public Affine create(Matrix... set)
		{
			if(set.length == 0)
				return null;
			if(set.length == 1)
			{
				Matrix s = set[0];
				return new HSpace(s);
			}
			
			Matrix s = Matrices.concat(set);
			return new HSpace(s);
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
	 * @param s  a matrix span
	 * 
	 * 
	 * @see Matrix
	 */
	public HSpace(Matrix s)
	{
		this(new Factory(s));
	}
	
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
		this(p, q.minus(p).Vector());
	}
		
	/**
	 * Creates a new {@code HSpace}.
	 * 
	 * @param o  an origin point
	 * @param n  a normal vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public HSpace(Point o, Vector n)
	{
		this(new Factory(o, n));
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