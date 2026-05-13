package waffles.utils.geom.spaces.arboreal.planar.bnd;

import waffles.utils.geom.shapes.bounds.convex.cuboid.BNDCuboid2D;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNodal;

/**
 * A {@code BNDPlanar} defines dynamic {@code Bounds2D} for a {@code PlanarNode}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see BNDCuboid2D
 * @see BNDPlanar
 */
public interface BNDPlanar2D extends BNDPlanar, BNDCuboid2D
{
	/**
	 * A {@code BNDPlanar.Base} implements a basic {@code BNDPlanar2D}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDPlanar2D
	 * @see BNDPlanar
	 */
	public static class Base extends BNDPlanar.Base implements BNDPlanar2D
	{
		/**
		 * Creates a new {@code BNDPlanar2D.Base}.
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
		return 2;
	}
}