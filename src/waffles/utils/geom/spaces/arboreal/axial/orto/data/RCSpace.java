package waffles.utils.geom.spaces.arboreal.axial.orto.data;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.arboreal.axial.orto.OrtoBoreal;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;
import waffles.utils.sets.arboreal.data.DataBoreal;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.utilities.arboreal.Nodal;
import waffles.utils.sets.utilities.arboreal.iterators.data.DataIterator;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * An {@code RCSpace} implements an {@code OrtoBoreal} as a {@code DataBoreal}.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see DataBoreal
 * @see OrtoBoreal
 * @see AtomicSet
 * @see Manifold
 * @see Bounded
 * @see Tree
 */
public class RCSpace<O extends Bounded> extends Tree implements AtomicSet<O>, DataBoreal<O>, OrtoBoreal<O>, Manifold<O>
{
	/**
	 * Defines the default maximum depth.
	 */
	public static final int MAX_DEPTH = 6;

	/**
	 * An {@code RCSpace.Factory} generates {@code RCNode} objects.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arboreal
	 */
	public static interface Factory extends Arboreal.Factory
	{
		@Override
		public default RCNode<?> node(Object... data)
		{
			Point o = (Point) data[0];
			Arrow s = (Arrow) data[1];

			return new RCNode<>(Tree(), o, s);
		}
		
		@Override
		public abstract RCSpace<?> Tree();
	}
	
	/**
	 * An {@code RCSpace.Query} defines spatial queries for a {@code RCSpace}.
	 *
	 * @author Waffles
	 * @since May 13, 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @see DataBoreal
	 * @see OrtoBoreal
	 * @see Manifold
	 * @see Bounded
	 */
	public static interface Query<O extends Bounded> extends DataBoreal.Query<O>, OrtoBoreal.Query<O>, Manifold.Query<O>
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
		public abstract RCSpace<O> Tree();
	}
	

	private int depth;
	
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
		RCNode<?> r = null;
		Factory fct = Factory();
		Point o = b.Origin();
		Arrow s = b.Scale();
		r = fct.node(o, s);
		
		setRoot(r);
		depth = d;
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
		this(HyperCuboid.create(o, s), d);
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
		RCNode<O> n = Root();
		HyperCuboid spc =   n.Bounds().Box();
		HyperCuboid bnd = obj.Bounds().Box();
		
		if(!spc.contains(bnd))
		{
			n.Data().add(obj);
			return;
		}

		while(true)
		{
			int d = n.Depth();
			int i = n.index(obj);
			if(i == -1 || d == depth)
			{
				n.Data().add(obj);
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
		
		RCNode<O> n = Root();
		if(!spc.contains(bnd))
		{
			n.Data().remove(obj);
			return;
		}

		while(true)
		{
			int i = n.index(obj);
			if(i == -1)
			{
				n.Data().remove(obj);
				while(n.isEmpty())
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
		Root().clear();
	}

	
	@Override
	public Bounds Bounds()
	{
		return Root().Bounds();
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
	public RCNode<O> Root()
	{
		return (RCNode<O>) super.Root();
	}
	
	@Override
	public int Count()
	{
		int c = 0;
		for(Nodal node : BFSearch())
		{
			RCNode<O> n = (RCNode<O>) node;
			c += n.Data().Count();
		}
		
		return c;
	}
}