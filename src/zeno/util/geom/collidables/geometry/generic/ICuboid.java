package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.collisions.geometry.CLSCuboid;
import zeno.util.tools.Floats;

/**
 * The {ICuboid} interface defines the collision operations for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IConvex
 */
public interface ICuboid extends IConvex
{	
	@Override
	public default Vector Extremum(Vector v)
	{
		Vector cen = Center();
		Vector min = Minimum();
		Vector max = Maximum();
		
		Vector e = Vectors.create(Dimension());
		for(int i = 0; i < Dimension(); i++)
		{
			if(Floats.isZero(v.get(i), 1))
				e.set(cen.get(i), i);
			else if(v.get(i) < 0)
				e.set(min.get(i), i);
			else
				e.set(max.get(i), i);
		}

		return e;
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSCuboid(this);
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