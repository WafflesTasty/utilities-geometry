package zeno.util.geom.tools.bounds;

import zeno.util.geom.shapes.solids.lateral.Cuboid;

/**
 * The {@code IBound3D} interface defines an object bound in three dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 * 
 * @see IBound2D
 */
public interface IBound3D extends IBound2D
{
	/**
	 * Returns the object's bounding {@code Cuboid}.
	 * 
	 * @return  the object's cuboid bounds
	 * @see Cuboid
	 */
	public default Cuboid Bounds3D()
	{
		return new Cuboid
		(
			0.5f * (XMin() + XMax()),
			0.5f * (YMin() + YMax()),
			0.5f * (ZMin() + ZMax()),
			XMax() - XMin(),
			YMax() - YMin(),
			ZMax() - ZMin()
		);
	}
	
	
	/**
	 * Returns the minimum z-coördinate of the object.
	 * 
	 * @return  the object's minimum z
	 */
	public abstract float ZMin();
	
	/**
	 * Returns the maximum z-coördinate of the object.
	 * 
	 * @return  the object's maximum z
	 */
	public abstract float ZMax();
}