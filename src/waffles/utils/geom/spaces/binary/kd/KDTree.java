package waffles.utils.geom.spaces.binary.kd;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.collidable.axial.cuboid.ICuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.binary.kd.queries.QRYAll;
import waffles.utils.geom.spaces.binary.kd.queries.QRYCuboid;
import waffles.utils.geom.spaces.binary.kd.queries.QRYPoint;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.sets.trees.binary.BiTree;
import waffles.utils.tools.patterns.semantics.Ordered;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code KDTree} defines an axis-aligned binary partition space.
 *
 * @author Waffles
 * @since 17 Jan 2022
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Iterable
 * @see Ordered
 * @see BiTree
 * @see Space
 */
public class KDTree<O> extends BiTree implements ICuboid, Iterable<O>, Space<O>
{
	private ICuboid shape;

	/**
	 * Creates a new {@code KDTree}.
	 * 
	 * @param s  a vector size
	 * 
	 * 
	 * @see Vector
	 */
	public KDTree(Vector s)
	{
		this(Vectors.create(s.Size()), s);
	}
	
	/**
	 * Creates a new {@code KDTree}.
	 * 
	 * @param c  a vector center
	 * @param s  a vector size
	 * 
	 * 
	 * @see Vector
	 */
	public KDTree(Vector c, Vector s)
	{
		shape = Geometries.cuboid(c, s);
	}
		
	/**
	 * Creates a new {@code KDTree}.
	 * 
	 * @param dim  a space dimension
	 */
	public KDTree(int dim)
	{
		this(Vectors.create(Floats.MAX_VALUE, dim));
	}
	
	
	@Override
	public Iterable<O> query(ICuboid c)
	{
		return () -> new QRYCuboid<>(this, c);
	}

	@Override
	public Iterable<O> query(Point p)
	{
		return () -> new QRYPoint<>(this, p);
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return new QRYAll<>(this);
	}

	@Override
	public KDNode<O> Root()
	{
		return (KDNode<O>) super.Root();
	}

	
	@Override
	public void setOrigin(Vector o)
	{
		shape.setOrigin(o);
	}

	@Override
	public void setSize(Vector s)
	{
		shape.setSize(s);
	}

	
	@Override
	public Vector Origin()
	{
		return shape.Origin();
	}

	@Override
	public Vector Size()
	{
		return shape.Size();
	}
	
	@Override
	public int Count()
	{
		if(Root() != null)
		{
			return Root().TreeSize();
		}
		
		return 0;
	}
}