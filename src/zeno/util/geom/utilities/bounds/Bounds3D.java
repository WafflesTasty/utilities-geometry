package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collidables.geometry.spatial.Cuboid;
import zeno.util.geom.collidables.geometry.spatial.Sphere;

/**
 * The {@code Bounds3D} interface defines object bounds in three dimensions.
 *
 * @author Zeno
 * @since Apr 06, 2019
 * @version 1.0
 * 
 * 
 * @see Bounds
 */
public interface Bounds3D extends Bounds
{		
	/**
	 * Returns the center x of the {@code Bounds3D}.
	 * 
	 * @return  the bounds center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code Bounds3D}.
	 * 
	 * @return  the bounds center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
	
	/**
	 * Returns the center z of the {@code Bounds3D}.
	 * 
	 * @return  the bounds center z
	 */
	public default float Z()
	{
		return Center().Z();
	}
		
	/**
	 * Returns the width of the {@code Bounds3D}.
	 * 
	 * @return  the bounds width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounds3D}.
	 * 
	 * @return  the bounds height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the depth of the {@code Bounds3D}.
	 * 
	 * @return  the bounds depth
	 */
	public default float Depth()
	{
		return Size().Z();
	}
	
	
	/**
	 * Returns the minimum x-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum x
	 */
	public default float XMin()
	{
		return Minimum().X();
	}
	
	/**
	 * Returns the maximum x-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum x
	 */
	public default float XMax()
	{
		return Maximum().X();
	}
	
	/**
	 * Returns the minimum y-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum y
	 */
	public default float YMin()
	{
		return Minimum().Y();
	}
	
	/**
	 * Returns the maximum y-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum y
	 */
	public default float YMax()
	{
		return Maximum().Y();
	}
	
	/**
	 * Returns the minimum z-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum z
	 */
	public default float ZMin()
	{
		return Minimum().Z();
	}
	
	/**
	 * Returns the maximum z-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum z
	 */
	public default float ZMax()
	{
		return Maximum().Z();
	}
	
	
	@Override
	public default Cuboid Box()
	{
		return (Cuboid) Bounds.super.Box();
	}
		
	@Override
	public default Sphere Ball()
	{
		return (Sphere) Bounds.super.Ball();
	}
			
	@Override
	public default Vector3 Minimum()
	{
		return (Vector3) Bounds.super.Minimum();
	}
	
	@Override
	public default Vector3 Maximum()
	{
		return (Vector3) Bounds.super.Maximum();
	}
	
	@Override
	public default Vector3 Center()
	{
		return (Vector3) Bounds.super.Center();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Bounds.super.Size();
	}
}