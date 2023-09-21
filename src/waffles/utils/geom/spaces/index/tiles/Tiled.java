package waffles.utils.geom.spaces.index.tiles;

import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.collidable.Geometrical;
import waffles.utils.geom.collidable.axial.cuboid.ICube;
import waffles.utils.geom.maps.GlobalMap;
import waffles.utils.geom.spaces.index.TiledSpace;
import waffles.utils.geom.spaces.index.tiles.maps.TileToWorld;
import waffles.utils.geom.spaces.index.tiles.maps.WorldToTile;
import waffles.utils.geom.utilities.Geometries;
import waffles.utils.tools.patterns.semantics.Ordered;

/**
 * A {@code Tiled} object can be contained in an n-dimensional {@code TiledSpace}.
 *
 * @author Waffles
 * @since 26 Feb 2020
 * @version 1.1
 * 
 * 
 * @see Geometrical
 * @see Ordered
 */
public interface Tiled extends Geometrical, Ordered
{
	/**
	 * A {@code IndexTransform} defines a global map for a {@code Tiled}.
	 *
	 * @author Waffles
	 * @since 26 Feb 2020
	 * @version 1.0
	 * 
	 * 
	 * @see GlobalMap
	 */
	public static class Transform implements GlobalMap
	{
		private TileToWorld ttw;
		private WorldToTile wtt;
				
		/**
		 * Creates a new {@code IndexTransform}.
		 * 
		 * @param s  a src tiled
		 * 
		 * 
		 * @see Tiled
		 */
		public Transform(Tiled s)
		{
			ttw = new TileToWorld(s);
			wtt = new WorldToTile(s);
		}
		
		
		@Override
		public LazyMatrix UTW()
		{
			return ttw;
		}


		@Override
		public LazyMatrix WTU()
		{
			return wtt;
		}
	}
	
	
	/**
	 * Returns the coordinates of the {@code Tiled}.
	 * 
	 * @return  a coordinate set
	 */
	public abstract int[] Coordinates();
	
	/**
	 * Returns the parent space of the {@code Tiled}.
	 * 
	 * @return  a parent space
	 * 
	 * 
	 * @see TiledSpace
	 */
	public abstract TiledSpace<?> Parent();
	
	
	@Override
	public default Transform Transform()
	{
		return new Transform(this);
	}

	@Override
	public default ICube Shape()
	{
		return Geometries.cube(Order());
	}
	
	@Override
	public default int Order()
	{
		return Coordinates().length;
	}
}