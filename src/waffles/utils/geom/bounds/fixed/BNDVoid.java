package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.fixed.Void;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDVoid} creates a bounding volume around an n-dimensional {@code Void} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see Bounds
 */
public class BNDVoid implements Bounds
{
	private Void src;
	
	/**
	 * Creates a new {@code BNDVoid}.
	 * 
	 * @param s  a source void
	 * 
	 * 
	 * @see Void
	 */
	public BNDVoid(Void s)
	{
		src = s;
	}
	
	
	@Override
	public Vector Minimum()
	{
		int dim = src.Dimension();
		float val = Floats.POS_INFINITY;
		return Vectors.create(val, dim);
	}
	
	@Override
	public Vector Maximum()
	{
		int dim = src.Dimension();
		float val = Floats.NEG_INFINITY;
		return Vectors.create(val, dim);
	}
	
	@Override
	public float Diameter()
	{
		return 0f;
	}
}