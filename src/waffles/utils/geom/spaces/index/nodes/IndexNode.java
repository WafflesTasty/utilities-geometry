package waffles.utils.geom.spaces.index.nodes;

import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.Geometry;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.spaces.index.IndexSpace;
import waffles.utils.geom.utilities.Geometries;

/**
 * A {@code IndexNode} defines a geometrical node in a {@code TiledSpace}.
 *
 * @author Waffles
 * @since 21 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometrical
 */
public interface IndexNode extends Geometrical
{
	/**
	 * Returns the space of the {@code IndexNode}.
	 * 
	 * @return  a node space
	 * 
	 * 
	 * @see IndexSpace
	 */
	public abstract IndexSpace<?> Space();
	
	/**
	 * Returns the minimum of the {@code IndexNode}.
	 * 
	 * @return  an index minimum
	 */
	public abstract int[] Minimum();

	/**
	 * Returns the maximum of the {@code IndexNode}.
	 * 
	 * @return  an index maximum
	 */
	public abstract int[] Maximum();
	
	
	@Override
	public default Geometry Shape()
	{
		return Geometries.cube(Dimension());
	}
	
	@Override
	public default GlobalMap Transform()
	{
		return new IndexTransform(this);
	}
				
	@Override
	public default int Dimension()
	{
		return Minimum().length;
	}
}