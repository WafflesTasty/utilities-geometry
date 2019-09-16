package zeno.util.geom.collidables;

import zeno.util.geom.ICollidable;
import zeno.util.geom.transforms.AffineMap;
import zeno.util.geom.utilities.bounds.IBounded;
import zeno.util.geom.utilities.bounds.Bounds;

/**
 * The {@code IGeometry} interface is the base for bounded n-dimensional shapes.
 * <br> It allows for basic collision detection and bounding volumes.
 * 
 * @author Zeno
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see ICollidable
 * @see IBounded
 * @see Bounds
 */
public interface IGeometry extends ICollidable, IBounded, Bounds
{	
	/**
	 * Creates bounds after transformation of the {@code IGeometry}.
	 * 
	 * @param map  an affine map to use
	 * @return  a transformed bounds
	 * 
	 * 
	 * @see AffineMap
	 * @see Bounds
	 */
	public abstract Bounds Bounds(AffineMap map);
	
	@Override
	public default Bounds Bounds()
	{
		return this;
	}
}