package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.collisions.geometry.CLSConvex;

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
	 * Returns an extreme point of the {@code IConvex}.
	 * 
	 * @param v  a direction of the extreme point
	 * @return  an extreme point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Vector Extremum(Vector v);
	
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSConvex(this);
	}
}