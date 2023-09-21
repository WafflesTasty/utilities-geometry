package waffles.utils.geom.spaces.ortho;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector3;
import waffles.utils.geom.bounds.Bounded3D;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.collidable.axial.cuboid.Cuboid;
import waffles.utils.geom.spaces.Space3D;

/**
 * An {@code OrtTree3D} defines an orthogonal tree, which partitions three-dimensional
 * space into a collection of equally spaced orthants.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Bounded3D
 * @see OrtTree
 * @see Space3D
 */
public class OrtTree3D<O extends Bounded3D> extends OrtTree<O> implements Space3D<O>, Bounded3D
{	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param x  a bounds x-coordinate
	 * @param y  a bounds y-coordinate
	 * @param z  a bounds z-coordinate
	 * @param w  a bounds width
	 * @param h  a bounds height
	 * @param d  a bounds depth
	 */
	public OrtTree3D(float x, float y, float z, float w, float h, float d)
	{
		this(new Cuboid(x, y, z, w, h, d));
	}
	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector3
	 */
	public OrtTree3D(Vector3 c, Vector3 s)
	{
		this(new Cuboid(c, s));
	}
	
	/**
	 * Creates a new {@code OrtTree3D}.
	 * 
	 * @param b  a cuboid bounds
	 * 
	 * 
	 * @see Cuboid
	 */
	public OrtTree3D(Cuboid b)
	{
		super(b);
	}
	
	
	@Override
	public Bounds3D Bounds()
	{
		return (Bounds3D) super.Bounds();
	}
}