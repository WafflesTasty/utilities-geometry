package waffles.utils.geom.bounds.owners;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geomold.utilities.Transforms;

/**
 * A {@code Boundable} object defines an n-dimensional transformable {@code Bounds}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see Bounded
 */
@FunctionalInterface
public interface Boundable extends Bounded
{	
	/**
	 * Returns the bounds of the {@code Boundable}.
	 * 
	 * @param map  a linear map
	 * @return  a boundary
	 * 
	 * 
	 * @see LinearMap
	 * @see Bounds
	 */
	public abstract Bounds Bounds(LinearMap map);
	
	
	@Override
	public default Bounds Bounds()
	{
		return Bounds(Transforms.identity());
	}
}