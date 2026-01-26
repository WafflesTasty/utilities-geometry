package waffles.utils.geom.spatial.owners.geom;

import waffles.utils.geom.shapes.Geometrical2D;
import waffles.utils.geom.spatial.Adjustable2D;

/**
 * The {@code AffineOriented2D} interface defines a two-dimensional {@code Adjustable Geometrical}.
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