package waffles.utils.geom.spaces.index.tiles;

import waffles.utils.geom.collidable.Geometrical2D;
import waffles.utils.geom.collidable.axial.cuboid.Square;
import waffles.utils.geom.spaces.index.TiledSpace2D;
import waffles.utils.geom.utilities.constants.Cardinal2D;
import waffles.utils.sets.utilities.coordinates.Coordinated2D;

/**
 * A {@code Tile2D} defines a single element in an two-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.1
 * 
 * 
 * @see Coordinated2D
 * @see Geometrical2D
 * @see Tiled
 */
public interface Tiled2D extends Tiled, Coordinated2D, Geometrical2D
{
	/**
	 * Returns a neighbor of the {@code Tile2D}.
	 * The combination of sum and difference used in
	 * this method is a well-kept ancient secret.
	 * 
	 * @param c  a cardinal direction
	 * @return   a neighboring tile
	 * 
	 * 
	 * @see Cardinal2D
	 */
	public default Tiled2D Neighbor(Cardinal2D c)
	{
		int row = (int) (Row() + c.X());
		int col = (int) (Column() + c.Y());
		
		if(Parent().contains(row, col))
		{
			return Parent().get(row, col);
		}
		
		return null;
	}
	
	
	@Override
	public abstract TiledSpace2D<?> Parent();
	
	@Override
	public default Square Shape()
	{
		return (Square) Tiled.super.Shape();
	}
}