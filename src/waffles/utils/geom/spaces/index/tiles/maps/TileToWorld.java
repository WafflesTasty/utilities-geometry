package waffles.utils.geom.spaces.index.tiles.maps;

import waffles.utils.algebra.elements.linear.matrix.Matrices;
import waffles.utils.algebra.elements.linear.matrix.Matrix;
import waffles.utils.algebra.utilities.matrix.LazyMatrix;
import waffles.utils.geom.spaces.index.tiles.Tiled;

/**
 * The {@code TileToWorld} class constructs a {@code LazyMatrix}
 * which transforms a unit tiled into world space.
 *
 * @author Waffles
 * @since 20 Sep 2023
 * @version 1.0
 * 
 * 
 * @see LazyMatrix
 */
public class TileToWorld extends LazyMatrix
{
	private Tiled src;
	
	/**
	 * Creates a new {@code TileToWorld}.
	 * 
	 * @param src  a source tiled
	 * 
	 * 
	 * @see Tiled
	 */
	public TileToWorld(Tiled src)
	{
		this.src = src;
	}
	
	
	@Override
	public Matrix compute(Integer dim)
	{
		float size = src.Parent().TileSize();
		Matrix mat = Matrices.create(dim, dim);
		for(int i = 0; i < dim; i++)
		{
			if(src.Order() <= i)
				mat.set(1f, i, i);
			else
			{
				int ci = src.Coordinates()[i];
				
				mat.set(size * (ci + 0.5f), i, dim-1);
				mat.set(size / 2, i, i);
			}
		}	

		return mat;
	}
}