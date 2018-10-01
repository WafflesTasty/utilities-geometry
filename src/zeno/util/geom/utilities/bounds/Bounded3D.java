package zeno.util.geom.utilities.bounds;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.collideables.geometry.spatial.shapes.Cuboid;

/**
 * The {@code Bounded3D} interface defines an object bound in three dimensions.
 *
 * @since Aug 25, 2015
 * @author Zeno
 * 
 * @see Bounded
 */
public interface Bounded3D extends Bounded
{		
	/**
	 * Returns the center x of the {@code Bounded2D}.
	 * 
	 * @return  the object's center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code Bounded2D}.
	 * 
	 * @return  the object's center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
	
	/**
	 * Returns the center z of the {@code Bounded3D}.
	 * 
	 * @return  the object's center z
	 */
	public default float Z()
	{
		return Center().Z();
	}
		
	/**
	 * Returns the width of the {@code Bounded2D}.
	 * 
	 * @return  the object's width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the height of the {@code Bounded2D}.
	 * 
	 * @return  the object's height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
	/**
	 * Returns the depth of the {@code Bounded3D}.
	 * 
	 * @return  the object's depth
	 */
	public default float Depth()
	{
		return Size().Z();
	}
	
	
	/**
	 * Returns the minimum x-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's minimum x
	 */
	public default float XMin()
	{
		return X() - Width() / 2;
	}
	
	/**
	 * Returns the maximum x-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's maximum x
	 */
	public default float XMax()
	{
		return X() + Width() / 2;
	}
	
	/**
	 * Returns the minimum y-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's minimum y
	 */
	public default float YMin()
	{
		return Y() - Height() / 2;
	}
	
	/**
	 * Returns the maximum y-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's maximum y
	 */
	public default float YMax()
	{
		return Y() + Height() / 2;
	}
	
	/**
	 * Returns the minimum z-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's minimum z
	 */
	public default float ZMin()
	{
		return Z() - Depth() / 2;
	}
	
	/**
	 * Returns the maximum z-coördinate of the {@code Bounded2D}.
	 * 
	 * @return  the object's maximum z
	 */
	public default float ZMax()
	{
		return Z() + Depth() / 2;
	}


	@Override
	public abstract Cuboid Bounds();
	
	@Override
	public default Vector3 Center()
	{
		return Bounds().Center();
	}
	
	@Override
	public default Vector3 Size()
	{
		return Bounds().Size();
	}
}