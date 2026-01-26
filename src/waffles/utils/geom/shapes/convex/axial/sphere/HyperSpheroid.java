package waffles.utils.geom.shapes.convex.axial.sphere;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.collide.collision.Collision;
import waffles.utils.geom.collide.collision.convex.spheres.CLSSpheroid;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid2D;
import waffles.utils.geom.shapes.bounds.convex.axial.spheroid.BNDSpheroid3D;
import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Ellipse;
import waffles.utils.geom.shapes.convex.axial.sphere.base.Spheroid;
import waffles.utils.geom.shapes.convex.axial.sphere.base.SpheroidND;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.tools.primitives.Doubles;
import waffles.utils.tools.primitives.Floats;

/**
 * A {HyperSpheroid} defines axis-aligned spheroid geometry with an origin and scale.
 *
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see AxialSet
 */
public interface HyperSpheroid extends AxialSet
{
	/**
	 * Defines the error margin of a {@code HyperSpheroid}.
	 */
	public static final double ERROR = Doubles.pow(2, -8);
		
	/**
	 * A {@code HyperSpheroid.Factory} generates {@code HyperSpheroid} geometry.
	 *
	 * @author Waffles
	 * @since 24 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see AxialSet
	 */
	public static interface Factory extends AxialSet.Factory
	{						
		@Override
		public default HyperSpheroid create(Point o, Arrow s)
		{
			return HyperSpheroid.create(o, s);
		}
		
		@Override
		public abstract HyperSpheroid Source();
	}
	
	
	/**
	 * Creates a {@code HyperSpheroid} from an origin and a scale.
	 * 
	 * @param o  a spheroid origin
	 * @param s  a spheroid scale
	 * @return   a spheroid
	 * 
	 * 
	 * @see Vector
	 */
	public static HyperSpheroid create(Vector o, Vector s)
	{
		return create(new Point(o, 1f), new Arrow(s));
	}
	
	/**
	 * Creates a {@code HyperSpheroid} from an origin and a scale.
	 * 
	 * @param o  a spheroid origin
	 * @param s  a spheroid scale
	 * @return   a spheroid
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public static HyperSpheroid create(Point o, Arrow s)
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
			float r = (sMin + sMax) / 4;
			return HyperSphere.create(o, r);
		}
		
		
		if(o.Dimension() == 2)
			return new Ellipse(o, s);
		if(o.Dimension() == 3)
			return new Spheroid(o, s);
		
		return new SpheroidND(o, s);
	}
	
	
	@Override
	public default Factory Factory()
	{
		return () -> this;
	}
			
	@Override
	public default Collision Collision()
	{
		return new CLSSpheroid(this, ERROR);
	}
			
	
	@Override
	public default Bounds Bounds(LinearMap m)
	{
		if(Dimension() == 2)
			return new BNDSpheroid2D(this, m);
		if(Dimension() == 3)
			return new BNDSpheroid3D(this, m);
		
		return new BNDSpheroid(this, m);
	}
	
	@Override
	public default Extremum Extremum()
	{
		return p ->
		{
			Point o = Origin();
			Point s = Scale().times(0.5f);
			Point h = p.hadamard(s);
			float n = h.norm();
			
			s = s.hadamard(s);
			h = p.hadamard(s);
			
			return o.plus(h.times(1f / n));
		};
	}
	
	@Override
	public default Bounds Bounds()
	{
		if(Dimension() == 2)
			return new BNDSpheroid2D(this);
		if(Dimension() == 3)
			return new BNDSpheroid3D(this);
		
		return new BNDSpheroid(this);
	}
}