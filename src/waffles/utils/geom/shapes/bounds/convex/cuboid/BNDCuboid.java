package waffles.utils.geom.shapes.bounds.convex.cuboid;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.BNDGeometry;
import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;

/**
 * A {@code BNDCuboid} defines dynamic {@code Bounds} for a {@code HyperCuboid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDGeometry
 */
@FunctionalInterface
public interface BNDCuboid extends BNDGeometry
{
	/**
	 * A {@code BNDCuboid.Transform} computes a transformed {@code BNDCuboid}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDGeometry
	 * @see BNDCuboid
	 */
	public static class Transform extends BNDGeometry.Transform implements BNDCuboid
	{
		/**
		 * Creates a new {@code Transform}.
		 * 
		 * @param b  a base bounds
		 * @param m  a linear map
		 * 
		 * 
		 * @see BNDCuboid
		 * @see LinearMap
		 */
		public Transform(BNDCuboid b, LinearMap m)
		{
			super(b, m);
		}
		
		
		@Override
		public HyperCuboid Geometry()
		{
			return (HyperCuboid) super.Geometry();
		}

		@Override
		public Arrow Scale()
		{
			int d = Dimension();
			
			Arrow s = Base().Scale();
			Matrix a = Map().Matrix(d + 1);

			a = a.resize(d, d).absolute();
			a = a.times(s.Vector());
			
			Vector v = (Vector) a;
			return new Arrow(v);
		}
	}
	
	@Override
	public abstract HyperCuboid Geometry();
		
	
	@Override
	public default Factory Factory()
	{
		return m -> new Transform(this, m);
	}
	
	@Override
	public default float Diameter()
	{
		return Scale().norm();
	}
	
	@Override
	public default Arrow Scale()
	{
		return Geometry().Scale();
	}
}