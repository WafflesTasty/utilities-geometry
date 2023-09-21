package waffles.utils.geom.spaces.index;

import waffles.utils.geom.bounds.Bounded3D;
import waffles.utils.geom.bounds.Bounds3D;
import waffles.utils.geom.spaces.Space3D;
import waffles.utils.geom.spaces.index.tiles.Tiled3D;

/**
 * A {@code TiledSpace2D} defines a two-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 *
 *
 * @param <T>  a tile type
 * @see TiledSpace
 * @see Bounded3D
 * @see Space3D
 * @see Tiled3D
 */
public interface TiledSpace3D<T extends Tiled3D> extends TiledSpace<T>, Space3D<T>, Bounded3D
{
	/**
	 * Returns the columns of the {@code TiledSpace3D}.
	 * 
	 * @return  a column count
	 */
	public default int Columns()
	{
		return Dimensions()[1];
	}
	
	/**
	 * Returns the aisles of the {@code TiledSpace3D}.
	 * 
	 * @return  an aisle count
	 */
	public default int Aisles()
	{
		return Dimensions()[2];
	}
	
	/**
	 * Returns the rows of the {@code TiledSpace3D}.
	 * 
	 * @return  a row count
	 */
	public default int Rows()
	{
		return Dimensions()[0];
	}
	
	
	@Override
	public default Bounds3D Bounds()
	{
		return (Bounds3D) TiledSpace.super.Bounds();
	}
	
	@Override
	public default int Order()
	{
		return 3;
	}
}