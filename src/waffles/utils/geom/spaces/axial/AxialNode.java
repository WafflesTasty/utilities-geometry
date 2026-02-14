package waffles.utils.geom.spaces.axial;

import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.rooted.Node;

/**
 * An {@code AxialNode} defines an {@code AxialSet} as a {@code Node}.
 * These nodes are used in various tree-based spatial indices.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 * 
 * 
 * @see AxialNodal
 * @see Node
 */
public abstract class AxialNode extends Node implements AxialNodal
{	
	/**
	 * Creates a new {@code AxialNode}.
	 */
	public AxialNode()
	{
		super();
	}
	
	/**
	 * Creates a new {@code AxialNode}.
	 * 
	 * @param r  an arboreal set
	 * 
	 * 
	 * @see Arboreal
	 */
	public AxialNode(Arboreal r)
	{
		super(r);
	}
	
		
	@Override
	public AxialNode Child(int i)
	{
		return (AxialNode) super.Child(i);
	}

	@Override
	public AxialNode Parent()
	{
		return (AxialNode) super.Parent();
	}

	@Override
	public AxialNode Arch()
	{
		return this;
	}
}