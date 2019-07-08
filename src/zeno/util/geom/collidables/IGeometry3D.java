package zeno.util.geom.collidables;

import zeno.util.geom.ICollideable3D;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;
import zeno.util.geom.utilities.bounds.Bounds3D;
import zeno.util.geom.utilities.bounds.IBounded3D;

/**
 * The {@code IGeometry3D} interface is the base for three-dimensional convex geometric shapes.
 *
 * @author Zeno
 * @since Aug 22, 2015
 * @version 1.0
 * 
 * 
 * @see IGeometry 
 * @see ICollideable3D
 * @see IBounded3D
 * @see Bounds3D
 */
public interface IGeometry3D extends IGeometry, ICollideable3D, IBounded3D, Bounds3D
{
	/**
	 * Indicates if the {@code IGeometry3D} intersects a cuboid.
	 * 
	 * @param x  the cuboid's center x-coördinate
	 * @param y  the cuboid's center y-coördinate
	 * @param z  the cuboid's center z-coördinate
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 * @return {@code true} if the cuboid is contained
	 */
	public default boolean intersects(float x, float y, float z, float w, float h, float d)
	{
		return intersects(new Cuboid(x, y, z, w, h, d));
	}
	
	/**
	 * Indicates if the {@code IGeometry3D} contains a cuboid.
	 * 
	 * @param x  the cuboid's center x-coördinate
	 * @param y  the cuboid's center y-coördinate
	 * @param z  the cuboid's center z-coördinate
	 * @param w  the cuboid's width
	 * @param h  the cuboid's height
	 * @param d  the cuboid's depth
	 * @return {@code true} if the cuboid is contained
	 */
	public default boolean contains(float x, float y, float z, float w, float h, float d)
	{
		return contains(new Cuboid(x, y, z, w, h, d));
	}

	
	@Override
	public default Bounds3D Bounds()
	{
		return this;
	}
}