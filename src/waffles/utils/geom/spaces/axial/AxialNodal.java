package waffles.utils.geom.spaces.axial;

import waffles.utils.geom.shapes.convex.axial.AxialSet;
import waffles.utils.sets.utilities.rooted.Nodal;

/**
 * An {@code AxialNodal} defines an {@code AxialSet} as a {@code Nodal}.
 *
 * @author Waffles
 * @since 11 Feb 2026
 * @version 1.1
 *
 * 
 * @see AxialSet
 * @see Nodal
 */
public interface AxialNodal extends AxialSet, Nodal
{
	@Override
	public abstract AxialNode Arch();
}
