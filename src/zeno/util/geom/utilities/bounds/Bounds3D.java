package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom._deprecated.collideables.geometry.spatial.shapes.Cuboid;
import zeno.util.geom._deprecated.collideables.geometry.spatial.shapes.Sphere;

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
	 * Returns the minimum x-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum x
	 */
	public abstract float XMin();
	
	/**
	 * Returns the maximum x-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum x
	 */
	public abstract float XMax();
	
	/**
	 * Returns the minimum y-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum y
	 */
	public abstract float YMin();
	
	/**
	 * Returns the maximum y-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum y
	 */
	public abstract float YMax();
	
	/**
	 * Returns the minimum z-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds minimum z
	 */
	public abstract float ZMin();
	
	/**
	 * Returns the maximum z-coördinate of the {@code Bounds3D}.
	 * 
	 * @return  the bounds maximum z
	 */
	public abstract float ZMax();
	
	
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
	
	
	@Override
	public default Cuboid Box()
	{
		return new Cuboid(Center(), Size());
	}
		
	@Override
	public default Sphere Sphere()
	{
		return new Sphere(Center(), Radius());
	}
			
	@Override
	public default Vector3 Minimum()
	{
		return new Vector3(XMin(), YMin(), ZMin());
	}
	
	@Override
	public default Vector3 Maximum()
	{
		return new Vector3(XMax(), YMax(), ZMax());
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