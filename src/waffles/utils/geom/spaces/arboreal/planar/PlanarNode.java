package waffles.utils.geom.spaces.arboreal.planar;

import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.spaces.arboreal.planar.bnd.BNDPlanar;
import waffles.utils.sets.arboreal.binary.BiNode;

/**
 * 
 * A {@code PlanarNode} defines a node in a {@code PlanarBoreal}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see PlanarNodal
 * @see BiNode
 */
public class PlanarNode extends BiNode implements PlanarNodal
{
	private Plane plane;
	private BNDPlanar bnd;

	/**
	 * Creates a new {@code PlanarNode}.
	 * 
	 * @param t  a parent tree
	 * @param p  a splitting plane
	 * 
	 * 
	 * @see PlanarBoreal
	 * @see Plane
	 */
	public PlanarNode(PlanarBoreal<?> t, Plane p)
	{
		super(t); plane = p;
		bnd = BNDPlanar.create(this);
	}

	
	@Override
	public BNDPlanar Bounds()
	{
		return bnd;
	}
		
	@Override
	public PlanarNodal LChild()
	{
		return (PlanarNodal) super.LChild();
	}
	
	@Override
	public PlanarNodal RChild()
	{
		return (PlanarNodal) super.RChild();
	}
	
	@Override
	public PlanarBoreal<?> Set()
	{
		return (PlanarBoreal<?>) super.Set();
	}
	
	@Override
	public PlanarNodal Parent()
	{
		return (PlanarNodal) super.Parent();
	}
			
	@Override
	public PlanarNode Arch()
	{
		return this;
	}
	
	@Override
	public Plane Plane()
	{
		return plane;
	}
}