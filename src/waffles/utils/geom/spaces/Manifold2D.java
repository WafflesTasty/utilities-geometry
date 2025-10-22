package waffles.utils.geom.spaces;

/**
 * A {@code Manifold2D} defines a two-dimensional {@code Manifold}.
 *
 * @author Waffles
 * @since 22 Jul 2020
 * @version 1.0
 * 
 * 
 * @param <O>  an object type
 * @see Manifold
 * @see Space2D
 */
public interface Manifold2D<O> extends Manifold<O>, Space2D<O>
{
	@Override
	public default int Dimension()
	{
		return 2;
	}
}