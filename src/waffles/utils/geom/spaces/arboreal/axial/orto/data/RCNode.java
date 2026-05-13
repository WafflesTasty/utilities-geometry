package waffles.utils.geom.spaces.arboreal.axial.orto.data;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.axial.orto.OrtoNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.sets.arboreal.data.DataNodal;
import waffles.utils.sets.countable.wrapper.JavaList;
import waffles.utils.sets.utilities.arboreal.Nodal;

/**
 * An {@code RCNode} defines a single node in an {@code RCSpace}.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see DataNodal
 * @see OrtoNode
 * @see Bounded
 */
public class RCNode<O extends Bounded> extends OrtoNode implements DataNodal<O>
{	
	private JavaList<O> data;
	
	/**
	 * Creates a new {@code RCNode}.
	 * 
	 * @param p  a parent tree
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see RCSpace
	 * @see Arrow
	 * @see Point
	 */
	public RCNode(RCSpace<?> p, Point o, Arrow s)
	{
		super(p, new Axis(o, s));
		data = new JavaList<>();
	}
	
	/**
	 * Checks the contents {@code RCNode}.
	 * 
	 * @return  {@code true} if empty
	 */
	public boolean isEmpty()
	{
		if(!Data().isEmpty())
		{
			return false;
		}
		
		for(Nodal c : Children())
		{
			if(!((RCNode<O>) c).isEmpty())
			{
				return false;
			}
		}
		
		return true;
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
	public RCSpace<O> Set()
	{
		return (RCSpace<O>) super.Set();
	}
	
	@Override
	public void clear()
	{
		super.clear();
		data.clear();
	}
}