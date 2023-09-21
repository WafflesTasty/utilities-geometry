package waffles.utils.geom.spaces.index.nodes;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.spaces.index.nodes.maps.NodeToWorld;
import waffles.utils.geom.spaces.index.nodes.maps.WorldToNode;

/**
 * An {@code IndexTransform} defines a global map for an {@code IndexNode}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.0
 * 
 * 
 * @see GlobalMap
 */
public class IndexTransform implements GlobalMap
{
	private NodeToWorld ntw;
	private WorldToNode wtn;
			
	/**
	 * Creates a new {@code IndexTransform}.
	 * 
	 * @param s  a src nodal
	 * 
	 * 
	 * @see IndexNode
	 */
	public IndexTransform(IndexNode s)
	{
		ntw = new NodeToWorld(s);
		wtn = new WorldToNode(s);
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