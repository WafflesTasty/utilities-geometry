package waffles.utils.geom.spaces.binary.kd;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.binary.kd.maps.NodeToWorld;
import waffles.utils.geom.spaces.binary.kd.maps.WorldToNode;
import waffles.utils.geom.spatial.maps.GlobalMap;

/**
 * A {@code KDTransform} defines a global map for a {@code KDNode}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class KDTransform implements GlobalMap
{
	private NodeToWorld ntw;
	private WorldToNode wtn;
			
	/**
	 * Creates a new {@code IndexTransform}.
	 * 
	 * @param n  a src node
	 * 
	 * 
	 * @see KDNode
	 */
	public KDTransform(KDNode<?> n)
	{
		ntw = new NodeToWorld(n);
		wtn = new WorldToNode(n);
	}
	
	
	@Override
	public LazyMatrix UTW()
	{
		return ntw;
	}

	@Override
	public LazyMatrix WTU()
	{
		return wtn;
	}
}