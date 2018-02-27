package zeno.util.geom.dimension.three;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry;
import zeno.util.geom.dimension.three.shapes.Cuboid;
import zeno.util.geom.utilities.bounds.Bounded3D;

/**
 * The {@code IGeometry3D} interface is the base for three-dimensional convex geometric shapes.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry
 * @see Bounded3D
 */
public interface IGeometry3D extends IGeometry, Bounded3D
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
	
	/**
	 * Indicates if the {@code IGeometry3D} contains a point.
	 * 
	 * @param x  the point's x-coördinate
	 * @param y  the point's y-coördinate
	 * @param z  the point's z-coördinate
	 * @return {@code true} if the point is contained
	 */
	public default boolean contains(float x, float y, float z)
	{
		return contains(new Vector3(x, y, z));
	}
}