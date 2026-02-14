package waffles.utils.geom.spaces.planar;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.spaces.axial.AxialNodal;
import waffles.utils.geom.spaces.planar.bnd.BNDPlanar;
import waffles.utils.geom.spaces.planar.bnd.BNDPlanar2D;
import waffles.utils.geom.spaces.planar.bnd.BNDPlanar3D;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.arboreal.binary.BiNodal;

/**
 * A {@code PlanarNodal} defines an {@code AxialNodal} with a splitting plane.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 */
public interface PlanarNodal extends AxialNodal, BiNodal, HyperCuboid
{
	/**
	 * Returns the plane of the {@code PlanarNodal}.
	 * 
	 * @return  a splitting plane
	 * 
	 * 
	 * @see Plane
	 */
	public abstract Plane Plane();
	
	@Override
	public abstract PlanarNode Arch();
	
	
	@Override
	public default Bounds Bounds()
	{
		if(Dimension() == 2)
			return (BNDPlanar2D) () -> this;
		if(Dimension() == 3)
			return (BNDPlanar3D) () -> this;
			
		return (BNDPlanar) () -> this;
	}
	
	@Override
	public default int Dimension()
	{
		return Plane().Dimension();
	}
}