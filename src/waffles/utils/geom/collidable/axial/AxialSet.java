package waffles.utils.geom.collidable.axial;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spatial.data.Axial;

/**
 * An {@code AxialSet} defines n-dimensional geometry through a center and size vector.
 *
 * @author Waffles
 * @since 15 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometry
 * @see Axial
 */
public abstract class AxialSet implements IAxialSet
{
	private Vector size;
	private Vector origin;

	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param o  an origin vector
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public AxialSet(Vector o, Vector s)
	{
		size = s.absolute();
		origin = o;
	}
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param p  an origin point
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 * @see Point
	 */
	public AxialSet(Point p, Vector s)
	{
		this(p.Generator(), s);
	}
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param s  a size vector
	 * 
	 * 
	 * @see Vector
	 */
	public AxialSet(Vector s)
	{
		this(Vectors.create(s.Size()), s);
	}
	
	/**
	 * Creates a new {@code AxialSet}.
	 * 
	 * @param dim  a space dimension
	 */
	public AxialSet(int dim)
	{
		this(Vectors.create(2f, dim));
	}
	
	
	@Override
	public Vector Origin()
	{
		return origin;
	}
	
	@Override
	public Vector Size()
	{
		return size;
	}
}