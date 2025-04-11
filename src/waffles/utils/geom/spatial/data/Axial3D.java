package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Positioned3D;
import waffles.utils.geom.spatial.data.unary.Scaled3D;

/**
 * An {@code Axial3D} object describes an axis-aligned position in
 * three-dimensional space. It defines an origin and a size vector.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Positioned3D
 * @see Scaled3D
 * @see Axial
 */
public interface Axial3D extends Axial, Positioned3D, Scaled3D
{
	/**
	 * A {@code Mutable Axial3D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Axial3D
	 * @see Positioned3D
	 * @see Scaled3D
	 * @see Axial
	 */
	public static interface Mutable extends Axial3D, Axial.Mutable, Positioned3D.Mutable, Scaled3D.Mutable
	{
		// NOT APPLICABLE
	}
}