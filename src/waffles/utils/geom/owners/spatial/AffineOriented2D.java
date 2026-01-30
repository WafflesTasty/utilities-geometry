package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical2D;
import waffles.utils.geom.spatial.Adjustable2D;

/**
 * An {@code AffineOriented2D} defines a two-dimensional {@code Adjustable Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see AffineOriented
 * @see Geometrical2D
 * @see Adjustable2D
 */
public interface AffineOriented2D extends AffineOriented, Adjustable2D, Geometrical2D
{
	@Override
	public default int Dimension()
	{
		return 2;
	}
}