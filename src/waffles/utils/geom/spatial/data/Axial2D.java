package waffles.utils.geom.spatial.data;

import waffles.utils.geom.spatial.data.unary.Positioned2D;
import waffles.utils.geom.spatial.data.unary.Scaled2D;

/**
 * An {@code Axial2D} object describes an axis-aligned position in
 * two-dimensional space. It defines an origin and a size vector.
 *
 * @author Waffles
 * @since 10 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Positioned2D
 * @see Scaled2D
 * @see Axial
 */
public interface Axial2D extends Axial, Positioned2D, Scaled2D
{
	/**
	 * A {@code Mutable Axial2D} can change its own values.
	 *
	 * @author Waffles
	 * @since 16 Oct 2023
	 * @version 1.0
	 * 
	 * 
	 * @see Axial2D
	 * @see Positioned2D
	 * @see Scaled2D
	 * @see Axial
	 */
	public static interface Mutable extends Axial2D, Axial.Mutable, Positioned2D.Mutable, Scaled2D.Mutable
	{
		// NOT APPLICABLE
	}
}