package waffles.utils.geom.spaces.ortho;

import waffles.utils.algebra.elements.linear.vector.fixed.Vector2;
import waffles.utils.geom.bounds.Bounded2D;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.collidable.axial.cuboid.Rectangle;
import waffles.utils.geom.spaces.Space2D;

/**
 * An {@code OrtTree2D} defines an orthogonal tree, which partitions two-dimensional
 * space into a collection of equally spaced orthants.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Bounded2D
 * @see OrtTree
 * @see Space2D
 */
public class OrtTree2D<O extends Bounded2D> extends OrtTree<O> implements Space2D<O>, Bounded2D
{	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param x  a bounds x-coordinate
	 * @param y  a bounds y-coordinate
	 * @param w  a bounds width
	 * @param h  a bounds height
	 */
	public OrtTree2D(float x, float y, float w, float h)
	{
		this(new Rectangle(x, y, w, h));
	}
	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector2
	 */
	public OrtTree2D(Vector2 c, Vector2 s)
	{
		this(new Rectangle(c, s));
	}
	
	/**
	 * Creates a new {@code OrtTree2D}.
	 * 
	 * @param b  a rectangle bounds
	 * 
	 * 
	 * @see Rectangle
	 */
	public OrtTree2D(Rectangle b)
	{
		super(b);
	}

	
	@Override
	public Bounds2D Bounds()
	{
		return (Bounds2D) super.Bounds();
	}
}