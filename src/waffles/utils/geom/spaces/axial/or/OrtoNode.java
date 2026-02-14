package waffles.utils.geom.spaces.axial.or;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spatial.maps.data.structs.Axis;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.utilities.rooted.Node;

/**
 * An {@code OrtoNode} defines a node in an {@code OrtoTree}.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 * 
 * @see OrtoNodal
 * @see Node
 */
public class OrtoNode extends Node implements OrtoNodal
{
	private Axis axis;
	
	/**
	 * Creates a new {@code OrtoNode}.
	 * 
	 * @param o  an origin point
	 * @param s  a scale arrow
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public OrtoNode(Point o, Arrow s)
	{
		this(new Axis(o, s));
	}
	
	/**
	 * Creates a new {@code OrtoNode}.
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
	public OrtoNode(Arboreal r, Point o, Arrow s)
	{
		this(r, new Axis(o, s));
	}

	/**
	 * Creates a new {@code OrtoNode}.
	 * 
	 * @param r  an arboreal set
	 * @param a  a node axis
	 * 
	 * 
	 * @see Arboreal
	 * @see Axis
	 */
	public OrtoNode(Arboreal r, Axis a)
	{
		super(r);
		axis = a;
	}
	
	/**
	 * Creates a new {@code OrtoNode}.
	 * 
	 * @param a  a node axis
	 * 
	 * 
	 * @see Axis
	 */
	public OrtoNode(Axis a)
	{
		axis = a;
	}

	
	@Override
	public OrtoNode Arch()
	{
		return this;
	}
		
	@Override
	public OrtoNode Child(int i)
	{
		return (OrtoNode) super.Child(i);
	}

	@Override
	public OrtoNode Parent()
	{
		return (OrtoNode) super.Parent();
	}

	
	@Override
	public Point Origin()
	{
		return axis.Origin();
	}
	
	@Override
	public Arrow Scale()
	{
		return axis.Scale();
	}
}