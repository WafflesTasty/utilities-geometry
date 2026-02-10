package waffles.utils.geom.shapes.convex.axial.cube;

import waffles.utils.alg.lin.measure.matrix.Matrix;
import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.lin.measure.vector.Vectors;
import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid;
import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid2D;
import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid3D;
import waffles.utils.geom.shapes.collision.convex.hulls.CLSCuboid;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.shapes.convex.axial.cube.base.Cuboid;
import waffles.utils.geom.shapes.convex.axial.cube.base.CuboidND;
import waffles.utils.geom.shapes.convex.axial.cube.base.Rectangle;
import waffles.utils.geom.shapes.convex.hulls.Hull;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;

/**
 * A {HyperCuboid} defines axis-aligned cuboid geometry with an origin and scale.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see AxialSet
 * @see Hull
 */
public interface HyperCuboid extends Hull, AxialSet
{		
	/**
	 * A {@code HyperCuboid.Factory} generates {@code HyperCuboid} geometry.
	 *
	 * @author Waffles
	 * @since 24 Jan 2026
	 * @version 1.1
	 * 
	 * 
	 * @see AxialSet
	 * @see Hull
	 */
	public static interface Factory extends AxialSet.Factory, Hull.Factory
	{	
		@Override
		public abstract HyperCuboid Source();
		
		@Override
		public default HyperCuboid create(Point o, Arrow s)
		{
			return HyperCuboid.create(o, s);
		}
		
		@Override
		public default HyperCuboid create(Matrix s)
		{
			return (HyperCuboid) AxialSet.Factory.super.create(s);
		}
		
		@Override
		public default Point Point(int idx)
		{			
			Point s = Source().Scale();
			Point o = Source().Origin();
			
			int d = Source().Dimension();			
			Vector v = Vectors.create(d);
			
			for(int k = 0; k < d; k++)
			{
				float ok = o.aff(k);
				float sk = s.aff(k) / 2;
				
				if(Integers.bitAt(idx, k) == 0)
					v.set(ok - sk, k);
				else
					v.set(ok + sk, k);
			}
			
			return new Point(v, 1f);
		}
		
		@Override
		public default int Count()
		{
			int n = Source().Dimension();
			return Integers.pow(2, n);
		}
	}
	
	
	/**
	 * Defines the error margin of a {@code HyperCuboid}.
	 */
	public static final double ERROR = Doubles.pow(2, -8);
			
	/**
	 * Creates a {@code HyperCuboid} from an origin and a scale.
	 * 
	 * @param o  a cuboid origin
	 * @param s  a cuboid scale
	 * @return   a cuboid
	 * 
	 * 
	 * @see Vector
	 */
	public static HyperCuboid create(Vector o, Vector s)
	{
		return create(new Point(o, 1f), new Arrow(s));
	}
	
	/**
	 * Creates a {@code HyperCuboid} from an origin and a scale.
	 * 
	 * @param o  a cuboid origin
	 * @param s  a cuboid scale
	 * @return   a cuboid
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public static HyperCuboid create(Point o, Arrow s)
	{
		int n = s.Dimension();
		
		float sMin = Floats.MAX_VALUE;
		float sMax = Floats.MIN_VALUE;
		
		for(int k = 0; k < n; k++)
		{
			float sk = Floats.abs(s.aff(k));
			
			sMin = Floats.min(sMin, sk);
			sMax = Floats.max(sMax, sk);
		}
		
		if(sMax - sMin < ERROR)
		{
			float l = (sMin + sMax) / 4;
			return HyperCube.create(o, l);
		}
		
		if(o.Dimension() == 2)
			return new Rectangle(o, s);
		if(o.Dimension() == 3)
			return new Cuboid(o, s);
		
		return new CuboidND(o, s);
	}
	
			
	@Override
	public default Factory Factory()
	{
		return () -> this;
	}
			
	@Override
	public default CLSCuboid Collision()
	{
		return new CLSCuboid(this);
	}
	
	@Override
	public default Extremum Extremum()
	{
		return v ->
		{
			int d = Dimension();
			double e = Collision().Error();
			
			Point o = Origin();
			Point s = Scale().times(0.5f);
			
			Vector w = Vectors.create(d);
			for(int k = 0; k < d; k++)
			{
				float ok = o.aff(k);
				float sk = s.aff(k);
				float vk = v.aff(k);
				
				if(vk <= -e)
					w.set(ok - sk, k);
				else if(vk >= +e)
					w.set(ok + sk, k);
				else
					w.set(ok, k);
			}
			
			return new Point(w, 1f);
		};
	}
	
	@Override
	public default Bounds Bounds()
	{
		if(Dimension() == 2)
			return (BNDCuboid2D) () -> this;
		if(Dimension() == 3)
			return (BNDCuboid3D) () -> this;
		
			return (BNDCuboid) () -> this;
	}
}