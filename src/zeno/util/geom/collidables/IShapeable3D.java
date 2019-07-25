package zeno.util.geom.collidables;

import zeno.util.algebra.linear.vector.fixed.Vector3;
import zeno.util.geom.ICollideable3D;
import zeno.util.geom.transformables.IAdjustable3D;
import zeno.util.geom.utilities.bounds.Bounds;
import zeno.util.geom.utilities.bounds.Bounds3D;
import zeno.util.geom.utilities.bounds.IBounded3D;

/**
 * The {@code IShapeable3D} interface defines an object that has a 3D geometric shape.
 * It can be collided with through affine spaces and can be transformed.
 *
 * @author Zeno
 * @since Feb 27, 2018
 * @version 1.0
 * 
 * 
 * @see IBounded3D
 * @see IAdjustable3D
 * @see ICollideable3D
 * @see IShapeable
 */
public interface IShapeable3D extends IAdjustable3D, IBounded3D, ICollideable3D, IShapeable
{
	@Override
	public abstract IGeometry3D Shape();

	@Override
	public default Bounds3D Bounds()
	{
		Bounds bounds = IShapeable.super.Bounds();
		return new Bounds3D()
		{
			@Override
			public Vector3 Minimum()
			{
				return (Vector3) bounds.Minimum();
			}
			
			@Override
			public Vector3 Maximum()
			{
				return (Vector3) bounds.Maximum();
			}
			
			
			@Override
			public Vector3 Center()
			{
				return (Vector3) bounds.Center();
			}
			
			@Override
			public Vector3 Size()
			{
				return (Vector3) bounds.Size();
			}
			
			
			@Override
			public float Diameter()
			{
				return bounds.Diameter();
			}
			
			@Override
			public float Radius()
			{
				return bounds.Radius();
			}
		};
	}
}