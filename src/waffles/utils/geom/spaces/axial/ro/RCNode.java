package waffles.utils.geom.spaces.axial.ro;

import java.util.Iterator;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.axial.or.OrtoNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.countable.wrapper.JavaList;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.rooted.Nodal;
import waffles.utils.sets.utilities.rooted.iterators.BreadthFirst;

/**
 * An {@code RCNode} defines a single node in an {@code RCTree}.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see AtomicSet
 * @see OrtoNode
 * @see Bounded
 */
public class RCNode<O extends Bounded> extends OrtoNode implements AtomicSet<O>
{
	/**
	 * A {@code Pairs} iterator forms object pairs in an {@code RCNode}.
	 *
	 * @author Waffles
	 * @since 11 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <P>  a pair type
	 * @see Iterator
	 * @see Pair
	 */
	public class Pairs<P extends Pair<?, ?>> implements Iterator<P>
	{
		private O key;
		private Pair<?, ?> next;
		private Iterator<RCNode<O>> nodes;
		private Iterator<O> objects;
		
		/**
		 * Creates new {@code Pairs}.
		 * 
		 * @param idx  a start index
		 */
		public Pairs(int idx)
		{
			int min = idx + 1;
			int max = Count() - 1;
			key = data.get(idx);
			
			objects = new ArrayValues<>(data, min, max);
			nodes = new BreadthFirst<>(RCNode.this);
			nodes.next(); next = findNext();
		}
		
		
		Pair<?, ?> findNext()
		{
			if(objects.hasNext())
			{
				O val = objects.next();
				RCTree.Factory fct = Set().Factory();
				return fct.pair(key, val);
			}
			
			if(nodes.hasNext())
			{
				RCNode<O> n = nodes.next();
				objects = n.iterator();
				return findNext();
			}

			return null;
		}

		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public P next()
		{
			P curr = (P) next;
			next = findNext();
			return curr;
		}
	}
	
	
	private JavaList<O> data;
	
	/**
	 * Creates a new {@code RCNode}.
	 * 
	 * @param r  a cuboid tree
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see RCTree
	 * @see Arrow
	 * @see Point
	 */
	public RCNode(RCTree<O> r, Point o, Arrow s)
	{
		super(r, new Axis(o, s));
		data = new JavaList<>();
	}

	/**
	 * Iterates over object pairs in the {@code RCNode}.
	 * 
	 * @param <P>  a pair type
	 * @param idx  a start index
	 * @return  a pair iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Pair
	 */
	public <P extends Pair<O, O>> Iterator<P> Pairs(int idx)
	{
		return new Pairs<>(idx);
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
			RCNode<O> n = (RCNode<O>) c;
			if(n.hasData())
			{
				return true;
			}
		}
		
		return !isEmpty();
	}
	
	
	@Override
	public RCTree<O> Set()
	{
		return (RCTree<O>) super.Set();
	}
		
	@Override
	public RCNode<O> Parent()
	{
		return (RCNode<O>) super.Parent();
	}
	 
	@Override
	public RCNode<O> Child(int i)
	{
		return (RCNode<O>) super.Child(i);
	}
	
	@Override
	public Iterator<O> iterator()
	{
		return data.iterator();
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