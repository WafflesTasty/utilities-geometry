package waffles.utils.geom.spatial;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.spatial.types.Movable3D;
import waffles.utils.geom.spatial.types.Scalable3D;

/**
 * An {@code Aligned3D} object can be axis-aligned in a three-dimensional vector space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.0
 * 
 * 
 * @see Aligned
 * @see Scalable3D
 * @see Movable3D
 */
public interface Aligned3D extends Aligned, Scalable3D, Movable3D
{
	@Override
	public default Vector3 Origin()
	{
		return (Vector3) Spatial().Origin();
	}
	
	@Override
	public default Vector3 Size()
	{
		return (Vector3) Spatial().Size();
	}
	

	@Override
	public default float Depth()
	{
		return Scalable3D.super.Depth();
	}

	@Override
	public default float Height()
	{
		return Scalable3D.super.Height();
	}

	@Override
	public default float Width()
	{
		return Scalable3D.super.Width();
	}
	
	
	@Override
	public default float X()
	{
		return Movable3D.super.X();
	}

	@Override
	public default float Y()
	{
		return Movable3D.super.Y();
	}

	@Override
	public default float Z()
	{
		return Movable3D.super.Z();
	}
}