package waffles.utils.geom.bounds.fixed;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.fixed.Universe;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BNDUniverse} creates a bounding volume around an n-dimensional {@code Universe} object.
 *
 * @author Waffles
 * @since 16 Sep 2023
 * @version 1.0
 *
 * 
 * @see Bounds
 */
public class BNDUniverse implements Bounds
{
	private Universe src;
	
	/**
	 * Creates a new {@code BNDUniverse}.
	 * 
	 * @param s  a source universe
	 * 
	 * 
	 * @see Universe
	 */
	public BNDUniverse(Universe s)
	{
		src = s;
	}
	
	
	@Override
	public Vector Minimum()
	{
		int dim = src.Dimension();
		float val = Floats.NEG_INFINITY;
		return Vectors.create(val, dim);
	}
	
	@Override
	public Vector Maximum()
	{
		int dim = src.Dimension();
		float val = Floats.POS_INFINITY;
		return Vectors.create(val, dim);
	}
	
	@Override
	public float Diameter()
	{
		return Floats.POS_INFINITY;
	}
}