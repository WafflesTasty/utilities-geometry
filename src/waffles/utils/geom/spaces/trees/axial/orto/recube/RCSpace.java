package waffles.utils.geom.spaces.trees.axial.orto.recube;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.array.ArrayTree;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * An {@code RCSpace} defines an object {@code Manifold} based on an {@code RCTree}.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ArrayTree
 * @see AtomicSet
 * @see RCNode
 */
public class RCSpace<O extends Bounded> implements AtomicSet<O>, ArrayTree<O, RCNode<O>>
{
	/**
	 * Defines the default maximum depth.
	 */
	public static final int MAX_DEPTH = 6;


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
	public void clear()
	{
		Tree().clear();
	}
	

	@Override
	public RCTree<O> Tree()
	{
		return tree;
	}
	
	@Override
	public RCTree.Factory Factory()
	{
		return () -> Tree();
	}
		
	@Override
	public Query<O, RCNode<O>> Query()
	{
		return () -> this;
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