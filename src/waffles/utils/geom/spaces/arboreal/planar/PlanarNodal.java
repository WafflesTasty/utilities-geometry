package waffles.utils.geom.spaces.arboreal.planar;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.spaces.arboreal.axial.AxialNodal;
import waffles.utils.geom.spaces.arboreal.planar.bnd.BNDPlanar;
import waffles.utils.geom.spaces.arboreal.planar.bnd.BNDPlanar2D;
import waffles.utils.geom.spaces.arboreal.planar.bnd.BNDPlanar3D;
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
 * @see HyperCuboid
 * @see AxialNodal
 * @see BiNodal
 */
public interface PlanarNodal extends AxialNodal, BiNodal, HyperCuboid
{	
	/**
	 * Returns a {@code PlanarNodal} plane.
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