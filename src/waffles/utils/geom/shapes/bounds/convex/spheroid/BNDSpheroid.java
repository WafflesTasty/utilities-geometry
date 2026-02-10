package waffles.utils.geom.shapes.bounds.convex.spheroid;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.shapes.bounds.BNDGeometry;
import waffles.utils.geom.shapes.convex.axial.sphere.HyperSpheroid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.global.fixed.AxisMap;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDSpheroid} defines dynamic {@code Bounds} for a {@code HyperSpheroid}.
 *
 * @author Waffles
 * @since Sep 11, 2019
 * @version 1.0
 *
 *
 * @see BNDGeometry
 */
public interface BNDSpheroid extends BNDGeometry
{
	/**
	 * A {@code BNDSpheroid.Transform} computes a transformed {@code BNDSpheroid}.
	 *
	 * @author Waffles
	 * @since 08 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDGeometry
	 * @see BNDSpheroid
	 */
	public static class Transform extends BNDGeometry.Transform implements BNDSpheroid
	{
		private Arrow scl;
		
		/**
		 * Creates a new {@code Transform}.
		 * 
		 * @param b  a base bounds
		 * @param m  a linear map
		 * 
		 * 
		 * @see BNDSpheroid
		 * @see LinearMap
		 */
		public Transform(BNDSpheroid b, LinearMap m)
		{
			super(b, m);
		}
		
		
		@Override
		public HyperSpheroid Geometry()
		{
			return (HyperSpheroid) super.Geometry();
		}

		@Override
		public Arrow Scale()
		{
			if(scl == null)
			{
				int d = Dimension();
				Arrow s = Base().Scale();
				
				AxisMap m = new AxisMap(Geometry());
				
				Matrix t = Map().Matrix(d + 1);
				Matrix u = m.Matrix(d + 1);
				Matrix a = t.times(u);
				a = a.resize(d, d);
				
				
				Vector v = Vectors.create(d);
				for(int k = 0; k < d; k++)
				{
					Vector c = a.Row(k);
					float n = c.norm();
					v.set(2 * n, k);
				}
				
				scl = new Arrow(v);
			}

			return scl;
		}
	}
	
	@Override
	public abstract HyperSpheroid Geometry();
		
	
	@Override
	public default Factory Factory()
	{
		return m -> new Transform(this, m);
	}
	
	@Override
	public default float Diameter()
	{
		float d = 0f;
		Point s = Scale();
		for(int k = 0; k < s.Dimension(); k++)
		{
			float v = s.aff(k);
			float w = Floats.abs(v);
			if(d < v)
			{
				d = v;
			}
		}
		
		return d;
	}
	
	@Override
	public default Arrow Scale()
	{
		return Geometry().Scale();
	}
}