package waffles.utils.geom.spaces.trees.planar.bipar;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.array.ArrayTree;
import waffles.utils.geom.spaces.trees.planar.Plane;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BPSpace} implements an {@code ArrayTree} as a wrapper around a {@code BPTree}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ArrayTree
 * @see AtomicSet
 * @see Bounded
 * @see BPNode
 */
public class BPSpace<O extends Bounded> implements AtomicSet<O>, ArrayTree<O, BPNode<O>>
{
	/**
	 * Defines the default maximum depth.
	 */
	public static final int MAX_DEPTH = 6;
	

	private int depth;
	private BPTree<O> tree;
	private HyperCuboid bounds;
	
	/**
	 * Creates a new {@code BPSpace}.
	 * 
	 * @param b  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public BPSpace(HyperCuboid b)
	{
		this(b, MAX_DEPTH);
	}
		
	/**
	 * Creates a new {@code BPSpace}.
	 * 
	 * @param b  a bounding box
	 * @param d  a maximum depth
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public BPSpace(HyperCuboid b, int d)
	{
		Object o = null;
		o = Factory().node(o);
		tree = new BPTree<>(this);
		tree.setRoot((Nodal) o);
		
		bounds = b;
		depth = d;
	}
	
	/**
	 * Creates a new {@code BPSpace}.
	 * 
	 * @param o  a space origin
	 * @param s  a space scale
	 * @param d  a maximum depth
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public BPSpace(Point o, Arrow s, int d)
	{
		this(HyperCuboid.create(o, s), d);
	}
	
	/**
	 * Creates a new {@code BPSpace}.
	 * 
	 * @param o  a space origin
	 * @param s  a space scale
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public BPSpace(Point o, Arrow s)
	{
		this(o, s, MAX_DEPTH);
	}
	
		
	@Override
	public void add(O obj)
	{
		BPNode<O> n = Root();
		if(!n.contains(obj))
		{
			n.add(obj);
			return;
		}
		
		Point min = obj.Bounds().Minimum();
		Point max = obj.Bounds().Maximum();
		
		while(!n.isLeaf())
		{
			Plane p = n.Plane();
			if(!n.contains(max))
				n = n.LChild();
			else if(n.contains(min))
				n = n.RChild();
			else
				break;
		}
		
		if(n.Depth() == depth)
		{
			n.add(obj);
			return;
		}
		
		int k = 0;
		int d = Dimension();
		if(!n.isRoot())
		{
			BPNode<O> p = n.Parent();
			k = p.Plane().Axis() + 1;
		}
		
		Point l = n.Bounds().Minimum();
		Point r = n.Bounds().Maximum();
		
		float dl = Floats.abs(min.aff(k) - l.aff(k));
		float dr = Floats.abs(max.aff(k) - r.aff(k));
		float dv = dr < dl ? min.aff(k) : max.aff(k);
		
		Object o = null;
		Plane p = new Plane(k, d, dv);
		BPNode<?> x = Factory().node(p);
		BPNode<?> y = Factory().node(o);
		BPNode<?> z = Factory().node(o);
		
		x.setLChild(y);
		x.setRChild(z);
		n.replace(x);
	}
	
	@Override
	public void remove(O obj)
	{
		BPNode<O> n = Root();
		if(!n.contains(obj))
		{
			n.remove(obj);
			return;
		}
		
		Point min = obj.Bounds().Minimum();
		Point max = obj.Bounds().Maximum();
		
		while(!n.isLeaf())
		{
			Plane p = n.Plane();
			if(!n.contains(max))
				n = n.LChild();
			else if(n.contains(min))
				n = n.RChild();
			else
				break;
		}
		
		n.remove(obj);
	}
	
	@Override
	public void clear()
	{
		Tree().clear();
	}
	
			
	@Override
	public BPTree<O> Tree()
	{
		return tree;
	}
		
	@Override
	public Query<O, BPNode<O>> Query()
	{
		return () -> this;
	}
	
	@Override
	public BPTree.Factory Factory()
	{
		return () -> Tree();
	}
	
	@Override
	public Bounds Bounds()
	{
		return bounds.Bounds();
	}
	
	@Override
	public int Count()
	{
		return AtomicSet.super.Count();
	}
}