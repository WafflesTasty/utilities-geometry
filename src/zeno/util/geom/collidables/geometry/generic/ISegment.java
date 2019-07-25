package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.geometry.CLSSegment;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code ISegment} interface defines the collision operations for line segment geometry.
 * 
 * @author Zeno
 * @since Mar 25, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ISegment extends IGeometry
{		
	/**
	 * Returns the first point of the {@code ISegment}.
	 * 
	 * @return  the segment's first point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Point P1();
	
	/**
	 * Returns the second point of the {@code ISegment}.
	 * 
	 * @return  the segment's second point
	 * 
	 * 
	 * @see Vector
	 */
	public abstract Point P2();
	
	
	@Override
	public default Bounds Bounds(AffineMap map)
	{
		return map.map(this).Bounds();
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSSegment(this);
	}

	// Optional Bounds overrides.
	
	@Override
	public default Vector Center()
	{
		return P1().plus(P2()).times(0.5f);
	}
	
	@Override
	public default Vector Size()
	{
		return P2().minus(P1()).Absolute();
	}
}