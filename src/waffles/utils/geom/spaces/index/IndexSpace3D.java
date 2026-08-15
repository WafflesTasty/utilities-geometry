package waffles.utils.geom.spaces.index;

import waffles.utils.geom.spaces.Space3D;
import waffles.utils.sets.indexed.MutableIndex;
import waffles.utils.sets.utilities.indexed.coords.Coordination3D;

/**
 * A {@code IndexSpace2D} is a three-dimensional {@code IndexSpace}.
 *
 * @author Waffles
 * @since Aug 15, 2026
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @param <T>  a tile type
 * @see Coordination3D
 * @see IndexSpace
 * @see Space3D
 */
public interface IndexSpace3D<O, T> extends IndexSpace<O, T>, Space3D<O>, Coordination3D
{
	/**
	 * An {@code IndexSpace3D.Mutable} defines a {@code Mutable IndexSpace3D}.
	 *
	 * @author Waffles
	 * @since 14 Feb 2026
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 * @param <T>  a tile type
	 * @see MutableIndex
	 * @see IndexSpace3D
	 */
	public static interface Mutable<O, T> extends IndexSpace3D<O, T>, MutableIndex<T>
	{
		// NOT APPLICABLE
	}
	
	@Override
	public default int Dimension()
	{
		return 3;
	}
}
