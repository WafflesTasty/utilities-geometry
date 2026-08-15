package waffles.utils.geom.spaces.index;

import waffles.utils.geom.spaces.Space2D;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.utilities.indexed.coords.Coordination2D;

/**
 * A {@code IndexSpace2D} is a two-dimensional {@code IndexSpace}.
 *
 * @author Waffles
 * @since Aug 15, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <T>  a tile type
 * @see Coordination2D
 * @see IndexSpace
 * @see Space2D
 */
public interface IndexSpace2D<O, T> extends IndexSpace<O, T>, Space2D<O>, Coordination2D
{
	/**
	 * An {@code IndexSpace2D.Mutable} defines a {@code Mutable IndexSpace2D}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <T>  a tile type
	 * @see MutableIndex
	 * @see IndexSpace2D
	 */
	public static interface Mutable<O, T> extends IndexSpace2D<O, T>, MutableIndex<T>
	{
		// NOT APPLICABLE
	}
	
	@Override
	public default int Dimension()
	{
		return 2;
	}
}
