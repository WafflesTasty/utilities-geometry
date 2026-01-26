package waffles.utils.geom.shapes.linear;

import waffles.utils.alg.lin.measure.vector.Vector;

/**
 * An {@code LSpace} provides a basic line-based structure.
 *
 * @author Waffles
 * @since 11 Dec 2025
 * @version 1.1
 *
 * 
 * @see VSpace
 */
public interface LSpace extends VSpace.Direct
{
	@Override
	public default Vector Direction()
	{
		return (Vector) Factory().Matrix();
	}
}