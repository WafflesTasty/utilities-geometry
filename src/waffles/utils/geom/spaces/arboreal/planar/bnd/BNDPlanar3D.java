package waffles.utils.geom.spaces.arboreal.planar.bnd;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid3D;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNodal;

/**
 * A {@code BNDPlanar} defines dynamic {@code Bounds3D} for a {@code PlanarNode}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see BNDCuboid3D
 * @see BNDPlanar
 */
public interface BNDPlanar3D extends BNDPlanar, BNDCuboid3D
{
	/**
	 * A {@code BNDPlanar3D.Base} implements a basic {@code BNDPlanar3D}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDPlanar2D
	 * @see BNDPlanar
	 */
	public static class Base extends BNDPlanar.Base implements BNDPlanar3D
	{
		/**
		 * Creates a new {@code BNDPlanar3D.Base}.
		 * 
		 * @param n  a parent nodal
		 * 
		 * 
		 * @see PlanarNodal
		 */
		public Base(PlanarNodal n)
		{
			super(n);
		}
	}
	
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}