package waffles.utils.geom.spaces.planar.bp;

import java.util.Iterator;

import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.planar.PlanarNode;
import waffles.utils.geom.spaces.planar.Plane;
import waffles.utils.geom.spaces.planar.bnd.BNDPlanar;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.countable.wrapper.JavaList;
import waffles.utils.sets.utilities.rooted.Nodal;

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
 * @see AtomicSet
 * @see Bounded
 */
public class BPNode<O extends Bounded> extends PlanarNode implements AtomicSet<O>
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
	 * @param s  a parent space
	 * @param p  a splitting plane
	 * 
	 * 
	 * @see BPSpace
	 * @see Plane
	 */
	public BPNode(BPSpace<?> s, Plane p)
	{
		super(s, p);
		bnd = new Bounds();
		data = new JavaList<>();
	}
	
	/**
	 * Checks for data in the {@code RCNode} hierarchy.
	 * 
	 * @return  {@code true} if any object is present
	 */
	public boolean hasData()
	{
		for(Nodal c : Children())
		{
			BPNode<O> n = (BPNode<O>) c;
			if(n.hasData())
			{
				return true;
			}
		}
		
		return !isEmpty();
	}

			
	@Override
	public BPSpace<O> Set()
	{
		return (BPSpace<O>) super.Set();
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
	public BPNode<O> Arch()
	{
		return this;
	}

	@Override
	public Bounds Bounds()
	{
		return bnd;
	}

	
	@Override
	public int Count()
	{
		return data.Count();
	}
	
	@Override
	public boolean isEmpty()
	{
		return data.isEmpty();
	}
		
	@Override
	public boolean contains(O o)
	{
		return data.contains(o);
	}
		
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
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