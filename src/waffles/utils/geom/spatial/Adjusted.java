package waffles.utils.geom.spatial;

import waffles.utils.alg.lin.measure.vector.Vector;
import waffles.utils.geom.spatial.maps.data.Spatial;
import waffles.utils.geom.spatial.owners.Rotatable;

/**
 * An {@code Adjusted} object can be affine transformed in an n-dimensional space.
 *
 * @author Waffles
 * @since Feb 10, 2019
 * @version 1.1
 * 
 * 
 * @see Aligned
 * @see Rotatable
 * @see Spatial
 */
public interface Adjusted extends Aligned, Rotatable, Spatial
{
	/**
	 * Moves the {@code Adjusted} for a given distance.
	 * 
	 * @param i  a vector index
	 * @param d  a distance value
	 */
	public default void moveFor(int i, float d)
	{
		if(ERROR < d)
		{
			Vector v = Spin().Basis(i);
			
			float n = v.norm();
			v = v.times(d / n);
			moveFor(v);
		}
	}
	
	
	@Override
	public abstract Spatial.Mutable Transform();
}