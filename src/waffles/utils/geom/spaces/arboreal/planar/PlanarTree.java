package waffles.utils.geom.spaces.arboreal.planar;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.linear.halved.Plane;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.NodalSpace;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.arboreal.binary.BiArboreal;
import waffles.utils.sets.arboreal.binary.BiTree;

/**
 * A {@code PlanarTree} implements a {@code PlanarBoreal} as a binary {@code NodalSpace}.
 *
 * @author Waffles
 * @since 14 Feb 2026
 * @version 1.1
 *
 * 
 * @see PlanarBoreal
 * @see PlanarNode
 * @see NodalSpace
 * @see BiTree
 */
public class PlanarTree extends BiTree implements PlanarBoreal<PlanarNode>, NodalSpace<PlanarNode>
{	
	/**
	 * A {@code PlanarTree.Query} defines spatial queries for a {@code PlanarTree}.
	 *
	 * @author Waffles
	 * @since May 12, 2026
	 * @version 1.1
	 *
	 * 
	 * @see NodalSpace
	 * @see PlanarBoreal
	 * @see PlanarNode
	 */
	public static interface Query extends PlanarBoreal.Query<PlanarNode>, NodalSpace.Query<PlanarNode>
	{
		@Override
		public abstract PlanarTree Tree();
		
		@Override
		public default Iterator<PlanarNode> at(Point p)
		{
			return Nodes(p);
		}
		
		@Override
		public default Iterator<PlanarNode> in(HyperCuboid c)
		{
			return Nodes(c);
		}
	}
	
	/**
	 * A {@code PlanarTree.Factory} generates {@code PlanarNode} objects.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see BiArboreal
	 */
	public static interface Factory extends BiArboreal.Factory
	{
		@Override
		public default PlanarNode node(Object... data)
		{
			Plane p = (Plane) data[0];
			return new PlanarNode(Tree(), p);
		}
		
		@Override
		public abstract PlanarTree Tree();
	}
	
	
	private HyperCuboid bnd;
	
	/**
	 * Creates a new {@code PlanarTree}.
	 * 
	 * @param b  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public PlanarTree(HyperCuboid b)
	{
		PlanarNode r = null;
		Factory fct = Factory();
		r = fct.node(r);
		bnd = b;
	}
	
	/**
	 * Creates a new {@code PlanarTree}.
	 * 
	 * @param o  a tree origin
	 * @param s  a tree scale
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public PlanarTree(Point o, Arrow s)
	{
		this(HyperCuboid.create(o, s));
	}

	
	@Override
	public PlanarNode Root()
	{
		return (PlanarNode) super.Root();
	}
		
	@Override
	public Factory Factory()
	{
		return () -> this;
	}
		
	@Override
	public Bounds Bounds()
	{
		return bnd.Bounds();
	}
	
	@Override
	public Query Query()
	{
		return () -> this;
	}
}