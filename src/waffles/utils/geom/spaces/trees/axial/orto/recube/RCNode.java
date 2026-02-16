package waffles.utils.geom.spaces.trees.axial.orto.recube;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.trees.array.ArrayNodal;
import waffles.utils.geom.spaces.trees.axial.orto.OrtoNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.sets.countable.AtomicSet;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * An {@code RCNode} defines a single node in an {@code RCTree}.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see ArrayNodal
 * @see AtomicSet
 * @see OrtoNode
 * @see Bounded
 */
public class RCNode<O extends Bounded> extends OrtoNode implements ArrayNodal<O>, AtomicSet<O>
{	
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
	

	@Override
	public RCTree<O> Set()
	{
		return (RCTree<O>) super.Set();
	}
	
	@Override
	public RCNode<O> Arch()
	{
		return this;
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
	public JavaList<O> Data()
	{
		return data;
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