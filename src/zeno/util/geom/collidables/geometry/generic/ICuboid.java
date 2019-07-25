package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.geom.ITransformation;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.geometry.CLSCuboid;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.Transforms;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {ICuboid} interface defines the collision operations for cuboid geometry.
 * 
 * @author Zeno
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see IGeometry
 */
public interface ICuboid extends IGeometry
{	
	public static class MappedBounds implements Bounds
	{
		private AffineMap map;
		private ICuboid shape;
		
		public MappedBounds(ICuboid s, AffineMap m)
		{
			shape = s;
			map = m;
		}
		
		@Override
		public float Radius()
		{
			Point s = new Point(shape.Size(), 0f);
			Vector v = ((Point) map.map(s)).VMatrix();
			return v.times(0.5f).norm();
		}
		
		@Override
		public Vector Center()
		{
			Point p = new Point(shape.Center(), 1f);
			return ((Point) map.map(p)).VMatrix();
		}
		
		@Override
		public Vector Size()
		{
			Point s = new Point(shape.Size(), 0f);
			ITransformation abs = Transforms.abs(map);
			return ((Point) abs.map(s)).VMatrix();
		}
	}
	
	
	@Override
	public default Bounds Bounds(AffineMap map)
	{
		return new MappedBounds(this, map);
	}
	
	@Override
	public default ICollision Collisions()
	{
		return new CLSCuboid(this);
	}
	
	// Optional Bounds overrides.
	
	@Override
	public default ICuboid Box()
	{
		return this;
	}
}