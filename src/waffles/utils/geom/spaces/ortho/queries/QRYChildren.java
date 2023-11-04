package waffles.utils.geom.spaces.ortho.queries;

import java.util.Iterator;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spaces.ortho.OrtNode;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.tools.primitives.Integers;

/**
 * A {@code QRYChildren} queries a cuboid on the children of an {@code OrtNode}.
 *
 * @author Waffles
 * @since 19 Sep 2023
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see Iterator
 * @see OrtNode
 * @see Bounded
 */
public class QRYChildren<O extends Bounded> implements Iterator<OrtNode<O>>
{
	private int curr;
	private OrtNode<O> src;
	private List<Integer> list;
	
	/**
	 * Creates a new {@code QRYChildren}.
	 * 
	 * @param s  a source node
	 * @param t  a target cuboid
	 * 
	 * 
	 * @see HyperCuboid
	 * @see OrtNode
	 */
	public QRYChildren(OrtNode<O> s,  HyperCuboid t)
	{
		src = s;
		findIndices();
	}
	
	/**
	 * Creates a new {@code QRYChildren}.
	 * 
	 * @param s  a source node
	 * 
	 * 
	 * @see OrtNode
	 */
	public QRYChildren(OrtNode<O> s)
	{
		src = s;
		allIndices();
	}
	
	
	private void findIndices()
	{
		list = new List<>();
		
		Bounds bnd = src.Bounds();
		Vector min = bnd.Minimum();
		Vector max = bnd.Maximum();
		int dim = bnd.Dimension();
		Vector c = bnd.Center();
		
		for(int i = 0; i < dim; i++)
		{
			int count = list.Count();
			if(c.get(i) < min.get(i))
			{
				for(int j = 0; j < count; j++)
				{
					int index = list.get(j);
					index += Integers.pow(2, i);
					list.put(index, j);
				}
				
				continue;
			}
			
			if(c.get(i) < max.get(i))
			{
				for(int j = 0; j < count; j++)
				{
					int index = list.get(j);
					index += Integers.pow(2, i);
					list.add(index);
				}
				
				continue;
			}
		}
	}
	
	private void allIndices()
	{
		list = new List<>();
		
		int dim = src.Dimension();
		for(int i = 0; i < Integers.pow(2, dim); i++)
		{
			list.add(i);
		}
	}
	
	@Override
	public boolean hasNext()
	{
		return curr < list.Count();
	}

	@Override
	public OrtNode<O> next()
	{
		int next = list.get(curr++);
		return src.Child(next);
	}
}