package waffles.utils.geom.spaces.trees.planar.bounds;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.planar.PlanarNodal;
import waffles.utils.geom.spaces.trees.planar.Plane;
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