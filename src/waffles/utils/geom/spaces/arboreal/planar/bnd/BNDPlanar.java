package waffles.utils.geom.spaces.arboreal.planar.bnd;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid;
import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNodal;
import waffles.utils.geom.spatial.bounds.Bounds;

/**
 * A {@code BNDPlanar} defines dynamic {@code Bounds} for a {@code PlanarNode}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see BNDCuboid
 */
public interface BNDPlanar extends BNDCuboid
{
	/**
	 * Creates a new {@code BNDPlanar} of the appropriate nodal dimension.
	 * 
	 * @param n  a parent nodal
	 * @return   a nodal bound
	 * 
	 * 
	 * @see PlanarNodal
	 */
	public static BNDPlanar create(PlanarNodal n)
	{
		if(n.Dimension() == 2)
			return new BNDPlanar2D.Base(n);
		if(n.Dimension() == 3)
			return new BNDPlanar3D.Base(n);

		return new BNDPlanar.Base(n);
	}
	
	/**
	 * A {@code BNDPlanar.Base} implements a basic {@code BNDPlanar}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDPlanar
	 */
	public static class Base implements BNDPlanar
	{
		private Point min, max;
		private PlanarNodal node;
		
		/**
		 * Creates a new {@code BNDPlanar.Base}.
		 * 
		 * @param n  a parent nodal
		 * 
		 * 
		 * @see PlanarNodal
		 */
		public Base(PlanarNodal n)
		{
			node = n;
		}

		
		@Override
		public PlanarNodal Geometry()
		{
			return node;
		}
		
		@Override
		public Point Minimum()
		{
			if(min == null)
			{
				min = BNDPlanar.super.Minimum();
			}
			
			return min;
		}
		
		@Override
		public Point Maximum()
		{
			if(max == null)
			{
				max = BNDPlanar.super.Maximum();
			}
			
			return max;
		}
	}
	
	
	@Override
	public abstract PlanarNodal Geometry();
	
	@Override
	public default Point Minimum()
	{
		int d = Dimension();
		PlanarNodal n = Geometry();
		Bounds bnd = n.Arch().Set().Bounds();

		Vector m = bnd.Minimum().Vector();
		while(!n.Arch().isRoot())
		{
			PlanarNodal p = n.Arch().Parent();
			if(p.Arch().RChild() == n)
			{
				Plane a = p.Plane();
				float v = a.Value();
				int k = a.Axis();
				
				if(m.get(k) < v)
				{
					m.set(v, k);
				}
			}
		}
		
		return new Point(m, 1f);
	}
	
	@Override
	public default Point Maximum()
	{
		int d = Dimension();
		PlanarNodal n = Geometry();
		Bounds bnd = n.Arch().Set().Bounds();

		Vector m = bnd.Minimum().Vector();
		while(!n.Arch().isRoot())
		{
			PlanarNodal p = n.Arch().Parent();
			if(p.Arch().LChild() == n)
			{
				Plane a = p.Plane();
				float v = a.Value();
				int k = a.Axis();
				
				if(m.get(k) > v)
				{
					m.set(v, k);
				}
			}
		}
		
		return new Point(m, 1f);
	}
		
	@Override
	public default int Dimension()
	{
		return Geometry().Dimension();
	}
	
	@Override
	public default Point Origin()
	{
		Point m = Minimum();
		Point n = Maximum();

		Point o = m.plus(n);
		o = o.times(0.5f);
		return o;
	}
	
	@Override
	public default Arrow Scale()
	{
		Point m = Minimum();
		Point n = Maximum();
		
		Point s = n.minus(m);
		return s.arrow();
	}
}