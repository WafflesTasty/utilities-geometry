package waffles.utils.geom.spaces.index;

import waffles.utils.geom.bounds.Bounded2D;
import waffles.utils.geom.bounds.Bounds2D;
import waffles.utils.geom.spaces.Space2D;
import waffles.utils.geom.spaces.index.tiles.Tiled2D;

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
 * @see Bounded2D
 * @see Space2D
 * @see Tiled2D
 */
public interface TiledSpace2D<T extends Tiled2D> extends TiledSpace<T>, Space2D<T>, Bounded2D
{
	/**
	 * Returns the columns of the {@code TiledSpace2D}.
	 * 
	 * @return  a column count
	 */
	public default int Columns()
	{
		return Dimensions()[1];
	}
	
	/**
	 * Returns the rows of the {@code TiledSpace2D}.
	 * 
	 * @return  a row count
	 */
	public default int Rows()
	{
		return Dimensions()[0];
	}
	
	
	@Override
	public default Bounds2D Bounds()
	{
		return (Bounds2D) TiledSpace.super.Bounds();
	}
	
	@Override
	public default int Order()
	{
		return 2;
	}
}