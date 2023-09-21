package waffles.utils.geom.spaces.index.tiles.maps;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.index.tiles.Tiled;

/**
 * The {@code WorldToTile} class constructs a {@code LazyMatrix}
 * which transforms a world space tiled to the unit cube.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class WorldToTile extends LazyMatrix
{
	private Tiled src;
	
	/**
	 * Creates a new {@code WorldToTile}.
	 * 
	 * @param src  a source tiled
	 * 
	 * 
	 * @see Tiled
	 */
	public WorldToTile(Tiled src)
	{
		this.src = src;
	}
	
	
	@Override
	public Matrix compute(Integer dim)
	{
		float size = src.Parent().TileSize();	
		Matrix inv = Matrices.create(dim, dim);
		for(int i = 0; i < dim; i++)
		{
			if(src.Order() <= i)
				inv.set(1f, i, i);
			else
			{
				int ci = src.Coordinates()[i];
				
				inv.set(-(2 * ci + 1), i, dim-1);
				inv.set(2f / size, i, i);
			}
		}

		return inv;
	}
}