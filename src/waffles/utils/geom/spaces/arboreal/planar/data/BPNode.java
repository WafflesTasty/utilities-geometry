package waffles.utils.geom.spaces.arboreal.planar.data;

import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNode;
import waffles.utils.geom.spatial.bounds.owners.Bounded;
import waffles.utils.sets.arboreal.data.DataNodal;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code BPNode} defines a single node in a {@code BPSpace}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PlanarNode
 * @see DataNodal
 * @see Bounded
 */
public class BPNode<O extends Bounded> extends PlanarNode implements DataNodal<O>
{
	private JavaList<O> data;
	
	/**
	 * Creates a new {@code BPNode}.
	 * 
	 * @param t  a parent tree
	 * @param p  a splitting plane
	 * 
	 * 
	 * @see BPSpace
	 * @see Plane
	 */
	public BPNode(BPSpace<?> t, Plane p)
	{
		super(t, p);
		data = new JavaList<>();
	}

	
	@Override
	public BPNode<O> Arch()
	{
		return this;
	}
	
	@Override
	public BPNode<O> Parent()
	{
		return (BPNode<O>) super.Parent();
	}
	
	@Override
	public BPNode<O> LChild()
	{
		return (BPNode<O>) super.LChild();
	}
	
	@Override
	public BPNode<O> RChild()
	{
		return (BPNode<O>) super.RChild();
	}
		
	@Override
	public JavaList<O> Data()
	{
		return data;
	}

	@Override
	public void clear()
	{
		super.clear();
		data.clear();
	}
}