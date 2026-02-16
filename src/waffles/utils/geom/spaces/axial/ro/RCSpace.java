package waffles.utils.geom.spaces.axial.ro;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.axial.ro.queries.QRYPairs;
import waffles.utils.geom.spaces.trees.SpatialTree;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * An {@code RCSpace} defines a {@code Manifold} based on an {@code RCTree}.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see SpatialTree
 * @see AtomicSet
 * @see Bounded
 * @see RCNode
 */
public class RCSpace<O extends Bounded> implements AtomicSet<O>, Bounded, SpatialTree<O, RCNode<O>>
{
	/**
	 * Defines the default maximum depth.
	 */
	public static final int MAX_DEPTH = 6;
	
	/**
	 * An {@code RCSpace.Query} defines queries for an {@code RCSpace}.
	 *
	 * @author Waffles
	 * @since 16 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see SpatialTree
	 * @see Bounded
	 * @see RCNode
	 */
	@FunctionalInterface
	public interface Query<O extends Bounded> extends SpatialTree.Query<O, RCNode<O>>
	{
		@Override
		public default Iterator<O> in(RCNode<O> n)
		{
			return n.iterator();
		}
	}
	
	
	private int depth;
	private RCTree<O> tree;
	
	/**
	 * Creates a new {@code RCSpace}.
	 * 
	 * @param b  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public RCSpace(HyperCuboid b)
	{
		this(b, MAX_DEPTH);
	}
		
	/**
	 * Creates a new {@code RCSpace}.
	 * 
	 * @param b  a bounding box
	 * @param d  a maximum depth
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public RCSpace(HyperCuboid b, int d)
	{
		this(b.Origin(), b.Scale(), d);
	}
	
	/**
	 * Creates a new {@code RCSpace}.
	 * 
	 * @param o  a space origin
	 * @param s  a space scale
	 * @param d  a maximum depth
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public RCSpace(Point o, Arrow s, int d)
	{
		Factory fct = Factory();
		tree = new RCTree<>(this);
		tree.setRoot(fct.node(o, s));
		depth = d;
	}
	
	/**
	 * Creates a new {@code RCSpace}.
	 * 
	 * @param o  a space origin
	 * @param s  a space scale
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public RCSpace(Point o, Arrow s)
	{
		this(o, s, MAX_DEPTH);
	}
	
	
	@Override
	public RCNode<O> Root()
	{
		return Tree().Root();
	}
	
	@Override
	public RCTree.Factory Factory()
	{
		return () -> Tree();
	}
	
	@Override
	public Iterable<Pair<O, O>> Pairs()
	{
		return () -> new QRYPairs<>(Tree().Root());
	}
	
	@Override
	public Query<O> Query()
	{
		return () -> this;
	}
	
	@Override
	public RCTree<O> Tree()
	{
		return tree;
	}
	
	@Override
	public Bounds Bounds()
	{
		return Tree().Bounds();
	}
	
	
	@Override
	public void clear()
	{
		Tree().clear();
	}
	
	@Override
	public void add(O obj)
	{
		RCNode<O> n = Tree().Root();
		HyperCuboid spc =   n.Bounds().Box();
		HyperCuboid bnd = obj.Bounds().Box();
		
		if(!spc.contains(bnd))
		{
			n.add(obj);
			return;
		}

		while(true)
		{
			int d = n.Depth();
			int i = n.index(obj);
			if(i == -1 || d == depth)
			{
				n.add(obj);
				return;
			}
			
			
			if(n.isLeaf())
			{
				n.split();
			}

			n = n.Child(i);
		}
	}
	
	@Override
	public void remove(O obj)
	{
		HyperCuboid spc =     Bounds().Box();
		HyperCuboid bnd = obj.Bounds().Box();
		
		RCNode<O> n = Tree().Root();
		if(!spc.contains(bnd))
		{
			n.remove(obj);
			return;
		}

		while(true)
		{
			int i = n.index(obj);
			if(i == -1)
			{
				n.remove(obj);
				while(!n.hasData())
				{
					n.clear();
					n = n.Parent();
					if(n == null)
					{
						break;
					}
				}
				
				return;
			}
			
			
			if(n.isLeaf())
			{
				return;
			}
			
			n = n.Child(i);
		}
	}
	
	@Override
	public int Dimension()
	{
		return Tree().Dimension();
	}
	
	@Override
	public int Count()
	{
		int c = 0;
		for(Nodal n : BFSearch())
		{
			c += ((RCNode<O>) n).Count();
		}
		
		return c;
	}
}