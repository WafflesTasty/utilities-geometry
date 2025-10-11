package waffles.utils.geom.bounds.owners;

import waffles.utils.alg.utilities.affine.LinearMap;
import waffles.utils.geom.bounds.Bounds3D;

/**
 * A {@code Boundable3D} object defines a three-dimensional transformable {@code Bounds}.
 * 
 * @author Waffles
 * @since Mar 24, 2017
 * @version 1.0
 * 
 * 
 * @see Boundable
 * @see Bounded3D
 */
@FunctionalInterface
public interface Boundable3D extends Boundable, Bounded3D
{	
	@Override
	public abstract Bounds3D Bounds(LinearMap map);
	
	@Override
	public default Bounds3D Bounds()
	{
		return (Bounds3D) Boundable.super.Bounds();
	}
}