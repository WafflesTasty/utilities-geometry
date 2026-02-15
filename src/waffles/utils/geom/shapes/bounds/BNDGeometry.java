package waffles.utils.geom.shapes.bounds;

import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.Geometry;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.utilities.tform.linear.LinearCompose;

/**
 * A {@code BNDGeometry} defines dynamic {@code Bounds} for a {@code Geometry}.
 *
 * @author Waffles
 * @since 14 Jan 2026
 * @version 1.1
 *
 * 
 * @see Bounds
 */
public interface BNDGeometry extends Bounds
{	
	/**
	 * A {@code BNDGeometry.Transform} computes a transformed {@code BNDGeometry}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDGeometry
	 */
	public static class Transform implements BNDGeometry
	{
		private LinearMap map;
		private BNDGeometry bnd;
		
		/**
		 * Creates a new {@code BNDGeometry.Transform}.
		 * 
		 * @param b  a base bounds
		 * @param m  a base map
		 * 
		 * 
		 * @see BNDGeometry
		 * @see LinearMap
		 */
		public Transform(BNDGeometry b, LinearMap m)
		{
			bnd = b;
			map = m;
		}
		
		
		/**
		 * Returns the map of the {@code Transform}.
		 * 
		 * @return  a base map
		 * 
		 * 
		 * @see LinearMap
		 */
		public LinearMap Map()
		{
			return map;
		}

		/**
		 * Returns the base of the {@code Transform}.
		 * 
		 * @return  a base bounds
		 * 
		 * 
		 * @see BNDGeometry
		 */
		public BNDGeometry Base()
		{
			return bnd;
		}
				
		
		@Override
		public Geometry Geometry()
		{
			return bnd.Geometry();
		}

		@Override
		public Point Origin()
		{
			Point o = Base().Origin();
			Affine a = Map().map(o);
			if(a instanceof Point)
			{
				return (Point) a;
			}
			
			return null;
		}
	}

	
	/**
	 * Returns the geometry of the {@code Bounds}.
	 * 
	 * @return  a source geometry
	 * 
	 * 
	 * @see Geometry
	 */
	public abstract Geometry Geometry();

	
	@Override
	public default Bounds.Factory Factory()
	{
		return m -> 
		{
			LinearMap map = new LinearCompose(m);
			return new Transform(this, map);
		};
	}
	
	@Override
	public default Point Origin()
	{
		return Geometry().Origin();
	}
}