package waffles.utils.geom.spaces.arboreal.axial.orto;

import java.util.Iterator;

import waffles.utils.geom.shapes.convex.axial.cube.HyperCuboid;
import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.geom.spaces.arboreal.NodalSpace;
import waffles.utils.geom.spaces.arboreal.planar.PlanarBoreal;
import waffles.utils.geom.spaces.arboreal.planar.PlanarNode;
import waffles.utils.geom.spatial.bounds.Bounds;
import waffles.utils.sets.arboreal.Tree;
import waffles.utils.sets.arboreal.binary.BiTree;

/**
 * An {@code OrtoTree} implements an {@code OrtoBoreal} as a {@code NodalSpace}.
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
public class OrtoTree extends Tree implements OrtoBoreal<OrtoNode>, NodalSpace<OrtoNode>
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
	public static interface Query extends OrtoBoreal.Query<OrtoNode>, NodalSpace.Query<OrtoNode>
	{		
		@Override
		public default Iterator<OrtoNode> at(Point p)
		{
			return Nodes(p);
		}
		
		@Override
		public default Iterator<OrtoNode> in(HyperCuboid c)
		{
			return Nodes(c);
		}
		
		@Override
		public abstract OrtoTree Tree();
	}
	
	/**
	 * An {@code OrtoTree.Factory} generates {@code OrtoNode} objects.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 * 
	 * @see Tree
	 */
	public static interface Factory extends Tree.Factory
	{
		@Override
		public default OrtoNode node(Object... data)
		{
			Point o = (Point) data[0];
			Arrow s = (Arrow) data[1];

			return new OrtoNode(Tree(), o, s);
		}
		
		@Override
		public abstract OrtoTree Tree();
	}
	
	
	private HyperCuboid bnd;
	
	/**
	 * Creates a new {@code OrtoTree}.
	 * 
	 * @param b  a bounding box
	 * 
	 * 
	 * @see HyperCuboid
	 */
	public OrtoTree(HyperCuboid b)
	{
		OrtoNode r = null;
		Factory fct = Factory();
		r = fct.node(r);
		bnd = b;
	}
	
	/**
	 * Creates a new {@code OrtoTree}.
	 * 
	 * @param o  a tree origin
	 * @param s  a tree scale
	 * 
	 * 
	 * @see Arrow
	 * @see Point
	 */
	public OrtoTree(Point o, Arrow s)
	{
		this(HyperCuboid.create(o, s));
	}

	
	@Override
	public OrtoNode Root()
	{
		return (OrtoNode) super.Root();
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