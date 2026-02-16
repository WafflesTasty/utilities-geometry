package waffles.utils.geom.spaces.trees.planar.bipar;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.array.ArrayNodal;
import waffles.utils.geom.spaces.trees.planar.PlanarNode;
import waffles.utils.geom.spaces.trees.planar.Plane;
import waffles.utils.geom.spaces.trees.planar.bounds.BNDPlanar;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code BPNode} defines a single node in a {@code BPSpace}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PlanarNode
 * @see ArrayNodal
 * @see AtomicSet
 * @see Bounded
 */
public class BPNode<O extends Bounded> extends PlanarNode implements ArrayNodal<O>, AtomicSet<O>
{
	/**
	 * A {@code BPNode.Bounds} defines fixed {@code Bounds} for a {@code BPNode}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BNDPlanar
	 */
	public class Bounds implements BNDPlanar
	{
		private Point m, n;
		
		@Override
		public Point Minimum()
		{
			if(m == null)
			{
				m = BNDPlanar.super.Minimum();
			}

			return m;
		}

		@Override
		public BPNode<O> Geometry()
		{
			return BPNode.this;
		}
		
		@Override
		public Point Maximum()
		{
			if(n == null)
			{
				n = BNDPlanar.super.Maximum();
			}

			return n;
		}
	}
	
	
	private Bounds bnd;
	private JavaList<O> data;
	
	/**
	 * Creates a new {@code BPNode}.
	 * 
	 * @param t  a parent tree
	 * @param p  a splitting plane
	 * 
	 * 
	 * @see BPTree
	 * @see Plane
	 */
	public BPNode(BPTree<?> t, Plane p)
	{
		super(t, p);
		bnd = new Bounds();
		data = new JavaList<>();
	}

	
	@Override
	public BPTree<?> Set()
	{
		return (BPTree<?>) super.Set();
	}
	
	@Override
	public BPNode<O> Arch()
	{
		return this;
	}
	
	@Override
	public BPNode<O> Parent()
	{
		return (BPNode<O>) super.Parent();
	}
	
	@Override
	public BPNode<O> LChild()
	{
		return (BPNode<O>) super.LChild();
	}
	
	@Override
	public BPNode<O> RChild()
	{
		return (BPNode<O>) super.RChild();
	}
		
	@Override
	public JavaList<O> Data()
	{
		return data;
	}

	@Override
	public Bounds Bounds()
	{
		return bnd;
	}

		
	@Override
	public void remove(O o)
	{
		data.remove(o);
	}

	@Override
	public void add(O o)
	{
		data.add(o);
	}
	
	@Override
	public void clear()
	{
		super.clear();
		data.clear();
	}
}