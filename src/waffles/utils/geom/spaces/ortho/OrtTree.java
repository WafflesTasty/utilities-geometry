package waffles.utils.geom.spaces.ortho;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.collidable.fixed.Point;
import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.ortho.queries.QRYAll;
import waffles.utils.geom.spaces.ortho.queries.QRYCuboid;
import waffles.utils.geom.spaces.ortho.queries.QRYPairs;
import waffles.utils.geom.spaces.ortho.queries.QRYPoint;
import waffles.utils.sets.keymaps.Pair;
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
 * @see Manifold
 * @see Bounded
 * @see Tree
 */
public class OrtTree<O extends Bounded> extends Tree implements Manifold<O>
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
	
	/**
	 * Creates a pair in the {@code OrtTree}.
	 * 
	 * @param k  a key object
	 * @param v  a value object
	 * @return   an object pair
	 * 
	 * 
	 * @see Pair
	 */
	public Pair<O, O> createPair(O k, O v)
	{
		return new Pair.Base<>(k, v);
	}

	
	@Override
	public Iterable<Pair<O, O>> Pairs()
	{
		return () -> new QRYPairs<>(this);
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