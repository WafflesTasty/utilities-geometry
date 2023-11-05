package waffles.utils.geom.spaces.ortho;

import waffles.utils.algebra.elements.linear.vector.Vector;
import waffles.utils.algebra.elements.linear.vector.Vectors;
import waffles.utils.geom.bounds.Bounded;
import waffles.utils.geom.bounds.Bounds;
import waffles.utils.geom.collidable.axial.AxialShape;
import waffles.utils.geom.collidable.axial.cuboid.HyperCuboid;
import waffles.utils.geom.spatial.data.Axial;
import waffles.utils.geom.spatial.structs.Axis;
import waffles.utils.sets.MutableSet;
import waffles.utils.sets.mutable.AtomicSet;
import waffles.utils.sets.mutable.JHashSet;
import waffles.utils.sets.trees.Nodal;
import waffles.utils.sets.trees.Node;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code OrtNode} defines a single node of an {@code OrtTree}.
 * Each node partitions n-dimensional space in 2^n equal sized child cuboids.
 * The partitioning can be controlled by changing its maximum depth and object count. 
 *
 * @author Waffles
 * @since 31 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see MutableSet
 * @see AxialShape
 * @see Node
 */
public class OrtNode<O extends Bounded> extends Node implements HyperCuboid, MutableSet<O>
{		
	private Axial axis;
	private AtomicSet<O> set;
			
	/**
	 * Creates a new {@code OrtNode}.
	 * 
	 * @param tree  a parent tree
	 * @param a  a node axis
	 * 
	 * 
	 * @see OrtTree
	 * @see Axial
	 */
	public OrtNode(OrtTree<O> tree, Axial a)
	{
		super(tree);
		set = new JHashSet<>();
		axis = a;
	}
	
	/**
	 * Creates a new {@code OrtNode}.
	 * 
	 * @param tree  a parent tree
	 * @param c  a shape center
	 * @param s  a shape size
	 * 
	 * 
	 * @see OrtTree
	 * @see Vector
	 */
	public OrtNode(OrtTree<O> tree, Vector c, Vector s)
	{
		this(tree, new Axis(c, s));
	}
	
	
	/**
	 * Returns the objects in the {@code OrtNode}.
	 * 
	 * @return  an object set
	 * 
	 * 
	 * @see AtomicSet
	 */
	public AtomicSet<O> Objects()
	{
		return set;
	}
	
	/**
	 * Checks if an object is in the {@code OrtNode}.
	 * 
	 * @param obj  an object
	 * @return  {@code true} if the object is containd
	 */
	public boolean contains(O obj)
	{
		return set.contains(obj);
	}
	
	/**
	 * Indexes an object in the {@code OrtNode}.
	 * If one of its children supports the object,
	 * its index is returned. Otherwise, this
	 * method returns -1.
	 * 
	 * @param obj  a target object
	 * @return  a child node index
	 */
	public int index(O obj)
	{
		Bounds nBnd = Bounds();
		Bounds oBnd = obj.Bounds();
		Vector min = oBnd.Minimum();
		Vector max = oBnd.Maximum();
		Vector c = nBnd.Center();
		int dim = Dimension();
		

		int index = 0;
		for(int i = 0; i < dim; i++)
		{
			if(c.get(i) < min.get(i))
			{
				index += Integers.pow(2, i);
				continue;
			}
			
			if(c.get(i) < max.get(i))
			{
				return -1;
			}
		}
		
		return index;
	}
	
	/**
	 * Performs a split on the {@code OrtNode}.
	 */
	public void split()
	{
		int dim = Bounds().Dimension();
		Vector s = Bounds().Size().times(0.5f);		
		Vector c = Bounds().Center();
		
		
		for(int i = 0; i < Integers.pow(2, dim); i++)
		{
			Vector v = Vectors.create(dim);
			for(int j = 0; j < dim; j++)
			{
				float val = c.get(j);
				if(Integers.bitAt(i, j) == 0)
					val -= s.get(j) / 2;
				else
					val += s.get(j) / 2;
				
				
				v.set(val, j);
			}
			
			addChild(Set().createNode(v, s));
		}
	}

	
	@Override
	public Vector Size()
	{
		return axis.Size();
	}
	
	@Override
	public Vector Origin()
	{
		return axis.Origin();
	}
	
	@Override
	public OrtTree<O> Set()
	{
		return (OrtTree<O>) super.Set();
	}
		
	@Override
	public OrtNode<O> Parent()
	{
		return (OrtNode<O>) super.Parent();
	}
	 
	@Override
	public OrtNode<O> Child(int i)
	{
		return (OrtNode<O>) super.Child(i);
	}
	

	@Override
	public boolean isEmpty()
	{
		if(!isLeaf())
		{
			for(Nodal node : Children())
			{
				OrtNode<O> child = (OrtNode<O>) node;
				if(!child.isEmpty())
				{
					return false;
				}
			}
		}
		
		return Count() == 0;
	}
	
	@Override
	public void remove(O obj)
	{
		set.remove(obj);
	}

	@Override
	public void add(O obj)
	{		
		set.add(obj);
	}

	@Override
	public void clear()
	{
		super.clear();
		set.clear();
	}
	
	@Override
	public int Count()
	{
		return set.Count();
	}
}