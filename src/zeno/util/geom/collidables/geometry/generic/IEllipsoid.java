package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.collisions.geometry.CLSEllipsoid;
import zeno.util.tools.Floats;

/**
 * The {IEllipsoid} interface defines the collision operations for ellipsoid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface IEllipsoid extends IConvex
{	
	@Override
	public default Vector Extremum(Vector v)
	{
		Vector n = Vectors.create(Dimension());
		for(int i = 0; i < Dimension(); i++)
		{
			float si = Size().get(i);
			n.set(v.get(i) * si, i);
		}
		
		Vector e = Vectors.create(Dimension());
		for(int i = 0; i < Dimension(); i++)
		{
			float si = Size().get(i);
			e.set(v.get(i) * si * si, i);
		}
		
		return Center().plus(e.times(1f / n.norm()));
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSEllipsoid(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default float Diameter()
	{
		return Floats.max(Size().Values());
	}
}