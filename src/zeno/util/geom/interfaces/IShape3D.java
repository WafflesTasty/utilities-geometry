package zeno.util.geom.interfaces;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IShape;

/**
 * The {@code IShape3D} interface defines a three-dimensional convex shape.
 *
 * @since Aug 22, 2015
 * @author Zeno
 * 
 * @see IGeometry3D
 * @see IShape
 */
public interface IShape3D extends IShape, IGeometry3D
{
	/**
	 * Returns the center x of the {@code IShape3D}.
	 * 
	 * @return  the shape's center x
	 */
	public default float X()
	{
		return Center().X();
	}
	
	/**
	 * Returns the center y of the {@code IShape3D}.
	 * 
	 * @return  the shape's center y
	 */
	public default float Y()
	{
		return Center().Y();
	}
	
	/**
	 * Returns the center z of the {@code IShape3D}.
	 * 
	 * @return  the shape's center z
	 */
	public default float Z()
	{
		return Center().Z();
	}
			
	/**
	 * Returns the width of the {@code IShape3D}.
	 * 
	 * @return  the shape's width
	 */
	public default float Width()
	{
		return Size().X();
	}
	
	/**
	 * Returns the depth of the {@code IShape3D}.
	 * 
	 * @return  the shape's depth
	 */
	public default float Depth()
	{
		return Size().Z();
	}
	
	/**
	 * Returns the height of the {@code IShape3D}.
	 * 
	 * @return  the shape's height
	 */
	public default float Height()
	{
		return Size().Y();
	}
	
		
	@Override
	public default float XMin()
	{
		return X() - Width() / 2;
	}

	@Override
	public default float XMax()
	{
		return X() + Width() / 2;
	}

	@Override
	public default float YMin()
	{
		return Y() - Height() / 2;
	}

	@Override
	public default float YMax()
	{
		return Y() + Height() / 2;
	}
	
	@Override
	public default float ZMin()
	{
		return Z() - Depth() / 2;
	}

	@Override
	public default float ZMax()
	{
		return Z() + Depth() / 2;
	}

	
	@Override
	public abstract Vector3 Center();

	@Override
	public abstract Vector3 Size();
}