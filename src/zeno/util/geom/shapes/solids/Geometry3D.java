package zeno.util.geom.shapes.solids;

import zeno.util.algebra.tensors.vectors.fixed.Vector3;
import zeno.util.geom.IGeometry3D;
import zeno.util.geom.shapes.other.NGeometry;

/**
 * The {@code Geometry2D} class is the base class for closed three-dimensional shapes.
 *
 * @since Apr 29, 2016
 * @author Zeno
 * 
 * @see IGeometry3D
 * @see NGeometry
 */
public abstract class Geometry3D extends NGeometry implements IGeometry3D
{
	/**
	 * Creates a new {@code Geometry3D}.
	 * 
	 * @param center  the geometry's center
	 * @param size  the geometry's size
	 * @see Vector3
	 */
	public Geometry3D(Vector3 center, Vector3 size)
	{
		super(center, size);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 * 
	 * @param size  the geometry's size
	 * @see Vector3
	 */
	public Geometry3D(Vector3 size)
	{
		this(new Vector3(), size);
	}
	
	/**
	 * Creates a new {@code Geometry3D}.
	 */
	public Geometry3D()
	{
		this(new Vector3(1, 1, 1));
	}
	

	@Override
	public Vector3 Center()
	{
		return (Vector3) super.Center();
	}

	@Override
	public Vector3 Size()
	{
		return (Vector3) super.Size();
	}
}