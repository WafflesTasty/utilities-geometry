package waffles.utils.geom.spaces.arboreal.planar.data;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.arboreal.planar.PlanarBoreal;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.binary.BiArboreal;
import waffles.utils.sets.arboreal.binary.BiTree;
import waffles.utils.sets.arboreal.data.DataBoreal;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.utilities.arboreal.iterators.data.DataIterator;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code BPSpace} implements a {@code PlanarBoreal} as a {@code DataBoreal}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PlanarBoreal
 * @see DataBoreal
 * @see AtomicSet
 * @see Manifold
 * @see Bounded
 * @see BiTree
 */
public class BPSpace<O extends Bounded> extends BiTree implements AtomicSet<O>, DataBoreal<O>, PlanarBoreal<O>, Manifold<O>
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
	 * @see BiArboreal
	 */
	public static interface Factory extends BiArboreal.Factory
	{
		@Override
		public default BPNode<?> node(Object... data)
		{
			Plane p = (Plane) data[0];
			return new BPNode<>(Tree(), p);
		}
		
		@Override
		public abstract BPSpace<?> Tree();
	}
	
	/**
	 * A {@code BPSpace.Query} defines spatial queries for a {@code BPSpace}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see PlanarBoreal
	 * @see DataBoreal
	 * @see Manifold
	 * @see Bounded
	 */
	@FunctionalInterface
	public static interface Query<O extends Bounded> extends DataBoreal.Query<O>, PlanarBoreal.Query<O>, Manifold.Query<O>
	{		
		@Override
		public default Iterator<O> at(Point p)
		{
			return new DataIterator<>(Nodes(p));
		}
		
		@Override
		public default Iterator<O> in(HyperCuboid c)
		{
			return new DataIterator<>(Nodes(c));
		}
		
		@Override
		public default Iterator<Pair<O, O>> Pairs()
		{
			return DataBoreal.Query.super.Pairs();
		}
		
		@Override
		public abstract BPSpace<O> Tree();
	}
	

	private int depth;
	private HyperCuboid bnd;
	
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
		BPNode<?> r = null;
		Factory fct = Factory();
		r = fct.node(r);
		
		setRoot(r);
		depth = d;
		bnd = b;
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
		if(!n.Data().contains(obj))
		{
			n.Data().add(obj);
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
			n.Data().add(obj);
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
		if(n.Data().contains(obj))
		{
			n.Data().remove(obj);
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
		
		n.Data().remove(obj);
	}
	
	@Override
	public void clear()
	{
		Root().clear();
	}
	
	
	@Override
	public Bounds Bounds()
	{
		return bnd.Bounds();
	}
	
	@Override
	public Query<O> Query()
	{
		return () -> this;
	}
	
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
		
	@Override
	public BPNode<O> Root()
	{
		return (BPNode<O>) super.Root();
	}
	
	@Override
	public int Count()
	{
		return AtomicSet.super.Count();
	}
}