package waffles.utils.geom.spaces;

/**
 * A {@code Manifold3D} defines a three-dimensional {@code Manifold}.
 *
 * @author Waffles
 * @since 22 Jul 2020
 * @version 1.0
 *
 *
 * @param <O>  an object type
 * @see Manifold
 * @see Space3D
 */
public interface Manifold3D<O> extends Manifold<O>, Space3D<O>
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
}