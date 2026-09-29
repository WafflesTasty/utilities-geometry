package waffles.utils.geom.spaces.arboreal.lexic;

import waffles.utils.geom.shapes.convex.axial.sphere.HyperSphere;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.nodal.SpatialNodal;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.sets.arboreal.binary.search.BSNode;

/**
 * A {@code BSLexicNode} is a node in a {@code BSLexicSpace}.
 *
 * @author Waffles
 * @since Sep 25, 2026
 * @version 1.1
 *
 *
 * @param <P>  an object type
 * @see SpatialNodal
 * @see HyperSphere
 * @see Positioned
 * @see BSNode
 */
public class BSLexicNode<P extends Positioned> extends BSNode<P> implements HyperSphere, SpatialNodal
{
	/**
	 * Creates a new {@code BSLexicNode}.
	 * 
	 * @param p  a parent space
	 * @param v  an object value
	 * 
	 * 
	 * @see BSLexicSpace
	 */
	public BSLexicNode(BSLexicSpace<P> p, P v)
	{
		super(p, v);
	}

	
	@Override
	public Point Origin()
	{
		return Value().Origin();
	}

	@Override
	public BSLexicNode<P> Parent()
	{
		return (BSLexicNode<P>) super.Parent();
	}
		
	@Override
	public Arrow Scale()
	{
		return Arrow.create(2f, Dimension());
	}
}