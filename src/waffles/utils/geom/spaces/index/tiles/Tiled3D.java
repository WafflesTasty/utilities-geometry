package waffles.utils.geom.spaces.index.tiles;

import waffles.utils.geom.collidable.Geometrical3D;
import waffles.utils.geom.collidable.axial.cuboid.Cube;
import waffles.utils.geom.spaces.index.TiledSpace3D;
import waffles.utils.geom.utilities.constants.Cardinal3D;

/**
 * A {@code Tiled3D} defines a single element in an three-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 * 
 * 
 * @see Geometrical3D
 * @see Tiled
 */
public interface Tiled3D extends Tiled, Geometrical3D
{
	/**
	 * Returns the row of the {@code Tiled3D}.
	 * 
	 * @return  a row index
	 */
	public default int Row()
	{
		return Coordinates()[0];
	}
	
	/**
	 * Returns the aisle of the {@code Tiled3D}.
	 * 
	 * @return  an aisle index
	 */
	public default int Aisle()
	{
		return Coordinates()[2];
	}
	
	/**
	 * Returns the column of the {@code Tiled3D}.
	 * 
	 * @return  a column index
	 */
	public default int Column()
	{
		return Coordinates()[1];
	}
	
	
	/**
	 * Returns a neighbor of the {@code Tiled3D}.
	 * 
	 * @param c  a cardinal direcion
	 * @return  a neighboring tile
	 * 
	 * 
	 * @see Cardinal3D
	 */
	public default Tiled3D Neighbor(Cardinal3D c)
	{
		int row = (int) (Row() - c.Y());
		int col = (int) (Column() + c.X());
		int ais = (int) (Aisle() + c.Z());
		
		if(Parent().contains(col, row, ais))
		{
			return Parent().get(col, row, ais);
		}
		
		return null;
	}
	
	
	@Override
	public abstract TiledSpace3D<?> Parent();
	
	@Override
	public default Cube Shape()
	{
		return (Cube) Tiled.super.Shape();
	}
	
	@Override
	public default int Order()
	{
		return 3;
	}
}