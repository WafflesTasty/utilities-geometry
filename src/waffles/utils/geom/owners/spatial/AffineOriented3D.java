package waffles.utils.geom.owners.spatial;

import waffles.utils.geom.owners.Geometrical3D;
import waffles.utils.geom.spatial.Adjustable3D;

/**
 * An {@code AffineOriented3D} defines a three-dimensional {@code Adjustable Geometrical}.
 *
 * @author Waffles
 * @since Feb 27, 2018
 * @version 1.1
 *
 *
 * @see AffineOriented
 * @see Geometrical3D
 * @see Adjustable3D
 */
public interface AffineOriented3D extends AffineOriented, Adjustable3D, Geometrical3D
{
	@Override
	public default int Dimension()
	{
		return 3;
	}
}