package waffles.utils.geom.spaces.axial.ro;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.Manifold;
import waffles.utils.geom.spaces.axial.ro.queries.QRYNodes;
import waffles.utils.geom.spaces.axial.ro.queries.QRYPairs;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.keymaps.Pair;

/**
 * An {@code RCSpace} defines a {@code Manifold} based on an {@code RCTree}.
 *
 * @author Waffles
 * @since 12 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see Arboreal
 * @see Manifold
 * @see Bounded
 */
public class RCSpace<O extends Bounded> implements Arboreal, Bounded, Manifold<O>
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
	 * @param o  a tree origin
	 * @param s  a tree scale
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public RCSpace(Point o, Arrow s)
	{
		this(o, s, MAX_DEPTH);
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
	 * Returns the {@code RCTree}.
	 * 
	 * @return  a cuboid tree
	 * 
	 * 
	 * @see RCTree
	 */
	public RCTree<O> Tree()
	{
		return tree;
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
	public Iterable<O> query(HyperCuboid c)
	{
		if(!Root().intersects(c))
			return Root();
		else
		{
			return () -> new QRYNodes<>(Tree().query(c));
		}
	}

	@Override
	public Iterable<O> query(Point p)
	{
		if(!Root().contains(p))
			return Root();
		else
		{
			return () -> new QRYNodes<>(Tree().query(p));
		}
	}

	@Override
	public Iterator<O> iterator()
	{
		return new QRYNodes<>(Tree().DFSearch());
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
		return Manifold.super.Count();
	}
}