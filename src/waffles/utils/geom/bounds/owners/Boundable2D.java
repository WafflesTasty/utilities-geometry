package waffles.utils.geom.bounds.owners;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.bounds.Bounds2D;

/**
 * A {@code Boundable2D} object defines a two-dimensional transformable {@code Bounds}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see Boundable
 * @see Bounded2D
 */
@FunctionalInterface
public interface Boundable2D extends Boundable, Bounded2D
{	
	@Override
	public abstract Bounds2D Bounds(LinearMap map);
	
	@Override
	public default Bounds2D Bounds()
	{
		return (Bounds2D) Boundable.super.Bounds();
	}
}