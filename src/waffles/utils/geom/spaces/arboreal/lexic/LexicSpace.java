package waffles.utils.geom.spaces.arboreal.lexic;

import java.util.Comparator;

import waffles.utils.geom.spaces.arboreal.ArborealSpace;
import waffles.utils.geom.spatial.maps.data.unary.Positioned;
import waffles.utils.lang.utilities.enums.Sign;
import waffles.utils.lang.utilities.patterns.lexic.LexicOrder;
import waffles.utils.sets.arboreal.binary.search.IOTree;

/**
 * A {@code LexicSpace} induces a lexicographic order on {@code Positioned} objects.
 * The objects are stored in a {@code NodalSpace} sorted by its origin coordinates,
 * with the coordinate order being defined by its {@link #Query()}.
 *
 * @author Waffles
 * @since Sep 25, 2026
 * @version 1.1
 *
 *
 * @param <P>  an object type
 * @see ArborealSpace
 * @see Comparator
 * @see Positioned
 */
public interface LexicSpace<P extends Positioned> extends ArborealSpace<P>, Comparator<P>
{
	/**
	 * A {@code Query} defines a {@code LexicOrder} for a {@code LexicSpace}.
	 *
	 * @author Waffles
	 * @since Sep 25, 2026
	 * @version 1.1
	 *
	 * 
	 * @param <P>  an object type
	 * @see LexicOrder
	 * @see Positioned
	 * @see IOTree
	 */
	public static interface Query<P extends Positioned> extends ArborealSpace.Query<P>, LexicOrder<Positioned>
	{		
		@Override
		public default int compare(Positioned o1, Positioned o2, int ord)
		{
			float v1 = o1.Origin().aff(ord);
			float v2 = o2.Origin().aff(ord);
			
			Sign s = Sign.of(v1 - v2);
			return s.Value();
		}

		@Override
		public default int length(Positioned p)
		{
			return p.Dimension();
		}
	}


	@Override
	public default int compare(P o1, P o2)
	{
		return Query().compare(o1, o2);
	}

	@Override
	public abstract Query<P> Query();
}