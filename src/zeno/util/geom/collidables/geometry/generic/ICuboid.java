package zeno.util.geom.collidables.geometry.generic;

import zeno.util.algebra.linear.vector.Vector;
import zeno.util.algebra.linear.vector.Vectors;
import zeno.util.geom.collidables.ICollision;
import zeno.util.geom.collidables.IGeometry;
import zeno.util.geom.collidables.affine.Point;
import zeno.util.geom.collidables.collisions.geometry.CLSCuboid;
import zeno.util.geom.transforms.AffineMap;
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
//	public static class MappedBounds implements Bounds
//	{
//		private AffineMap map;
//		private ICuboid shape;
//		
//		public MappedBounds(ICuboid s, AffineMap m)
//		{
//			shape = s;
//			map = m;
//		}
//		
//		@Override
//		public Vector Center()
//		{
//			int dim = Dimension();
//			
//			Point p = new Point(Center());
//			Vector c = Vectors.resize(Center(), dim + 1);
//			Vector s = Vectors.resize(Size(), dim + 1);
//			c.set(1f, dim);
//
//			c = (Vector) map.map(c);
//			s = (Vector) map.map(s);
//			return null;
//		}
//	}
//	
//	
//	@Override
//	public default Bounds Bounds(AffineMap map)
//	{
//		int dim = Dimension();
//		
//		Vector c = Vectors.resize(Center(), dim + 1);
//		Vector s = Vectors.resize(Size(), dim + 1);
//		
//		// TODO Auto-generated method stub
//		return IGeometry.super.Bounds(map);
//	}
	
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