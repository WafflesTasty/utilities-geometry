package waffles.utils.geom.spaces.trees.queries;

import java.util.Iterator;

import waffles.utils.geom.spaces.trees.array.ArrayNodal;
import waffles.utils.sets.indexed.array.ArraySet;
import waffles.utils.sets.utilities.indexed.iterators.arrays.ArrayValues;
import waffles.utils.sets.utilities.keymaps.Pair;
import waffles.utils.sets.utilities.rooted.iterators.BreadthFirst;

/**
 * A {@code QRYPairs} queries manifold pairs in an {@code ArrayNodal}.
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.1
 *
 * 
 * @param <O>  an object type
 * @param <N>  a nodal type
 * @see ArrayNodal
 * @see Iterator
 * @see Pair
 */
public class QRYPairs<O, N extends ArrayNodal<O>> implements Iterator<Pair<O, O>>
{
	private O key;
	private N curr;

	private Iterator<O> objects;
	private Iterator<N> nodes;
	private Pair<O, O> next;
	
	/**
	 * Creates a new {@code QRYPairs}.
	 * 
	 * @param n  a spatial nodal
	 * @param idx  a start index
	 */
	public QRYPairs(N n, int idx)
	{
		ArraySet<?, O> data = n.Data();
		
		int min = idx + 1;
		int max = data.Count() - 1;
		key = data.get(idx);
		
		objects = new ArrayValues<>(data, min, max);
		nodes = new BreadthFirst<>(n);
		
		curr = nodes.next();
		next = findNext();
	}
	
	
	Pair<O, O> findNext()
	{
		if(objects.hasNext())
		{
			O val = objects.next();
			return new Pair.Base<>(key, val);
		}
		
		if(nodes.hasNext())
		{
			curr = nodes.next();
			ArraySet<?, O> data = curr.Data();
			objects = new ArrayValues<>(data);
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
	public Pair<O, O> next()
	{
		Pair<O, O> curr = next;
		next = findNext();
		return curr;
	}
}