package waffles.utils.geom.spaces.ortho;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.Space;
import waffles.utils.geom.spaces.ortho.queries.QRYAll;
import waffles.utils.geom.spaces.ortho.queries.QRYCuboid;
import waffles.utils.geom.spaces.ortho.queries.QRYPoint;
import waffles.utils.sets.mutable.AtomicSet;
import waffles.utils.sets.trees.Tree;

/**
 * An {@code OrtTree} defines an orthogonal tree, which partitions n-dimensional
 * space into a collection of equally spaced orthants.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see AtomicSet
 * @see Bounded
 * @see Space
 * @see Tree
 */
public class OrtTree<O extends Bounded> extends Tree implements Bounded, AtomicSet<O>, Space<O>
{		
	/**
	 * Creates a new {@code OrtTree}.
	 * 
	 * @param b  a cuboid bounds
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public OrtTree(HyperCuboid b)
	{
		this(b.Origin(), b.Size());
	}
	
	/**
	 * Creates a new {@code OrtTree}.
	 * 
	 * @param c  a bounds center
	 * @param s  a bounds size
	 * 
	 * 
	 * @see Vector
	 */
	public OrtTree(Vector c, Vector s)
	{
		setRoot(createNode(c ,s));
	}

				
	@Override
	public Iterable<OrtNode<O>> BFSearch()
	{
		return super.BFSearch();
	}
	
	@Override
	public Iterable<OrtNode<O>> DFSearch()
	{
		return super.DFSearch();
	}

	@Override
	public Iterable<O> query(HyperCuboid c)
	{
		if(!Root().intersects(c))
			return Root().Objects();
		else
		{
			return () -> new QRYCuboid<>(this, c);
		}
	}

	@Override
	public Iterable<O> query(Point p)
	{
		if(!Root().contains(p))
			return Root().Objects();
		else
		{
			return () -> new QRYPoint<>(this, p);
		}
	}
	
	
	@Override
	public OrtNode<O> createNode(Object... vals)
	{
		Vector c = (Vector) vals[0];
		Vector s = (Vector) vals[1];
		return new OrtNode<>(this, c, s);
	}

	@Override
	public Iterator<O> iterator()
	{
		return new QRYAll<>(this);
	}
	
	@Override
	public OrtNode<O> Root()
	{
		return (OrtNode<O>) super.Root();
	}

	@Override
	public Bounds Bounds()
	{
		return Root().Bounds();
	}

	
	@Override
	public boolean contains(O obj)
	{
		OrtNode<O> curr = Root();
		while(true)
		{
			int i = curr.index(obj);
			if(i == -1)
			{
				return curr.contains(obj);
			}
			
			
			if(curr.isLeaf())
			{
				return false;
			}
			
			curr = curr.Child(i);
		}
	}

	@Override
	public void remove(O obj)
	{
		OrtNode<O> curr = Root();
		while(true)
		{
			int i = curr.index(obj);
			if(i == -1)
			{
				curr.remove(obj);
				while(curr.isEmpty())
				{
					curr.clear();
					curr = curr.Parent();
					if(curr == null)
						break;
				}
				
				return;
			}
			
			
			if(curr.isLeaf())
			{
				return;
			}
			
			curr = curr.Child(i);
		}
	}

	@Override
	public void add(O obj)
	{
		OrtNode<O> curr = Root();
		while(true)
		{
			int i = curr.index(obj);
			if(i == -1)
			{
				curr.add(obj);
				return;
			}
			
			
			if(curr.isLeaf())
			{
				curr.split();
			}
			
			curr = curr.Child(i);
		}
	}

	@Override
	public void clear()
	{
		Root().clear();
	}

	@Override
	public int Count()
	{
		int count = 0;
		for(OrtNode<O> node : BFSearch())
		{
			count += node.Count();
		}
		
		return count;
	}
}