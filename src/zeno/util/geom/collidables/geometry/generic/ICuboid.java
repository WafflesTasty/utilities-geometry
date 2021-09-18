package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.matrix.Matrices;
import zeno.util.algebra.linear.matrix.Matrix;
import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.collisions.geometry.CLSCuboid;
import zeno.util.geom.utilities.VChain;
import zeno.util.geom.utilities.VChain.Mode;
import zeno.util.tools.Integers;

/**
 * The {ICuboid} interface defines the collision operations for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IHull
 */
public interface ICuboid extends IHull
{		
//	@Override
//	public default Vector Extremum(Vector v)
//	{
//		Vector cen = Center();
//		Vector min = Minimum();
//		Vector max = Maximum();
//		
//		Vector e = Vectors.create(Dimension());
//		for(int i = 0; i < Dimension(); i++)
//		{
//			if(Floats.isZero(v.get(i), 1))
//				e.set(cen.get(i), i);
//			else if(v.get(i) < 0)
//				e.set(min.get(i), i);
//			else
//				e.set(max.get(i), i);
//		}
//
//		return e;
//	}
	
	@Override
	public default CLSCuboid Collisions()
	{
		return new CLSCuboid(this);
	}
	
	@Override
	public default Matrix Vertices()
	{
		VChain spx = VChain.of(this, Mode.POINTS);
		int cols = Integers.pow(2, Dimension());
		int rows = Dimension();
		
		
		int c = 0;
		Matrix span = Matrices.create(rows, cols);
		for(Vector v : spx.Vertices())
		{
			for(int r = 0; r < rows; r++)
			{
				span.set(v.get(r), r, c); 
			}
			
			c++;
		}
		
		return span;
	}
	
	
	// Optional Bounds overrides.

	@Override
	public default float Diameter()
	{
		return Size().norm();
	}
	
	@Override
	public default ICuboid Box()
	{
		return this;
	}
}