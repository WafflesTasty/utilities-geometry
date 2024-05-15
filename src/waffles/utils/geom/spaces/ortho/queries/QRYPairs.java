package waffles.utils.geom.spaces.ortho.queries;

import java.util.Iterator;

import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.spaces.ortho.OrtNode;
import waffles.utils.geom.spaces.ortho.OrtTree;
import waffles.utils.sets.keymaps.Pair;

/**
 * A {@code QRYPairs} queries all pairs of nearby objects within an {@code OrtTree}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 * 
 * 
 * @param <O>  an object type
 * @see Iterator
 * @see Bounded
 * @see Pair
 */
public class QRYPairs<O extends Bounded> implements Iterator<Pair<O, O>>
{	
	/**
	 * A {@code Pairs} object iterates over all pairs in an {@code OrtNode}.
	 * These pairs are formed with keys in the main node, and values
	 * in the main node or relevant children.
	 *
	 * @author Waffles
	 * @since 12 Apr 2024
	 * @version 1.1
	 *
	 * 
	 * @see Iterator
	 */
	public class Pairs implements Iterator<Pair<O, O>>
	{
		private O next;
		private int key;

		private OrtNode<O> node;
		private Iterator<O> vals;
		
		/**
		 * Creates a new {@code Pairs}.
		 * 
		 * @param n  a source node
		 * 
		 * 
		 * @see OrtNode
		 */
		public Pairs(OrtNode<O> n)
		{
			node = n;
			if(0 < node.Count())
			{
				vals = Query();
				next = findNext();
			}
		}
		
		
		Iterator<O> Query()
		{
			O obj = node.Objects().get(key);
			return new QRYCuboid<>(node, key+1, obj);
		}
		
		private O findNext()
		{
			if(vals.hasNext())
			{
				return vals.next();
			}
			
			
			key++;
			if(key == node.Count())
			{
				return null;
			}
			
			vals = Query();
			return findNext();
		}
		
		@Override
		public boolean hasNext()
		{
			return key < node.Count();
		}

		@Override
		public Pair<O, O> next()
		{			
			O obj = node.Objects().get(key);
			Pair<O, O> p = src.createPair(obj, next);
			next = findNext();
			return p;
		}
	}
	
	
	private Pairs pairs;
	private OrtTree<O> src;
	private Pair<O, O> next;
	
	Iterator<OrtNode<O>> nodes;
	
	/**
	 * Creates a new {@code QRYPairs}.
	 * 
	 * @param s  a source tree
	 * 
	 * 
	 * @see OrtTree
	 */
	public QRYPairs(OrtTree<O> s)
	{
		src = s;
		nodes = s.BFSearch().iterator();
		pairs = new Pairs(nodes.next());
		next = findNext();
	}

	
	@Override
	public boolean hasNext()
	{
		return next != null;
	}
	
	@Override
	public Pair<O, O> next()
	{
		Pair<O, O> curr = next;
		next = findNext();
		return curr;
	}

	Pair<O, O> findNext()
	{
		if(pairs.hasNext())
		{
			return pairs.next();
		}
		
		if(nodes.hasNext())
		{
			OrtNode<O> next = nodes.next();
			pairs = new Pairs(next);
			return findNext();
		}
		
		return null;
	}
}