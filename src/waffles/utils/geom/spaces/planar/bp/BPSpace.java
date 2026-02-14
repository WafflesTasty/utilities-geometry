package waffles.utils.geom.spaces.planar.bp;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.planar.PlanarBoreal;
import waffles.utils.geom.spaces.planar.Plane;
import waffles.utils.geom.spaces.planar.bp.queries.QRYCuboid;
import waffles.utils.geom.spaces.planar.bp.queries.QRYNodes;
import waffles.utils.geom.spaces.planar.bp.queries.QRYPoint;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.binary.BiTree;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BPSpace} implements a {@code PlanarBoreal} binary tree.
 * This space is intended to mimick the behavior of a traditional kd-tree.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PlanarBoreal
 * @see AtomicSet
 * @see Bounded
 * @see BPNode
 * @see BiTree
 */
public class BPSpace<O extends Bounded> extends BiTree implements AtomicSet<O>, PlanarBoreal<BPNode<O>, O>
{
	/**
	 * Defines the default maximum depth.
	 */
	public static final int MAX_DEPTH = 6;
	
	/**
	 * A {@code BPSpace.Factory} generates {@code BPNode} objects.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BiTree
	 */
	public class Factory implements BiTree.Factory
	{
		@Override
		public BPNode<O> node(Object... data)
		{
			Plane p = (Plane) data[0];
			return new BPNode<>(Tree(), p);
		}
		
		@Override
		public BPSpace<?> Tree()
		{
			return BPSpace.this;
		}
	}
	
	
	private int depth;
	private Bounds bnd;
	
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
		setRoot(Factory().node(o));
		bnd = b.Bounds();
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
	public Iterable<BPNode<O>> inorder()
	{
		return super.inorder();
	}
	
	@Override
	public Iterable<BPNode<O>> preorder()
	{
		return super.preorder();
	}
		
	@Override
	public Iterable<BPNode<O>> postorder()
	{
		return super.postorder();
	}
		
	@Override
	public Iterable<O> query(HyperCuboid c)
	{
		return () -> new QRYNodes<>(() -> new QRYCuboid<>(this, c));
	}

	@Override
	public Iterable<O> query(Point p)
	{
		return () -> new QRYNodes<>(() -> new QRYPoint<>(this, p));
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return new QRYNodes<>(inorder());
	}
		
	@Override
	public BPNode<O> Root()
	{
		return (BPNode<O>) super.Root();
	}
	
	
	@Override
	public Bounds Bounds()
	{
		return bnd;
	}
	
	@Override
	public Factory Factory()
	{
		return new Factory();
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
		BPNode<O> x = Factory().node(p);
		BPNode<O> y = Factory().node(o);
		BPNode<O> z = Factory().node(o);
		
		x.setLChild(y);
		x.setRChild(z);
		n.replace(x);
	}
	
	@Override
	public int Count()
	{
		return AtomicSet.super.Count();
	}
}