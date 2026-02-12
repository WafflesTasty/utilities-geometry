package waffles.utils.geom.spaces.axial;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
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
	private Axis axis;
	
	/**
	 * Creates a new {@code AxialNode}.
	 * 
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public AxialNode(Point o, Arrow s)
	{
		this(new Axis(o, s));
	}
	
	/**
	 * Creates a new {@code AxialNode}.
	 * 
	 * @param r  an arboreal set
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arboreal
	 * @see Arrow
	 * @see Point
	 */
	public AxialNode(Arboreal r, Point o, Arrow s)
	{
		this(r, new Axis(o, s));
	}

	/**
	 * Creates a new {@code AxialNode}.
	 * 
	 * @param r  an arboreal set
	 * @param a  a node axis
	 * 
	 * 
	 * @see Arboreal
	 * @see Axis
	 */
	public AxialNode(Arboreal r, Axis a)
	{
		super(r);
		axis = a;
	}
	
	/**
	 * Creates a new {@code AxialNode}.
	 * 
	 * @param a  a node axis
	 * 
	 * 
	 * @see Axis
	 */
	public AxialNode(Axis a)
	{
		axis = a;
	}

	
	
	
	
	@Override
	public Point Origin()
	{
		return axis.Origin();
	}
	
	@Override
	public AxialNode Arch()
	{
		return this;
	}

	@Override
	public Arrow Scale()
	{
		return axis.Scale();
	}
}