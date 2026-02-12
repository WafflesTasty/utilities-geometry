package waffles.utils.geom.spaces.axial.orto;

import waffles.utils.geom.shapes.points.Arrow;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.sets.arboreal.Arboreal;
import waffles.utils.sets.arboreal.Tree;

/**
 * An {@code OrtoTree} implements an {@code OrtoBoreal} tree structure.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 *
 * @param <N>  a node type
 * @see OrtoBoreal
 * @see OrtoNode
 * @see Tree
 */
public class OrtoTree<N extends OrtoNode> extends Tree implements OrtoBoreal<N>
{
	/**
	 * An {@code OrtoTree.Factory} generates {@code OrtoNode} objects.
	 *
	 * @author Waffles
	 * @since 25 Jan 2026
	 * @version 1.1
	 *
	 * 
	 * @see Arboreal
	 */
	public static interface Factory extends Arboreal.Factory
	{			
		@Override
		public default OrtoNode node(Object... data)
		{
			Point o = (Point) data[0];
			Arrow s = (Arrow) data[1];

			return new OrtoNode(Tree(), o, s);
		}
		
		@Override
		public abstract OrtoTree<?> Tree();
	}
	

	@Override
	public Factory Factory()
	{
		return () -> this;
	}
	
	@Override
	public N Root()
	{
		return (N) super.Root();
	}
}