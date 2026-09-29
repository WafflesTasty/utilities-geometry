package waffles.utils.geom.spaces.arboreal.lexic;

import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.lang.utilities.patterns.lexic.LexicOrder;
import waffles.utils.sets.arboreal.binary.search.BSTree;
import waffles.utils.sets.arboreal.binary.search.IOTree;

/**
 * A {@code BSLexicSpace} induces a lexicographic order on {@code Positioned} objects.
 * The objects are stored in a {@code BSTree} sorted by its origin coordinates, with
 * the coordinate order being defined by its {@link #Query()}.
 *
 * @author Waffles
 * @since Sep 25, 2026
 * @version 1.1
 *
 *
 * @param <P>  a positioned type
 * @see LexicSpace
 * @see Positioned
 * @see BSTree
 */
public class BSLexicSpace<P extends Positioned> extends BSTree<P> implements LexicSpace<P>
{
	/**
	 * A {@code Query} defines a {@code LexicOrder} for a {@code BSLexicSpace}.
	 *
	 * @author Waffles
	 * @since Sep 25, 2026
	 * @version 1.1
	 *
	 * 
	 * @see LexicOrder
	 * @see IOTree
	 */
	public class Query implements IOTree.Query<P>, LexicSpace.Query<P>
	{
		@Override
		public BSLexicSpace<P> Tree()
		{
			return BSLexicSpace.this;
		}
	}
	
	/**
	 * A {@code BSLexicSpace.Factory} generates {@code BSLexicNode} objects.
	 *
	 * @author Waffles
	 * @since Sep 25, 2026
	 * @version 1.1
	 *
	 * 
	 * @see BSTree
	 */
	public class Factory implements BSTree.Factory<P>
	{
		@Override
		public BSLexicNode<P> node(Object... data)
		{
			return new BSLexicNode<>(Tree(), (P) data[0]);
		}

		@Override
		public BSLexicSpace<P> Tree()
		{
			return BSLexicSpace.this;
		}
	}

	
	@Override
	public int compare(P o1, P o2)
	{
		return LexicSpace.super.compare(o1, o2);
	}
		
	@Override
	public BSLexicNode<P> Root()
	{
		return (BSLexicNode<P>) super.Root();
	}
	
	@Override
	public Factory Factory()
	{
		return new Factory();
	}
	
	@Override
	public Query Query()
	{
		return new Query();
	}
}